package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

// 게시물 요청을 처리하는 담당자
public class MemberServlet implements Servlet {
  
  ObjectInputStream in;
  ObjectOutputStream out;
  MemberDao memberDao;
  
  public MemberServlet(MemberDao memberDao, ObjectInputStream in, ObjectOutputStream out) throws Exception {
    
    this.in = in;
    this.out = out;
    this.memberDao = memberDao;
    
    // Servlet가 사용할 DAO를 직접 만들기 않고 외부에서 주입받아 사용한다.
    // 이렇게 의존하는 객체를 외부에서 주입받아 사용하는 방법을 (Dependency Injection (DI); 의존성 주입) 이라 부른다.
    // 그래야만 의존객체를 교체하기 쉽다.
  }

  
  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/delete":
        deleteMember();
        break;
      case "/member/detail":
        detailMember();
        break;
      case "/member/update":
        updateMember();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

  private void addMember() throws Exception {
    Member member = (Member) in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if(memberDao.insert(member) == 0) {
      fail("회원정보를 입력할 수 없습니다.");
      return;
    }
    
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberDao.findAll());
  }

  private void detailMember() throws Exception {
    int no = in.readInt();
    
    Member member = memberDao.findBy(no);

    if (member == null) {
      fail("해당 번호의 회원정보가 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);

  }

  private void updateMember() throws Exception {
    // 인덱스를 알고 있을 때, 사용할 수 있는 방법
    Member member = (Member) in.readObject();
    member.setRegisteredDate(new Date(System.currentTimeMillis()));
    if (memberDao.update(member) == 0) {
      fail("해당 번호의 회원정보가 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();
    
    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 회원정보가 없습니다.");
      return;
    }
    out.writeUTF("ok");

  }
  private void fail(String cause) throws IOException {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
