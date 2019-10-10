package com.eomcs.lms.web;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;

@Controller
@RequestMapping("/photoboard")
public class PhotoBoardController {

  @Resource
  private PhotoBoardService photoBoardService;
  @Resource
  private PhotoFileWriter photoFileWriter;

  @RequestMapping("form")
  public void form() {}

  @RequestMapping("add")
  public String add(HttpServletRequest request, PhotoBoard photoBoard, MultipartFile[] filePath)
      throws Exception {

    // 사진 파일 정보를 사진 게시물에 담는다.
    photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));

    // 서비스 컴포넌트를 통해 데이터를 저장한다.
    photoBoardService.insert(photoBoard);
    return "redirect:list";
  }

  @RequestMapping("delete")
  public String delete(int no) throws Exception {
    photoBoardService.delete(no);
    return "redirect:list";
  }

  @RequestMapping("list")
  public void list(Model model) throws Exception {
    List<PhotoBoard> photoBoards = photoBoardService.list();
    model.addAttribute("photoBoards", photoBoards);
  }

  @RequestMapping("detail")
  public void detail(Model model, int no) throws Exception {
    PhotoBoard photoBoard = photoBoardService.get(no);
    model.addAttribute("photoBoard", photoBoard);
  }

  @PostMapping("update")
  public String update(HttpServletRequest request, PhotoBoard photoBoard, MultipartFile[] filePath,
      int no) throws Exception {
    // 사진 파일 정보를 사진 게시물에 담는다.
    photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));

    // 서비스 컴포넌트를 통해 데이터를 저장한다.
    photoBoardService.update(photoBoard);
    return "redirect:list";
  }

}


