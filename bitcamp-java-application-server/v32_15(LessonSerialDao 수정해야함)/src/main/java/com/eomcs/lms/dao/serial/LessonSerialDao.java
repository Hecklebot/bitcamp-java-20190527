package com.eomcs.lms.dao.serial;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonSerialDao implements LessonDao {
  ArrayList<Lesson> list = new ArrayList<>();
  File file;

  public LessonSerialDao(String file) throws Exception {
    this.file = new File(file);

    try {
      loadData();
    } catch (IOException e) {
      System.out.println("강의정보 데이터 로딩 중 오류 발생");
    }
  }

  @SuppressWarnings("unchecked")
  private void loadData() throws IOException, ClassNotFoundException {
    File file = new File("./lesson.ser");

    try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
      list = (ArrayList<Lesson>) in.readObject();
      System.out.println("강의 데이터 로딩 완료");
    }
  }

  public void saveData() throws IOException {
    File file = new File("./lesson.ser");

    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));) {
      out.writeObject(list);
      System.out.println("강의 데이터 저장 완료");

    } catch (FileNotFoundException e) {
      System.out.println("파일을 생성할 수 없습니다.");

    } catch (IOException e) {
      System.out.println("파일에 데이터를 출력하는 중 오류발생");
      e.printStackTrace();
    }
  }

  @Override
  public int insert(Lesson lesson) throws Exception {
    list.add(lesson);
    return 1;
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    return list;
  }

  @Override
  public Lesson findBy(int no) throws Exception {
    int index = indexOf(no);

    if (indexOf(index) == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    int index = indexOf(lesson.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, lesson);
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;
    }
    list.remove(no);
    return 1;
  }

  private int indexOf(int no) {
    int i = 0;
    for (Lesson b : list) {
      if (b.getNo() == no) {
        return i;
      }
      i++;
    }
    return -1;
  }



}
