//캡슐화(Encapsulation) 적용 전

package ch12.d;

public class Test01 {
  
  public static void main(String[] args) {
    
    Patient p = new Patient();
    p.name = "김영희";
    p.age = 20;
    p.weight = 60;
    p.height = 157;
    p.gender = Patient.FEMALE;
    
    System.out.println(p);
    
    //환자를 컴퓨터에서 다루려면 데이터화 해야한다.
    //Patient는 그런 목적으로 정의한 클래스이다.
    //Patient의 경우처럼 컴퓨터에서 다루기 위해 데이터화하여 정의하는 것을 "추상화"라 부른다.
    //꼭 데이터만 해당하는 것은 아니다. MemberHandler 클래스의 경우처럼 특정 업무를 정의하는 것도 "추상화"라 부른다.
    //현실의 객체를 컴퓨터에서 다룰 수 있도록 클래스로 정의하는 과정을 "추상화"라 부른다.
    Patient p2 = new Patient();
    p2.name = "이철희";
    p2.age = 300;
    p2.weight = -50;
    p2.height = 400;
    p2.gender = Patient.MALE;
    
    System.out.println(p2);
    
    //나이가 300이면 환자가 아니고 괴물이다.
    //몸무게가 -50kg면 이 세상 몸무게가 아니다.
    //키가 4m면 부럽다.
    //즉, Patient 클래스는 환자의 데이터를 저장할 목적으로 정의한 클래스인데, 환자의 데이터와 무관한 데이터를 저장하고 있다.
    //차라리 클래스 이름을 monster로 변경하는 것이 바람직하다.
    //이렇게 클래스 목적에 부합하지 않는 데이터를 입력될 수 있다면, 추상화가 무너지게 된다.
    //이를 방지하기 위해서는 추상화 목적에 맞춰 인스턴스 변수에 무효한 값이 들어가지 않도록 해야한다.
    //그럴 목적으로 만든 문법이 캡슐화이다.
    //"캡슐화"란 추상화가 무너지지 않도록, 인스턴스에 접근을 제어하는 문법이다.
    //추상화 기법
    // -데이터 타입을 정의
    // -유관 메서드를 묶기
  }
}
