// 상속과 메서드 호출

package ch13.c;

public class Test2 {

  public static void main(String[] args) {
    D obj = new D();
    
    obj.m4(); //obj 레퍼런스의 클래스에서 m4()를 찾아 호출한다.
    obj.m3(); //obj 레퍼런스의  클래스(D)에서 m3()찾아보고 없다면 슈퍼 클래스에서 찾는다.
    obj.m2(); //만약 D의 슈퍼 클래스에서도 못 찾는다면 D의 슈퍼 클래스의 슈퍼 클래스에서 찾는다.
    obj.m1(); //만약 그 슈퍼 클래스에서도 못 찾는다면 슈퍼 클래스의 슈퍼 클래스의 슈퍼 클래스에서 찾는다.
    
    //
  }

}
