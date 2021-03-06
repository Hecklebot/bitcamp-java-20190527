package com.eomcs.lms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

@WebServlet("/lesson/add")
public class LessonAddCommand extends HttpServlet {
  private static final Logger logger = LogManager.getLogger(LessonAddCommand.class);

  private static final long serialVersionUID = 1L;
  private LessonDao lessonDao;

  @Override
  public void init() throws ServletException {
    ApplicationContext appCtx = 
        (ApplicationContext) getServletContext().getAttribute("iocContainer");
    lessonDao = appCtx.getBean(LessonDao.class);
  }
  
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<html><head><title>강의 등록폼</title></head>");
    out.println("<body><h1>강의 등록폼</h1>");
    out.println("<form action='/lesson/add' method='post'>");
    out.println("수업명 : <input type='text' name='title'><br/>");
    out.println("설명 : <input type='text' name='contents'><br/>");
    out.println("시작일 : <input type='text' name='startDate'><br/>");
    out.println("종료일 : <input type='text' name='endDate'><br/>");
    out.println("총수업시간 : <input type='text' name='totalHours'><br/>");
    out.println("일수업시간 : <input type='text' name='dayHours'><br/>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body></html>");
  }
  
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    try {
      Lesson lesson = new Lesson();
      lesson.setTitle(request.getParameter("title"));
      lesson.setContents(request.getParameter("contents"));
      lesson.setStartDate(Date.valueOf(request.getParameter("startDate")));
      lesson.setEndDate(Date.valueOf(request.getParameter("endDate")));
      lesson.setTotalHours(Integer.parseInt(request.getParameter("totalHours")));
      lesson.setDayHours(Integer.parseInt(request.getParameter("dayHours")));

      lessonDao.insert(lesson);
      response.sendRedirect("/lesson/list");
    } catch (Exception e) {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println("<html><head><title>강의 등록</title></head>");
      out.println("<body><h1>강의 등록</h1>");
      out.println("<p>데이터 저장에 실패했습니다!</p>");
      out.println("</body></html>");
      response.setHeader("Refresh", "1;url=/lesson/list");
      
      StringWriter strOut = new StringWriter();
      e.printStackTrace(new PrintWriter(strOut));
      logger.error(strOut.toString());
    }
  }
}












