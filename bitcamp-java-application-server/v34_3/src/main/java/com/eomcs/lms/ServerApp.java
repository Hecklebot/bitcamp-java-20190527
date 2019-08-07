// v34_3: Runnable 인터페이스를 사용하여 간접적으로 스레드를 실행하기
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import com.eomcs.lms.context.AppInitListener;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.servlet.Servlet;

public class ServerApp {

  public static boolean isStopping = false;

  int port;
  ArrayList<ServletContextListener> listeners = new ArrayList<>();

  public ServerApp(int port) {
    this.port = port;
  }

  // 서버가 실행되는 동안 공유할 객체를 보관하는 저장소를 준비한다.
  HashMap<String, Object> servletContext = new HashMap<>();


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
        new Thread(new RequestHandler(socket)).start();
        if (isStopping) {
          break;
        }
      } // while

      // 서버가 될 때 관찰자에게 보고한다.
      for (ServletContextListener listener : listeners) {
        listener.contextDestroyed(servletContext);
      }


    } catch (Exception e) {
      e.printStackTrace();
    }
    System.out.println("서버 종료");

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
          isStopping = true;
          return;
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
