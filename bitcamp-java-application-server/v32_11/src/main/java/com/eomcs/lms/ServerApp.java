// v32_11: Servlet 클래스에서 데이터 저장 기능을 별도의 클래스로 분리
package com.eomcs.lms;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

public class ServerApp {

  static ObjectInputStream in;
  static ObjectOutputStream out;

  public static void main(String[] args) {
    System.out.println("[수업관리시스템 서버 애플리케이션]");


    try (ServerSocket serverSocket = new ServerSocket(8888)) {
      System.out.println("서버 시작");
   // try with resources에선 반드시 변수의 타입을 선언해줘야 한다.
      try (Socket clientSocket = serverSocket.accept();
          ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
          ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream())) {

        System.out.println("클라이언트와 연결되었음");

        // 다른 메서드가 사용할 수 있도록 입출력 스트림을 스태틱 변수에 저장한다.
        BoardServlet boardServlet = new BoardServlet(in, out);
        MemberServlet memberServlet = new MemberServlet(in, out);
        LessonServlet lessonServlet = new LessonServlet(in, out);

        while (true) {
          // 클라이언트가 보낸 명령을 읽는다.
          String command = in.readUTF();
          System.out.println(command + " 요청 처리중...");

          // 명령어에 따라 처리한다.
          if (command.startsWith("/board/")) {
            boardServlet.service(command);

          } else if (command.startsWith("/member/")) {
            memberServlet.service(command);

          } else if (command.startsWith("/lesson/")) {
            lessonServlet.service(command);

          } else if (command.equals("quit")) {
            out.writeUTF("ok");
            out.flush();
            break;

          } else {
            out.writeUTF("fail");
            out.writeUTF("지원하지 않는 명령입니다.");
          }
          out.flush();
          System.out.println("클라이언트에게 응답 완료");
        }
        
        // 클라이언트와 연결을 종료하기 전에 작업내용을 파일에 저장한다.
        boardServlet.saveData();
        lessonServlet.saveData();
        memberServlet.saveData();
      }

      System.out.println("클라이언트와 연결을 끊었음");

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("서버 종료");
  }



  // private static void updateMember0() throws IOException, ClassNotFoundException {
  // Member member = (Member) in.readObject();
  //
  // for (Member m : memberList) {
  // if (member.getNo() == m.getNo()) {
  // m.setName(member.getName());
  // m.setEmail(member.getEmail());
  // m.setPassword(member.getPassword());
  // m.setPhoto(member.getPhoto());
  // m.setTel(member.getTel());
  // m.setRegisteredDate(member.getRegisteredDate());
  // out.writeUTF("ok");
  // return;
  // }
  // }
  //
  // }


}
