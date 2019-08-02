// v32_4: 클라이언트와 서버간에 데이터 주고 받기 II - 인스턴스 주고 받기
package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.domain.Member;

public class ServerApp {
  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");

    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");

      try (Socket clientSocket = serverSocket.accept();
          ObjectInputStream in =
              new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out =
              new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음");

        // 클라이언트가 보낸 serialize 된 인스턴스 데이터를 읽는다.
        Member member = (Member) in.readObject();
        System.out.println("클라이언트가 보낸 객체를 읽었음");
        
        // 클라이언트가 보낸 객체의 내용이 서버쪽 콘솔에서 한 번 출력해보고
        System.out.println(member);
        
        // 클라이언트가 데이터를 받았다고 응답한다.
        out.writeUTF("OK");
        out.flush(); // PrintWriter가 출력한 데이터는 버퍼에 있다. 강제로 출력하라
        System.out.println("클라이언트로 데이터를 보냈음");
        
      } catch (Exception e) {
        e.printStackTrace();
      } 
      
      System.out.println("클라이언트와 연결을 끊었음");

    } catch (IOException e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료");
  }
}
