package com.eomcs.lms;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.domain.Member;

public class ServerTest {

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

      // 다른 메서드가 사용할 수 있도록 static 변수에 주소값을 저장한다.
      ServerTest.out = out;
      ServerTest.in = in;

      System.out.println("서버와 연결되었음");

      Member member = new Member();
      member.setNo(1);
      member.setName("홍길동");
      member.setEmail("hong@test.com");
      member.setPhoto("hong.gif");
      member.setTel("1111-1111");

      if (!add(member)) {
        error();
      }
      System.out.println("------------------------------------");

      member = new Member();
      member.setNo(2);
      member.setName("임꺽정");
      member.setEmail("leem@test.com");
      member.setPhoto("leem.gif");
      member.setTel("2222-2222");

      if (!add(member)) {
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

      if (!quit()) {
        error();
      }
      System.out.println("------------------------------------");

    } catch (RequestException e) {
      // 서버에서 요청 처리에 실패했다면, 서버가 보낸 이유를 받는다.
      System.out.printf("오류: %s\n", in.readUTF());

    } catch (IOException e) {
      // 예외가 발생하면 어디에서 예외가 발생했는지 정보를 모두 출력한다.
      e.printStackTrace();
    }

    System.out.println("서버와 연결 끊음");
  }

  private static void error() throws IOException {
    System.out.printf("오류: %s\n", in.readUTF());
  }

  private static boolean add(Member m) throws IOException, RequestException {
    out.writeUTF("/member/add");
    out.writeObject(m);
    out.flush();
    System.out.print("add 요청함 -> ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }

    System.out.println("처리 완료");
    return true;
  }

  private static boolean list() throws IOException, ClassNotFoundException, RequestException {
    out.writeUTF("/member/list");
    out.flush();
    System.out.print("list 요청함 -> ");
    // 서버가 보낸 데이터를 읽는다.
    if (!in.readUTF().equals("ok")) {
      return false;
    }

    System.out.println("처리 완료");
    @SuppressWarnings("unchecked")
    List<Member> list = (List<Member>) in.readObject();
    System.out.println("------------------------------------");
    for (Member m : list) {
      System.out.println(m);
    }
    return true;
  }

  private static boolean delete() throws IOException, RequestException {
    // 서버가 처리할 수 없는 명령어 보내기
    out.writeUTF("/member/delete");
    out.flush();

    System.out.print("delete 요청함 -> ");

    if (!in.readUTF().equals("ok")) {
      return false;
    }
    System.out.println("처리 완료");
    return true;
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
