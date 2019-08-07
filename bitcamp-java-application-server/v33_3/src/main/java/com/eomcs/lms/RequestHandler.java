package com.eomcs.lms;

import java.net.Socket;

// 별도의 실행 흐름을 만들기 위해서는 Thread 클래스를 상속받아야 한다.
// 또는 Runnable 자격을 갖춘 후 Thread로 실행해야 한다.
public class RequestHandler extends Thread {
  // 별도의 실행 흐름(thread)은 run() 메서드에 작성한다.
  
  Socket socket;
  // 클라이언트가 접속하면 그 소켓 정보를 가지고 이 클래스의 인스턴스를 생성한다.
  // -> 그런 후 스레드를 분리하여 실행시키면 run()이 실행된다.
  public RequestHandler(Socket socket) {
    this.socket = socket;
  }
  
  @Override
  public void run() {
    // 여기에서 클라이언트의 요청을 처리한다.
    // main thread와는 분리된 실행흐름(thread)이기 때문에
    // 여기에서 실행이 지체되더라도 main thread의 흐름에는 영향을 끼치지 않는다.
  }
}
