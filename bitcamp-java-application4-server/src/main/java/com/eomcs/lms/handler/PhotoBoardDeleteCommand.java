package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;

public class PhotoBoardDeleteCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  private PlatformTransactionManager txManager;

  public PhotoBoardDeleteCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao,
      PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }

  @Override
  public String getCommandName() {
    return "/photoboard/delete";
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      txManager.beginTransaction();
      
      int no = Input.getIntValue(in, out, "번호? ");

      if (photoBoardDao.findBy(no) == null) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      // 먼저 게시물의 첨부파일을 삭제한다.
      photoFileDao.deleteAll(no);

      // 게시물을 삭제한다.
      photoBoardDao.delete(no);
      
      txManager.commit();
      out.println("데이터를 삭제하였습니다.");
      out.flush();

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
      }
      out.println("데이터 삭제에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }
}
