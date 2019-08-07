// v33_3: 클라이언트 요청마다 서블릿 객체를 만들지 않기
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
        try (Socket clientSocket = serverSocket.accept();
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

          System.out.println("클라이언트와 연결되었음");

          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();
          System.out.println(command + " 요청 처리중...");


          Servlet servlet = null;

          // 명령어에 따라 처리한다.
          if (command.equals("serverstop")) { // 어차피 서버종료 요청하면 끝
            break;
          } else if ((servlet = findServlet(command)) != null) { // findServlet()를 수행해서 servlet에 뭔가 들어갔다면
            servlet.service(command, in, out);                   // 그 servlet에서 service()를 수행
          } else {
            out.writeUTF("fail");                                // servlet이 null이라면 fail을 출력하라
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료");
        }

        System.out.println("클라이언트와 연결을 끊었음");
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
    // -> command가 /admin/list 같이 없는 내용이라면 null을 리턴한다.
    return null;
  }


  public static void main(String[] args) {

    ServerApp server = new ServerApp(8888);
    // 서버가 시작되면 보고받는 메서드
    server.addServletContextListener(new AppInitListener());

    server.execute();

  }
}
