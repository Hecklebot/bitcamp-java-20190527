package ch11;

public class Test12 {
  int a;
  int b;
  String c;
  
  public Test12 (int a, int b, String c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
  
  public static void main(String[] args) {
    Test12 test12 = new Test12(10, 20, "ABC");
    Test12 test121 = new Test12(10, 20, "ABC");
    String str = "asd";
    
    
    System.out.println(test12 == test121);
    System.out.println(test12.equals(test121));
    System.out.println(str.equals(str));
    
  }
}
