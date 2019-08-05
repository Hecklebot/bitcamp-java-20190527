package design_pattern.observer2.after.v4_02;

public class Print01Listener implements CharacterListener {
  
  int a;
  String str;
  
  // readed는 이 클래스가 수행할 작업
  @Override
  public void readed(int ch) {
    str+="01";
  }

  @Override
  public void displayResult() {
    System.out.println(str);
  }
}
