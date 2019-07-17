// Object 클래스 - clone() 사용법 II
package ch15;
 
// clone()은 인스턴스를 복제할 때 호출하는 메서드이다.

public class Test12_2 {
  
  // 인스턴스를 복제할 때는, 
  // => Object에서 상속 받은 clone()을 호출해야 한다.
  // => 그런데 clone()의 접근 제어가 protected 이라서 
  //    같은 패키지의 멤버이거나 서브 클래스만이 호출할 수 있다.
  // => 다른 패키지의 멤버가 호출하려면 public 으로 접근 제어의 범위를 넓혀야 한다.
  // => 어떻게? 오버라이딩 하라!
  static class Score {
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;
    
    public Score() {}
    
    public Score(String name, int kor, int eng, int math) {
      this.name = name;
      this.kor = kor;
      this.eng = eng;
      this.math = math;
      this.sum = this.kor + this.eng + this.math;
      this.aver = this.sum / 3f;
    }

    @Override
    public String toString() {
      return "Score [name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
          + sum + ", aver=" + aver + "]";
    }
    
    // 자바에서 제공하는 인스턴스 복제 기능을 사용하려면,
    // => java.lang.Cloneable 인터페이스 규칙을 따라야 한다.
    // => Object에서 상속 받은 clone()을 오버라이딩하여 다른 패키지의 멤버도 사용할 수 있게 
    //    public 으로 접근 범위를 넗혀라!
    // => 오버라이딩은 접근 범위를 좁힐 수는 없지만, 넓힐 수는 있다.
    // => 오버라이딩 할 때 리턴 타입을 클래스 타입으로 변경해도 된다.
    @Override
    public Score clone() throws CloneNotSupportedException {
      // 복제를 위한 코드를 작성할 필요가 없다. JVM이 알아서 해준다. 
      // 그냥 상속 받은 메서드를 오버라이딩하고, 접근권한을 public로 확대한다.
      // 리턴 타입은 해당 클래스 이름으로 변경한다.
      return (Score) super.clone();
    }
  }
  
  public static void main(String[] args) throws Exception {
    
    Score s1 = new Score("홍길동", 100, 100, 100);
    
    // 만약 Score에 java.lang.Cloneable 인터페이스 붙이지 않았다면,
    // JVM은 다음 예외를 발생시킨다.
    // => java.lang.CloneNotSupportedException:
    // 즉 Score 클래스는 개발자가 복제를 허락하지 않았다는 뜻이다.
    // 
    Score s2 = s1.clone(); 

    //CloneNotSuportedExeption이 발생한다.
    // -> 이유: clone() 메서드의 사용을 활성화시키지 않아 예외가 발생한 것이다
    //clone()를 활성화하려면? Test12_3을 참조하라
    
    System.out.println(s1 == s2);
    System.out.println(s1);
    System.out.println(s2);
  }
}







