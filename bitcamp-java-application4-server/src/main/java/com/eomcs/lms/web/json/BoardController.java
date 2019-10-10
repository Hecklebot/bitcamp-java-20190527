package com.eomcs.lms.web.json;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.service.BoardService;

// @RestController
// => request handler의 리턴 값이 응답 데이터임을 선언한다.
// => 리턴 값은 내부에 설정된 HttpMessageConverter에 의해 JSON 문자열로 변환되어 보낸다.
@RestController("json.BoardController")
@RequestMapping("/json/board")
public class BoardController {

  @Resource
  private BoardService boardService;

  @PostMapping("add")
  public JsonResult add(Board board) {
    try {
      boardService.insert(board);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("delete")
  public JsonResult delete(int no) {
    try {
      boardService.delete(no);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("detail")
  public JsonResult detail(int no) {
    try {
      Board board = boardService.get(no);
      if (board == null) {
        throw new Exception("해당 번호의 데이터가 없습니다!");
      } 
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(board);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("list")
  public JsonResult list() 
      throws Exception {
    try {
    List<Board> boards = boardService.list();
      return new JsonResult().setState(JsonResult.SUCCESS).setResult(boards);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }

  @RequestMapping("update")
  public JsonResult update(Board board) {

    try {
      boardService.update(board);
      return new JsonResult().setState(JsonResult.SUCCESS);
    } catch (Exception e) {
      return new JsonResult().setState(JsonResult.FAILURE).setMessage(e.getMessage());
    }
  }
}
