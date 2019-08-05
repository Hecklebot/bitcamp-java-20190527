package com.eomcs.lms.dao.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import com.eomcs.lms.domain.Board;

public class BoardCsvDao extends AbstractCsvDataSerializer<Board, Integer> {

  public BoardCsvDao(String file) {
    super(file);

    try {
      loadData();
      System.out.println("게시물 데이터 로딩 완료");
    } catch (Exception e) {
      System.out.println("게시물 데이터 로딩 중 오류 발생");
    }

  }

  @Override
  public void saveData() throws IOException {

    try {
      super.saveData();
      System.out.println("게시물 데이터 로딩 완료");

    } catch (FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다.");

    } catch (Exception e) {
      System.out.println("파일에 데이터를 출력하는 중 오류발생");
      e.printStackTrace();
    }
  }

  @Override
  protected Board createObject(String[] values) {
    // 저장한 파일에서 읽어들인 데이터의 CSV 형식은 다음과 같다고 가정하자.
    // 번호,내용,생성일,조회수
    Board board = new Board();
    board.setNo(Integer.parseInt(values[0]));
    board.setContents(values[1]);
    board.setCreatedDate(Date.valueOf(values[2]));
    board.setViewCount(Integer.parseInt(values[3]));

    return board;
  }

  // 슈퍼 클래스에서 template method(loadSave())를 정의하고 객체 생성의 구체적인 구현은 서브 클래스에서 완성해야 한다.
  @Override
  protected String createCSV(Board obj) {

    // CSV 형식의 String으로 넘겨서 저장한다.
    return String.format("%d,%s,%s,%d", obj.getNo(), obj.getContents(), obj.getCreatedDate(),
        obj.getViewCount());
  }

  @Override
  public int indexOf(Integer key) {
    int i = 0;
    for (Board b : list) {
      if (b.getNo() == key) {
        return i;
      }
      i++;
    }
    return -1;
  }

  public int add(Board board) throws Exception {
    list.add(board);
    return 1;
  }

  public List<Board> list() throws Exception {
    return list;
  }

  public Board get(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  public int modify(Board board) throws Exception {
    int index = indexOf(board.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, board);
    return 1;
  }

  public int remove(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    list.remove(index);
    return 1;
  }

}
