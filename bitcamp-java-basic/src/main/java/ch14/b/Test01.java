//오버라이딩과 this/super
package ch14.b;

public class Test01 {

  public static void main(String[] args) {
    X2 obj = new X2();
    obj.test(); //this, 즉 X2의 인스턴스부터 m1을 찾아 올라간다. 찾을 때 까지 상위 클래스로 올라간다(X5..X4..X3..X2..X1)
                //super,  X2의 슈퍼클래스부터 m2을 찾아 올라간다
    
    System.out.println("=====================================");
    
    X3 obj2 = new X3();
    obj2.test(); //X2.m1(), X1.m1()
    System.out.println("=====================================");

    X4 obj3 = new X4();
    obj3.test(); //X4.m1(), X1.m1()
    System.out.println("=====================================");

    X5 obj4 = new X5();
    obj4.test(); //X4.m1(), X1.m1()
    
  }

}
