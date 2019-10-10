package com.eomcs.lms.web.json;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.service.PhotoBoardService;
import com.eomcs.lms.web.PhotoFileWriter;

@RestController("json.PhotoBoardController")
@RequestMapping("/json/photoboard")
public class PhotoBoardController {

  @Resource
  private PhotoBoardService photoBoardService;
  @Resource
  private PhotoFileWriter photoFileWriter;

  @RequestMapping("add")
  public JsonResult add(
      HttpServletRequest request, PhotoBoard photoBoard,MultipartFile[] filePath) {
    try {
      photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));
      photoBoardService.insert(photoBoard);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("delete")
  public JsonResult delete(int no) {
    try {
      photoBoardService.delete(no);
      return new JsonResult().setState(JsonResult.SUCCESS);

    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) {
    try {
      PhotoBoard photoBoard = photoBoardService.get(no);
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(photoBoard);
      
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("list")
  public JsonResult list() {
    try {
      List<PhotoBoard> photoBoards = photoBoardService.list();
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(photoBoards);
      
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("update")
  public JsonResult update(
      HttpServletRequest request, PhotoBoard photoBoard, MultipartFile[] filePath, int no) {

    try {
      photoBoard.setFiles(photoFileWriter.getPhotoFiles(filePath));
      photoBoardService.update(photoBoard);
      return new JsonResult().setState(JsonResult.SUCCESS);

    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }
}
