package ch12.d;

public class Patient3 {
  public static final int FEMALE = 1;
  public static final int MALE = 2;

  private String name;
  private int age;
  private int height;
  private int weight;
  private int gender;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public int getAge() {
    return this.age;
  }
  
  public void setAge(int age) {
    if(age >= 1 && age <= 100) {
      this.age = age;
    }else {
      throw new RuntimeException("나이 값이 유효하지 않습니다.");
    }
  }
  
  public int getHeight() {
    return this.height;
  }
  
  public void setHeight(int height) {
    if(height >= 1 && height <= 300) {
      this.height = height;
    }else {
      throw new RuntimeException("키가 유효하지 않습니다.");
    }
  }
  
  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    if(weight > 0 && weight <= 500) {
      this.weight = weight;
    }else {
      throw new RuntimeException("몸무게가 유효하지 않습니다.");
    }
  }

  public int getGender() {
    return gender;
  }

  public void setGender(int gender) {
    if(gender > 0 && gender < 3) {
      this.gender = gender;
    }else {
      throw new RuntimeException("성별이 유효하지 않습니다.");
    }
  }

  public String toString() { //Test1의 p주소는 이 메소드의 this 변수에 자동으로 저장된다.
    return String.format("name: %s, age: %s, height: %s, weight: %s, gender: %s", this.name, 
        this.age, this.height, this.weight, this.gender);
  }
  
}
