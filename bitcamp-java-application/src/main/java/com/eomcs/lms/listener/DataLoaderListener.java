package com.eomcs.lms.listener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import com.eomcs.lms.context.ApplicationContextListener;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.lms.domain.Member;

// 애플리케이션이 시작되거나 종료될 때 보고를 받는 옵저버이다.
// 옵저버의 역할을 수행하려면 옵저버 수행규칙에 따라 작성해야 한다. 즉, ApplicationContextListener를 구현해야 한다.
public class DataLoaderListener implements ApplicationContextListener{
  
  ArrayList<Lesson> lessonList = new ArrayList<>();
  LinkedList<Member> memberList = new LinkedList<>();
  ArrayList<Board> boardList = new ArrayList<>();

  // 애플리케이션이 시작될 때 수업관리 데이터를 로딩한다.
  @Override
  public void contextInitialized(Map<String, Object> beanContainer) {
    loadLessonData();
    loadMemberData();
    loadBoardData();

    // 옵저버에서 준비한 객체를 App 클래스에서 사용할 수 있도록 beanContainer에 저장한다.
    beanContainer.put("lessonList", lessonList);
    beanContainer.put("memberList", memberList);
    beanContainer.put("boardList", boardList);
  }
  
  // 애플리케이션이 종료될 때 수업과리 데이터를 저장한다.
  @Override
  public void contextDestroyed(Map<String, Object> beanContainer) {
    saveLessonData();
    saveMemberData();
    saveBoardData();
  }
  
  @SuppressWarnings("unchecked")
  private void loadLessonData() {
    File file = new File("./lesson.ser");

    // 바이트 단위로 출력된 데이터를 읽을 객체를 준비한다.

    FileInputStream in = null;
    ObjectInputStream in2 = null;
    try {
      in = new FileInputStream(file);

      // 바이트 배열을 읽어 객체로 복원해주는 객체 준비
      in2 = new ObjectInputStream(in);

      lessonList = (ArrayList<Lesson>) in2.readObject();

    } catch (FileNotFoundException e) {
      // 읽을 파일을 찾지 못할 때
      System.out.println("파일을 찾을 수 없습니다.");

    } catch (Exception e) {
      // FileNotException 외의 다른 예외를 여기서 처리한다.
      System.out.println("파일을 읽는 중 오류가 발생했습니다.");
    } finally {
      try {
        in2.close();
      } catch (Exception e) {
      }

      try {
        in.close();
      } catch (Exception e) {
      }
    }

  }

  private void saveLessonData() {
    File file = new File("./lesson.ser");

    // 객체를 통째로 바이트 배열로 변환하여 바이트 스트림에 출력하기
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      out = new FileOutputStream(file);

      // 객체를 통째로 바이트 배열로 변환해주는 출력스트림 준비하기
      out2 = new ObjectOutputStream(out);

      // lessonList를 통째로 출력하기
      out2.writeObject(lessonList);

    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때, JVM을 멈추지 않게하고 메세지를 출력한다.
      System.out.println("파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("파일의 데이터를 출력하는 중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out2.close();
      } catch (Exception e) {
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void loadMemberData() {
    File file = new File("./member.ser");
    // 파일 정보를 바탕으로, 데이터를 입력해주는 객체 준비
    FileInputStream in = null;
    ObjectInputStream in2 = null;

    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);

      memberList = (LinkedList<Member>) in2.readObject();

    } catch (FileNotFoundException e) {
      // 읽을 파일을 찾지 못할 때
      System.out.println("파일을 찾을 수 없습니다.");

    } catch (Exception e) {
      // FileNotException 외의 다른 예외를 여기서 처리한다.
      System.out.println("파일을 읽는 중 오류가 발생했습니다.");
      e.printStackTrace();

    } finally {
      try {
        in2.close();
      } catch (Exception e) {
      }

      try {
        in.close();
      } catch (Exception e) {
      }
    }

  }

  private void saveMemberData() {

    // File의 정보를 준비한다.
    File file = new File("./member.ser");
    // 파일 정보를 바탕으로, 데이터를 출력해주는 객체 준비
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      out2.writeObject(memberList);

    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때, JVM을 멈추지 않게하고 메세지를 출력한다.
      System.out.println("파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("파일의 데이터를 출력하는 중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }

      try {
        out.close();
      } catch (Exception e) {
      }
    }
  }

  @SuppressWarnings("unchecked")
  private void loadBoardData() {
    File file = new File("./board.ser");
    // 파일 정보를 바탕으로, 데이터를 입력해주는 객체 준비
    FileInputStream in = null;
    ObjectInputStream in2 = null;

    try {
      in = new FileInputStream(file);
      in2 = new ObjectInputStream(in);

      boardList = (ArrayList<Board>) in2.readObject();


    } catch (FileNotFoundException e) {
      // 읽을 파일을 찾지 못할 때
      System.out.println("파일을 찾을 수 없습니다.");

    } catch (Exception e) {
      // FileNotException 외의 다른 예외를 여기서 처리한다.
      System.out.println("파일을 읽는 중 오류가 발생했습니다.");
      e.printStackTrace();

    } finally {
      try {
        in2.close();
      } catch (Exception e) {
        // close 하다 오류가 발생하면 무시한다.
      }

      try {
        in.close();
      } catch (Exception e) {
        // close 하다 오류가 발생하면 무시한다.
      }
    }

  }

  private void saveBoardData() {

    // File의 정보를 준비한다.
    File file = new File("./board.ser");
    // 파일 정보를 바탕으로, 데이터를 출력해주는 객체 준비
    FileOutputStream out = null;
    ObjectOutputStream out2 = null;

    try {
      out = new FileOutputStream(file);
      out2 = new ObjectOutputStream(out);

      out2.writeObject(boardList);

    } catch (FileNotFoundException e) {
      // 출력할 파일을 생성하지 못할 때, JVM을 멈추지 않게하고 메세지를 출력한다.
      System.out.println("파일을 생성할 수 없습니다!");
    } catch (IOException e) {
      System.out.println("파일의 데이터를 출력하는 중 오류 발생!");
      e.printStackTrace();
    } finally {
      try {
        out2.close();
      } catch (Exception e) {
      }
      try {
        out.close();
      } catch (Exception e) {
      }

    }
  }

  
}