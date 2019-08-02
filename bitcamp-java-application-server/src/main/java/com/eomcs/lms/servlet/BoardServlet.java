package com.eomcs.lms.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {

  ArrayList<Board> boardList = new ArrayList<>();
  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws ClassNotFoundException {
    
    this.in = in;
    this.out = out;
    
    try {
      loadData();
    } catch (IOException e) {
      System.out.println("게시물 데이터 로딩 중 오류 발생");
    }

  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./board.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
      boardList = (ArrayList<Board>) in.readObject();
      System.out.println("게시물 데이터 로딩 완료");
    }  
  }

  public void saveData() throws IOException {
    File file = new File("./board.ser");

//    if (!file.exists()) {
//      file.createNewFile();
//    }
    
    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));) {
      out.writeObject(boardList);
      System.out.println("게시물 데이터 저장 완료");
      
    } catch(FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다.");
      
    } catch(IOException e) {
      System.out.println("파일에 데이터를 출력하는 중 오류발생");
      e.printStackTrace();
    }
  }

  @Override
  public void service(String command) throws Exception {
    switch (command) {
      case "/board/add":
        addBoard();
        break;
      case "/board/list":
        listBoard();
        break;
      case "/board/delete":
        deleteBoard();
        break;
      case "/board/detail":
        detailBoard();
        break;
      case "/board/update":
        updateBoard();
        break;
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }

  private void addBoard() throws ClassNotFoundException, IOException {
    // 클라리언트가 보낸 객체를 읽는다.
    Board board = (Board) in.readObject();
    boardList.add(board);
    out.writeUTF("ok");
  }

  private void listBoard() throws IOException {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(boardList);
  }

  private void detailBoard() throws IOException {
    int no = in.readInt();

    int index = indexOfBoard(no);
    // if문에서는 부정적인 조건을 찾는게 코딩이 수월하다 왜?
    // 코드의 흐름을 들여쓰기로 구분하는게 좋다. 다른 들여쓰기 -> 다른 흐름

    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(boardList.get(index));


    // for (Member m : memberList) {
    // if (m.getNo() == no) {
    // out.writeUTF("ok");
    // out.writeObject(m);
    // return;
    // }
    // out.writeUTF("fail");
    // }
    //
    // out.writeUTF("fail");
    // out.writeUTF("해당 번호의 회원이 없습니다.");

  }

  private void updateBoard() throws IOException, ClassNotFoundException {
    // 인덱스를 알고 있을 때, 사용할 수 있는 방법
    Board board = (Board) in.readObject();
    int index = indexOfBoard(board.getNo());

    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }

    boardList.set(index, board);
    out.writeUTF("ok");

    // for (int i = 0; i < memberList.size(); i++) {
    // Member m = memberList.get(i);
    // if (member.getNo() == m.getNo()) {
    // // 기존 객체를 클라이언트가 보낸 객체로 교체한다.
    // memberList.set(i, member);
    // out.writeUTF("ok");
    // return;
    // }
    // }

  }

  private void deleteBoard() throws IOException {
    int no = in.readInt();

    int index = indexOfBoard(no);
    if (index == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }

    boardList.remove(index);
    out.writeUTF("ok");

    // for (Member m : memberList) {
    // if (m.getNo() == no) {
    // memberList.remove(m);
    // out.writeUTF("ok");
    // return;
    // }
    // }

    // out.writeUTF("fail");
    // out.writeUTF("해당 번호의 회원이 없습니다.");

  }

  private int indexOfBoard(int no) {
    int i = 0;
    for (Board b : boardList) {
      if (b.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }



  private void fail(String cause) throws IOException {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
