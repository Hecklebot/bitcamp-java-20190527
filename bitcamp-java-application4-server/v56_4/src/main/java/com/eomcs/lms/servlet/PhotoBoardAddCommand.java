package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;

@WebServlet("/photoboard/add")
public class PhotoBoardAddCommand extends HttpServlet{
  
  private static final long serialVersionUID = 1L;
  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;
  
  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    photoBoardDao = appCtx.getBean(PhotoBoardDao.class);
    photoFileDao = appCtx.getBean(PhotoFileDao.class);
  }

  @Transactional
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>회원 등록폼</title>"
        + "<link rel=\'stylesheet\' href=\'https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\'integrity=\'sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\' crossorigin=\'anonymous\'>"
        + "<link rel='stylesheet' href='/css/common.css'>"
        + "</head>");
    out.println("<body>");
    request.getRequestDispatcher("/header").include(request, response);
    out.println("<div id='content'>");
    out.println("<body><h1>회원 등록폼</h1>");
    out.println("<form action='/photoboard/add' method='post'>");
    out.println("제목 : <input type='text' name='title'></textarea><br/>");
    out.println("수업번호 : <input type='text' name='lessonNo'></textarea><br/>");
    out.println("사진1 : <input type='text' name='filePath1'></textarea><br/>");
    out.println("사진2 : <input type='text' name='filePath2'></textarea><br/>");
    out.println("사진3 : <input type='text' name='filePath3'></textarea><br/>");
    out.println("사진4 : <input type='text' name='filePath4'></textarea><br/>");
    out.println("사진5 : <input type='text' name='filePath5'></textarea><br/>");
    out.println("사진6 : <input type='text' name='filePath6'></textarea><br/>");
    out.println("<button>등록</button>");
    out.println("</form>");
    request.getRequestDispatcher("/footer").include(request, response);
    out.println("</body></html>");
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) 
      throws IOException, ServletException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setLessonNo(Integer.parseInt(request.getParameter("lessonNo")));

      photoBoardDao.insert(photoBoard); // insert 후에는 auto increment된 PK값이 들어간다.

      int count = 0;

      for(int i=1;i<=6;i++) {
        String filePath = request.getParameter("filePath"+i);
        if (filePath.length() == 0) {
          continue;
        }
        PhotoFile photoFile = new PhotoFile();
        photoFile.setFilePath(filePath);
        photoFile.setBoardNo(photoBoard.getNo());
        photoFileDao.insert(photoFile);
        count++;
      }
      if(count==0) {
        out.println("<p>반드시 한 개 이상의 사진을 등록해야 합니다.</p>");
      }
      response.sendRedirect("/photoboard/list");

    } catch (Exception e) {
      request.setAttribute("message", "데이터 저장에 실패했습니다!");
      request.setAttribute("refresh", "/photoboard/list");
      request.setAttribute("error", e);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
