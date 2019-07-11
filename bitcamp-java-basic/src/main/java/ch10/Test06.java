// 인스턴스 필드의 초기화 - 생성자를 통해 필드를 초기화 하기 
package ch10;

class Monitor4 {
  int bright = 10; // 밝기 (0% ~ 100%)
  int contrast = 10; // 명암 (0% ~ 100%)
  int widthRes = 480; // 해상도 너비
  int heightRes = 320; // 해상도 높이
  
  {
    System.out.println("인스턴스 초기화 블럭 실행");
    bright = 20;
    contrast = 20;
    widthRes = 800;
    heightRes = 600;
  }
  //호출될 생성자는 new 명령에서 지정한다.
  //    ex) new ClassName(parameter a, parameter b)
  //->파라미터의 값을 주지 않으면 파라미터 없는 생성자가 호출된다.
  //    ex) Monitor4() -> 파라미터 값을 안 받는 생성자를 defalut constructor라고 부른다.
  //-> new 명령을 실행하여 인스턴스를 생성할 때는 반드시 호출될 생성자를 지정해야 한다.
  //    ex) new ClassName();
  //-> 다음과 같이 생성자를 지정하지 않으면 컴파일 오류가 발생한다.
  //    ex) new ClassName;
  Monitor4() {
    System.out.println("생성자 실행");
    this.bright = 50;
    this.contrast = 50;
    this.widthRes = 1920;
    this.heightRes = 1080;
    //인스턴스 블록이 있는데 왜 굳이 생성자를 사용하는가?
    //-> 생성자는 외부에서 초기화시킬 값을 받을 수 있다.
    //다만 기본생성자는 외부로부터 값을 받지 않기 때문에 초기화 블럭과 다를 바가 없다.
    //Monitor4(int bright, int contarast, int widthRes, int height){}
  }
  
  void display() {
    System.out.println("----------------------------------");
    System.out.printf("밝기(%d)\n", this.bright);
    System.out.printf("명암(%d)\n", this.contrast);
    System.out.printf("해상도(%d x %d)\n", this.widthRes, this.heightRes);
    System.out.println("----------------------------------");
  }
}

public class Test06 {
  public static void main(String[] args) {
    // 모니터 인스턴스 생성
    //1) Monitor4 설계도에 따라 인스턴스 필드를 Heap에 생성한다.
    
    //2) 필드의 초기화 문장을 실행한다.
    //  int bright = 10;
    
    //3) 인스턴스 초기화 블록을 실행한다.
    //{
    //    System.out.println("인스턴스 초기화 블럭 실행");
    //    bright = 20;
    //    ...
    //}
    
    //4) 디폴트 생성자를 호출한다.
    //Monitor4() {
    //  System.out.println("생성자 실행");
    //  this.bright = 50;
    //  ...
    //}


    Monitor4 m1 = new Monitor4();
    
    //다음과 같이 생성자를 지정하지 않으면 컴파일 오류가 발생한다.
    //Monitor4 m2 = new Monitor4; <- 에러
    
    
    // 인스턴스 필드의 값이 생성자를 통해 유효한 기본 값들로 
    // 미리 초기화 되었기 때문에 바로 사용할 수 있다.
    m1.display(); 
    
    // 물론 특정 속성의 값을 바꾼 후에 사용해도 된다.
    m1.bright = 40;
    
    m1.display();
  }
}
















