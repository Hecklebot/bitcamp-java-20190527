package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardDetailCommand implements Command {

  private PhotoBoardDao photoPhotoBoardDao;

  public PhotoBoardDetailCommand(PhotoBoardDao photoPhotoBoardDao) {
    this.photoPhotoBoardDao = photoPhotoBoardDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoPhotoBoard = photoPhotoBoardDao.findBy(no);

      if (photoPhotoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      out.printf("제목: %s\n", photoPhotoBoard.getTitle());
      out.printf("작성일: %s\n", photoPhotoBoard.getCreatedDate());
      
    } catch (Exception e) {
      System.out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }



  }

}
