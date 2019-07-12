package ch12.d;

public class Patient {
  public static final int FEMALE = 1;
  public static final int MALE = 2;

  String name;
  int age;
  int height;
  int weight;
  int gender;
  
  public String toString() { //Test1의 p주소는 이 메소드의 this 변수에 자동으로 저장된다.
    return String.format("name: %s, age: %s, height: %s, weight: %s, gender: %s", this.name, 
        this.age, this.height, this.weight, this.gender);
  }
  
}
