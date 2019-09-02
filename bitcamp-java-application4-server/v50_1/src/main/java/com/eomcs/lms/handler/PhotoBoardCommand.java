package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import org.springframework.stereotype.Component;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;
import com.eomcs.util.PlatformTransactionManager;
import com.eomcs.util.RequestMapping;

@Component
public class PhotoBoardCommand {


  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  private PlatformTransactionManager txManager;

  public PhotoBoardCommand(
      PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao, PlatformTransactionManager txManager) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
    this.txManager = txManager;
  }


  @RequestMapping("/photoboard/add")
  public void add(BufferedReader in, PrintStream out) {
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

  @RequestMapping("/photoboard/delete")
  public void delete(BufferedReader in, PrintStream out) {
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

  @RequestMapping("/photoboard/detail")
  public void detail(BufferedReader in, PrintStream out) {
    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      PhotoBoard photoBoard = photoBoardDao.findWithFilesBy(no);

      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      photoBoardDao.increaseViewCount(no);
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("작성일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());
      out.printf("수업: %s\n", photoBoard.getLessonNo());
      out.println("사진 파일:");
      
      List<PhotoFile> files = photoBoard.getFiles();
      for(PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }
      
      
    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/photoboard/list")
  public void list(BufferedReader in, PrintStream out) {
    try {
      List<PhotoBoard> photoBoards = photoBoardDao.findAll();
      for (PhotoBoard photoBoard : photoBoards) {
        out.printf("%d, %s, %s, %d, %d\n", 
            photoBoard.getNo(), photoBoard.getTitle(), 
            photoBoard.getCreatedDate(), photoBoard.getViewCount(), photoBoard.getLessonNo());
      }
      
    } catch (Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

  @RequestMapping("/photoboard/update")
  public void update(BufferedReader in, PrintStream out) {
    try {
      txManager.beginTransaction();
      int no = Input.getIntValue(in, out, "번호? ");

      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      out.println("제목을 입력하지 않으면 이전 제목을 유지합니다.");
      String str = Input.getStringValue(in, out, String.format("제목(%s)? ", photoBoard.getTitle()));

      // 제목을 입력했으면 사진 게시글의 제목을 변경한다.
      if (str.length() > 0) {
        photoBoard.setTitle(str);
        photoBoardDao.update(photoBoard);
        out.println("게시물의 제목을 변경하였습니다.");
      }

      // 이전에 입력한 파일 목록을 출력한다.
      out.println("사진 파일:");
      List<PhotoFile> files = photoFileDao.findAll(no);
      for (PhotoFile file : files) {
        out.printf("> %s\n", file.getFilePath());
      }

      // 파일을 변경할 지 여부를 묻는다.
      out.println("사진은 일부만 변경할 수 없습니다.");
      out.println("전체를 새로 등록해야 합니다.");
      String response = Input.getStringValue(in, out, "사진을 변경하시겠습니까? (Y/N)");

      if (!response.equalsIgnoreCase("Y")) {
        txManager.commit();
        out.println("파일 변경을 취소합니다.");
        return;
      }
      // 기존 사진 파일을 삭제한다.
      photoFileDao.deleteAll(no);

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
      txManager.commit();
      out.println("사진을 변경하였습니다.");

    } catch (Exception e) {
      try {
        txManager.rollback();
      } catch (Exception e1) {
      }
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    } 
  }

}
