//
package com.eomcs.lms;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerTest {
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션 테스트]");

    // 서버에 통신을 연결한다.
    // -> new Socket(인터넷 주소, 포트번호)
    // -> 서버와 연결될 때 까지 리턴하지 않는다.
    //
    // 인터넷 주소?
    // -> IP 주소 (192.168.0.1)
    // -> 도메인 이름 (www.naver.com)
    //
    // localhost?
    // -> 로컬 PC를 가리키는 특수 도메인 이름이다.
    //
    // 127.0.0.1?
    // -> 로컬 PC를 가리키는 특수 IP 주소이다.
    try (Socket socket = new Socket("192.168.0.51", 8888);
        // 서버와의 입출력을 위해 스트림 객체를 준비한다.
        // 버퍼를 사용할 경우, 데이터를 보내는 쪽에서는 출력 스트림을 먼저 준비하라
        PrintWriter out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

      System.out.println("서버와 연결되었음");
      // 서버에 출력한다.
      out.println("'소한샘'입니다.");
      out.flush(); // 버퍼로 출력한 내용을 서버쪽에 보내도록 강제해야 한다.
      System.out.println("서버에 데이터를 보냈음");
      
      // 서버가 보낸 데이터를 읽는다.
      String response = in.readLine();
      System.out.println("서버에서 데이터를 받았음");
      
      // 서버가 보낸 데이터를 콘솔창에 출력한다.
      System.out.println("--->" + response);
      
    } catch (IOException e) {
      // 예외가 발생하면 어디에서 예외가 발생했는지 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음");
  }
}
