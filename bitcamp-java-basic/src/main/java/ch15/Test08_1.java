// hash code 응용 - HashSet과 HashCode()
package ch15;

import java.util.HashSet;

public class Test08_1 {
  
  static class Student {
    String name;
    int age;
    boolean working;
    
    public Student(String name, int age, boolean working) {
      this.name = name;
      this.age = age;
      this.working = working;
    }
  }
  
  public static void main(String[] args) {
    Student s1 = new Student("홍길동", 20, false);
    Student s2 = new Student("홍길동", 20, false);
    Student s3 = new Student("임꺽정", 21, true);
    Student s4 = new Student("유관순", 22, true);
    
    System.out.println(s1 == s2);
    System.out.println(s1.hashCode());
    System.out.println(s2.hashCode());
    System.out.println(s3.hashCode());
    System.out.println(s4.hashCode());
    
    System.out.println("==================================");
    
    HashSet<Student> set = new HashSet<>();
    set.add(s1);
    set.add(s2);
    set.add(s3);
    set.add(s4);
    
    Object[] list = set.toArray();
    for(Object obj : list) {
      Student student = (Student) obj;
      System.out.printf("%s, %d, %s\n", student.name, student.age, (student.working ? "재직중" : "실직중"));
    }
    
    //현재 예제의 문제점
    // -> s1, s2는 같은 데이터를 갖고 있지만, 다른 hashcode를 리턴하기 때문에, HashSet은 s1과 s2를 다른 값으로 취급한다.
    // -> 그래서 s1과 s2 모두 HashSet에 보관된 것이다.
    //
    //해결책?
    //Student 클래스에서 hashCode()를 오버라이딩하여, 같은 데이터에 대해 같은 hashcode를 리턴하게 만든다.
  }

}







