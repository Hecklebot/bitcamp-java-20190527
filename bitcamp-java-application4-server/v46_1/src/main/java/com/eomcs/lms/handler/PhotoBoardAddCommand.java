package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;

public class PhotoBoardAddCommand implements Command {


  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  private PlatformTransactionManager txManager;

  public PhotoBoardAddCommand(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao, PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public String getCommandName() {
    return "/photoboard/add";
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      
      txManager.beginTransaction();
      
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(Input.getStringValue(in, out, "제목? "));
      photoBoard.setLessonNo(Input.getIntValue(in, out, "수업번호? "));

      photoBoardDao.insert(photoBoard); // insert 후에는 auto increment된 PK값이 들어간다.
      out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
      out.println("파일 명 없이 엔터를 입력하면 파일 추가를 종료합니다.");
      out.flush();

      int count = 0;

      while (true) {
        String filePath = Input.getStringValue(in, out, "사진 파일?");

        if (filePath.length() == 0) {
          if (count > 0) {
            break;
          } else {
            out.println("최소 한 개의 사진을 입력해야 합나다.");
            continue;
          }
        }

        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filePath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      // DBMS쪽 담당자(스레드)에게 임시 보관된 데이터 변경결과
      // 실제 테이블에 적용할 것을 명령한다.
      txManager.commit();
      out.println("저장하였습니다.");


    } catch (Exception e) {
      try {
      // DBMS쪽 담당자(스레드)에게 임시 보관된 데이터 변경 결과를 모두 취소할 것을 명령한다.
        txManager.rollback();
      } catch (Exception e1) {
      }
      // 예외가 발생하면 DBMS의 임시 데이터베이스에 보관된 데이터 변경작업들을 모두 취소한다.
      out.println("데이터 저장에 실패했습니다!");
      System.out.println(e.getMessage());
      e.printStackTrace();
    } 

  }

}
