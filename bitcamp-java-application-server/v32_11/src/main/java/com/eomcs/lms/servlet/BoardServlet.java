package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.eomcs.lms.dao.BoardSerialDao;
import com.eomcs.lms.domain.Board;

// 게시물 요청을 처리하는 담당자
public class BoardServlet implements Servlet {

  BoardSerialDao boardDao;
  ObjectInputStream in;
  ObjectOutputStream out;
  

  public BoardServlet(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    this.in = in;
    this.out = out;
    boardDao = new BoardSerialDao("./board.ser");
  }

  public void saveData() throws IOException {
    boardDao.saveData();
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

  private void addBoard() throws Exception {
    Board board = (Board) in.readObject();
    if(boardDao.insert(board) == 0) {
      fail("게시물을 입력할 수 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listBoard() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(boardDao.findAll());
  }

  private void detailBoard() throws Exception {
    int no = in.readInt();

    Board board = boardDao.findBy(no);
    if (board == null) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(board);

  }

  private void updateBoard() throws Exception {
    // 인덱스를 알고 있을 때, 사용할 수 있는 방법
    Board board = (Board) in.readObject();
    if(boardDao.update(board) == 0) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }
    boardDao.update(board);
    out.writeUTF("ok");


  }

  private void deleteBoard() throws Exception {
    int no = in.readInt();

    if (boardDao.delete(no) == -1) {
      fail("해당 번호의 게시물이 없습니다.");
      return;
    }

    out.writeUTF("ok");

  }

  private void fail(String cause) throws IOException {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }


}
