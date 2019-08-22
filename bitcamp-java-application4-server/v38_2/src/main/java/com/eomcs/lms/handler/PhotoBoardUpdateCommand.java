package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;

public class PhotoBoardUpdateCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;


  public PhotoBoardUpdateCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {

    try {
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
      out.println("사진을 변경하였습니다.");
    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
