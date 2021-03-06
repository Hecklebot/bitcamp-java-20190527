package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.LessonDao;

@WebServlet("/lesson/delete")
public class LessonDeleteCommand extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(LessonDeleteCommand.class);
  private static final long serialVersionUID = 1L;

  private LessonDao lessonDao;

  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    lessonDao = appCtx.getBean(LessonDao.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      if (lessonDao.delete(no) == 0) {
        throw new Exception("해당 데이터가 없습니다.");
      }
      
      response.sendRedirect("/lesson/list");
      
    } catch (Exception e) {
      request.setCharacterEncoding("UTF-8");
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>강의 삭제</title></head>");
      out.println("<body><h1>강의 삭제</h1>");
      out.println("<p>데이터 삭제에 실패했습니다!</p>");
      out.println("</body></html>");
      response.setHeader("Refresh", "1;url=/lesson/list");

      StringWriter strOut = new StringWriter();
      e.printStackTrace(new PrintWriter(strOut));
      logger.error(strOut.toString());

    }
  }
}












