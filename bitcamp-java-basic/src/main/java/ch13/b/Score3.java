package ch13.b;

import ch13.Score;

// 기존의 Score 클래스를 상속 받는다.
// => 상속 받은 메서드 중에서 변경할 게 있으면 재정의 한다.
// => 이것을 "오버라이딩(overriding)"이라 부른다.
// => 예) compute()


// 오버라이딩?
// -> 상속받은 메서드를 서브 클래스의 역할에 맞게 수정하는 것
// -> 반드시 원래의 메서드 시그니쳐 (Method signature)가 일치해야 한다.
// -> Method Signature? -> 이름과 리턴타입, 파라미터 선언(C에선 "function prototype"라 한다.)
public class Score3 extends Score {

  private int music;
  private int art;
  
  public int getMusic() {
    return this.music;
  }
  public void setMusic(int music) {
    if (music >= 0 && music <= 100) {// 유효한 점수인 경우에만 저장한다.
      this.music = music;
      this.compute(); // 이 클래스에 새로 추가한 계산 메서드를 호출한다.
    }
  }
  
  public int getArt() {
    return this.art;
  }
  public void setArt(int art) {
    if (art >= 0 && art <= 100) {// 유효한 점수인 경우에만 저장한다.
      this.art = art;
      this.compute(); // 이 클래스에 새로 추가한 계산 메서드를 호출한다.
    }
  }
  
  // 수퍼 클래스의 메서드 중에서 서브 클래스의 역할과 맞지 않는 경우 
  // 서브 클래스의 역할에 맞춰서 재정의 해도 된다.
  
  protected void compute() {
    System.out.println("Score3.compute() 호출됨");
    
    this.sum = this.kor + this.math + this.eng + this.music + this.art;
    this.aver = this.sum / 5f;
  } 
  
  /*
  @Override
  protected void compute() {
    System.out.println("Score3.compute() 호출됨");
    // 기존 계산 메서드를 호출해서 일단 국,영,수 세 과목의 합계와 평균을 계산한다.
    // => 재정의 하기 전의 메서드를 호출할 때는 super 이용한다.
    super.compute();
    
    this.sum += this.music + this.art;
    this.aver = this.sum / 5f;
  }
  */
}







