package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Lesson;

public class ServerTest2 {

  static ObjectOutputStream out;
  static ObjectInputStream in;

  public static void main(String[] args) throws Exception {
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
    try (Socket socket = new Socket("localhost", 8888);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      
      System.out.println("서버와 연결되었음");

      // 다른 메서드가 사용할 수 있도록 static 변수에 주소값을 저장한다.
      ServerTest2.out = out;
      ServerTest2.in = in;
      

      Lesson lesson = new Lesson();
      lesson.setNo(1);
      lesson.setTitle("java programming");
      lesson.setContents("okok");
      lesson.setStartDate(Date.valueOf("2019-1-1"));
      lesson.setEndDate(Date.valueOf("2019-2-2"));
      lesson.setTotalHours(200);
      lesson.setDayHours(4);

      if (!add(lesson)) {
        error();
      }
      System.out.println("------------------------------------");

      lesson = new Lesson();
      lesson.setNo(2);
      lesson.setTitle("java programming2");
      lesson.setContents("okok2");
      lesson.setStartDate(Date.valueOf("2019-2-2"));
      lesson.setEndDate(Date.valueOf("2019-3-3"));
      lesson.setTotalHours(400);
      lesson.setDayHours(3);

      if (!add(lesson)) {
        error();
      }
      System.out.println("------------------------------------");

      if (!list()) {
        error();
      }
      System.out.println("------------------------------------");

      if (!delete()) {
        error();
      }
      System.out.println("------------------------------------");

      if (!list()) {
        error();
      }
      System.out.println("------------------------------------");

      if (!detail()) {
        error();
      }
      System.out.println("------------------------------------");
      
      lesson = new Lesson();
      lesson.setNo(1);
      lesson.setTitle("java web programming");
      lesson.setContents("웹 개발자 양성과정");
      lesson.setStartDate(Date.valueOf("2019-5-27"));
      lesson.setEndDate(Date.valueOf("2019-11-27"));
      lesson.setTotalHours(1000);
      lesson.setDayHours(8);
      
      if (!update(lesson)) {
        error();
      }
      System.out.println("------------------------------------");
      
      if (!list()) {
        error();
      }
      System.out.println("------------------------------------");
      
      if (!quit()) {
        error();
      }
      System.out.println("------------------------------------");

    } catch (IOException e) {
      // 예외가 발생하면 어디에서 예외가 발생했는지 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음");
  }

  private static boolean add(Lesson obj) throws IOException {
    out.writeUTF("/lesson/add");
    out.writeObject(obj);
    out.flush();
    System.out.print("add 요청함 -> ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }

    System.out.println("처리 완료");
    return true;
  }

  private static boolean list() throws IOException, ClassNotFoundException {
    out.writeUTF("/lesson/list");
    out.flush();
    System.out.print("list 요청함 -> ");
    // 서버가 보낸 데이터를 읽는다.
    if (!in.readUTF().equals("ok")) {
      return false;
    }

    System.out.println("처리 완료");
    @SuppressWarnings("unchecked")
    List<Lesson> list = (List<Lesson>) in.readObject();
    System.out.println("------------------------------------");
    for (Lesson obj : list) {
      System.out.println(obj);
    }
    return true;
  }

  private static boolean detail() throws IOException, ClassNotFoundException {
    // 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/lesson/detail");
    out.writeInt(1);
    out.flush();
  
    System.out.print("detail 요청함 -> ");
  
    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료");
    System.out.println(in.readObject());
    
    return true;
  }

  private static boolean update(Lesson obj) throws IOException, ClassNotFoundException {
    out.writeUTF("/lesson/update");
    out.writeObject(obj);
    out.flush();
  
    System.out.print("update 요청함 -> ");
  
    if (!in.readUTF().equals("ok")) {
      return false;
    }
    
    System.out.println("처리 완료");
    
    return true;
  }

  private static boolean delete() throws IOException {
    // 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/lesson/delete");
    out.writeInt(2);
    out.flush();

    System.out.print("delete 요청함 -> ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료");
    return true;
  }

  private static void error() throws IOException {
    System.out.printf("오류: %s\n", in.readUTF());
  }

  private static boolean quit() throws Exception {
    out.writeUTF("quit");
    out.flush();
    System.out.print("quit 요청함 ->");

    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료");
    return true;
  }
  
  
  
}
