// v35_2: serverstop 명령어를 받으면 JVM 강제종료하기(좋은 방법은 아니다.)
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.context.AppInitListener;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {

  int port;
  ArrayList<ServletContextListener> listeners = new ArrayList<>();

  public ServerApp(int port) {
    this.port = port;
  }

  // 서버가 실행되는 동안 공유할 객체를 보관하는 저장소를 준비한다.
  HashMap<String, Object> servletContext = new HashMap<>();

  // Thread Pool
  ExecutorService executorService = Executors.newCachedThreadPool();

  public void execute() {

    System.out.println("[수업관리시스템 서버 애플리케이션]");


    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 시작");

      // 서버가 시작되면 보고를 받을 관찰자에게 보고한다.
      for (ServletContextListener listener : listeners) {
        listener.contextInitializer(servletContext);
      }

      while (true) {
        System.out.println("클라이언트 요청을 기다리는 중...");
        Socket socket = serverSocket.accept();

        // 스레드 풀을 사용할 때는 직접 스레드를 만들지 않는다.
        // 단지 스레드풀에게 스레드가 실행할 코드(Runnable)를 제출한다.
        // -> 스레드풀은 남은 스레드가 없으면 새로 만들어 해당 코드(RequestHandler)를 실행할 것이다.
        // -> 남아있는 스레드가 있다면 그 스레드를 이용하여 해당 코드(RequestHandler)를 실행할 것이다.
        executorService.submit(new RequestHandler(socket));
      } // while



    } catch (Exception e) {
      e.printStackTrace();
    }
    

  }

  // 서버가 시작하거나 종료할 때, 보고를 받을 객체를 등록하는 메서드
  // 즉, 서블릿을 실행하는 데 필요한 환경을 준비시키는 객체를 등록한다.
  public void addServletContextListener(ServletContextListener listener) {
    listeners.add(listener);
  }

  private Servlet findServlet(String command) {
    Set<String> keys = servletContext.keySet();
    // 명령어에 포함된 키를 찾아서 해당 키로 저장된 서블릿을 꺼낸다.
    for (String key : keys) {
      if (command.startsWith(key)) { // 입력받은 command가 키값으로 시작한다면
        return (Servlet) servletContext.get(key); // 그 키값의 밸류를 꺼낸다.
      }
    }
    return null;
  }

  // serverStop 명령처리
  private void stop() {
    // 서버가 종료될 때 관찰자에게 보고한다.
    for (ServletContextListener listener : listeners) {
      listener.contextDestroyed(servletContext);
    }

    // 스레드 풀에게 동작을 멈추라고 알려준다.
    // -> 그러면 스레드 풀은 작업중인 모든 스레드의 작업이 완료될 때까지 기다렸다가 스레드 풀의 작업을 종료한다.
    executorService.shutdown();
    
    System.out.println("서버 종료");

    System.exit(0); // 현재 실행중인 스레드까지 강제종료시킨다.
  }

  // Thread를 상속받아 직접 스레드 역할을 하는 대신에
  // Thread에서 독립적으로 실행할 코드를 정의한다.
  private class RequestHandler implements Runnable { // 중첩 클래스도 상위 클래스의 멤버다

    Socket socket;

    public RequestHandler(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {
      try (Socket clientSocket = this.socket;
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음");

        String command = in.readUTF();
        System.out.println(command + " 요청 처리중...");


        Servlet servlet = null;

        if (command.equals("serverstop")) { // 어차피 서버종료 요청하면 끝
          stop();
        } else if ((servlet = findServlet(command)) != null) { // findServlet()를 수행해서 servlet에 뭔가
          // 들어갔다면
          servlet.service(command, in, out); // 그 servlet에서 service()를 수행
        } else {
          out.writeUTF("fail"); // servlet이 null이라면 fail을 출력하라
          out.writeUTF("지원하지 않는 명령입니다.");
        }
        out.flush();
        System.out.println("클라이언트에게 응답 완료");
      } catch (Exception e) {
        System.out.println("클라이언트와의 통신 오류" + e.getMessage());
      }

      System.out.println("클라이언트와 연결을 끊었음");
    }
  }

  public static void main(String[] args) {

    ServerApp server = new ServerApp(8888);
    // 서버가 시작되면 보고받는 메서드
    server.addServletContextListener(new AppInitListener());

    server.execute();

  }
}
