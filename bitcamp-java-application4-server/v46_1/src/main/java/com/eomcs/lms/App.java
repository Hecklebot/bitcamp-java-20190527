// v46_1: Bean Container 적용하기
package com.eomcs.lms;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.eomcs.lms.handler.Command;
import com.eomcs.util.ApplicationContext;
import com.eomcs.util.SqlSessionFactoryProxy;

public class App {

  private static final int CONTINUE = 1;
  private static final int STOP = 0;
  ApplicationContext appCtx;
  int state;
  ExecutorService executorService = Executors.newCachedThreadPool();

  public App() throws Exception {
    state = CONTINUE;
    appCtx = new ApplicationContext("com.eomcs.lms");
  }

  @SuppressWarnings("static-access")
  private void service() {

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("애플리케이션 서버가 시작되었음!");

      while (true) {
        // 클라이언트가 접속하면 작업을 수행할 Runnable 객체를 만들어 스레드풀에 맡긴다.
        executorService.submit(new CommandProcessor(serverSocket.accept()));

        // 한 클라이언트가 serverstop 명령을 보내면 종료상태로 설정되고, 다음 요청을 처리할 때 즉시 실행을 멈춘다.
        if (state == STOP) {
          break;
        }
      }
      // 스레드풀에게 실행 종료를 요청한다.
      // -> 스레드풀은 자신이 관리하는 스레드들이 실행이 종료되었는지 감시한다.
      executorService.shutdown();

      // 스레드풀이 관리하는 모든 스레드가 종료되었는지 검사하여 매 0.5초마다 검사한다.
      // -> 스레드풀의 모든 스레드가 실행을 종료했으면 즉시 메인 스레드를 종료한다.
      while (!executorService.isTerminated()) { // 종료되지 않은 스레드가 있으면
        Thread.currentThread().sleep(500); // 메인 스레드는 0.5초마다 모든 스레드가 종료되었는지 확인한다.
      } // 모든 스레드가 종료되면 while문을 벗어나고 서버가 종료된다.

      System.out.println("애플리케이션 서버를 종료함!");

    } catch (Exception e) {
      System.out.println("소켓 통신 오류!");
      e.printStackTrace();
    }
  }


  class CommandProcessor implements Runnable {

    Socket socket;

    public CommandProcessor(Socket socket) {
      this.socket = socket;
    }

    @Override
    public void run() {

      // 처음에는 계속 클라이언트 요청을 처리해야 하는 상태로 설정한다.

      try (Socket socket = this.socket;
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          PrintStream out = new PrintStream(socket.getOutputStream())) {

        System.out.println("클라이언트와 연결됨!");

        String request = in.readLine();
        if (request.equals("quit")) {
          out.println("Good bye");
        } else if (request.equals("serverstop")) {
          state = STOP;
          out.println("Good bye");
        } else {
          // non-static 중첩 클래스는 바깥 클래스의 인스턴스 멤버를 사용할 수 있다.
          try {
            Command command = (Command) appCtx.getBean(request);
            command.execute(in, out);
          } catch(Exception e) {
            out.println("해당 명령을 처리할 수 없습니다.");
            e.printStackTrace();
          }
        }
        out.println("!end!");
        out.flush();
        System.out.println("클라이언트와 연결 끊음!");

      } catch (Exception e) {
        System.out.println("클라이언트와 통신 오류!");
      } finally {
        // 현재 스레드가 클라이언트 요청에 대해 응답을 완료했다면,
        // 현재 스레드에 보관된 Mybatis의 SqlSession 객체를 제거해야 한다.
        // 그래야만 다음 클라이언트 요청이 들어왔을 때 새 SqlSession 객체를 사용할 것이다.
        // 그렇지 않으면 오토커밋 여부가 전에 쓴 스레드따라 달라진다.
        // 이게 다 스레드풀이 스레드를 재활용해서 생긴 문제
        SqlSessionFactoryProxy proxy = 
            (SqlSessionFactoryProxy) appCtx.getBean("sqlSessionFactory");
        proxy.clearSession();

      }
    }
  }

  // private void dummyRequest() {
  // // 클라이언트에서 serverstop 명령을 보냈을 때, 즉시 서버 실행을 멈출 수 있도록 state의 값이 STOP로 바뀐다.
  // // 이 state 상태를 인식할 수 있도록 가상의 클라이언트가 되어 요청을 보낸다.
  // try (Socket socket = new Socket("localhost", 8888);
  // PrintStream out = new PrintStream(socket.getOutputStream());
  // BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
  // out.println("quit");
  // out.flush();
  //
  // while(true) {
  // if(in.readLine().equals("!end!")) {
  // break;
  // }
  // }
  // } catch(Exception e) {
  // // 예외는 무시한다.
  // }
  // }


  public static void main(String[] args) {
    try {
      App app = new App();
      app.service();

    } catch (Exception e) {
      System.out.println("시스템 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}


