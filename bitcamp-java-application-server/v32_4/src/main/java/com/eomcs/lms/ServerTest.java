package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

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
    try (Socket socket = new Socket("192.168.0.49", 8888);
        // 서버와의 입출력을 위해 스트림 객체를 준비한다.
        // 버퍼를 사용할 경우, 데이터를 보내는 쪽에서는 출력 스트림을 먼저 준비하라
        ObjectOutputStream out =
            new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in =
            new ObjectInputStream(socket.getInputStream())) {

      System.out.println("서버와 연결되었음");
      
      // 서버에 전송할 객체를 준비한다.
      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");
      
      // 서버에 객체를 전송한다.
      out.writeObject(member);
      out.flush(); // 버퍼로 출력한 내용을 서버쪽에 보내도록 강제해야 한다.
      System.out.println("서버에 객체를 보냈음");
      
      // 서버가 보낸 데이터를 읽는다.
      String response = in.readUTF();
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
