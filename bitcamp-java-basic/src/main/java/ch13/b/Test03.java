// 기능 확장하기 - Score 클래스를 상속 받아 기능 추가하기 II
package ch13.b;

import ch13.Score;

public class Test03 {
  public static void main(String[] args) {
    
    // Score3를 이용하여 수퍼 클래스의 기능을 호출할 수 있다.
    // => Score2나 다를 바가 없다. 다만 Score3는 compute()를 오버라이딩 했다.
    Score3 s = new Score3();
    s.setName("홍길동"); // Score의 메서드
    s.setKor(100); // Score의 메서드
    s.setEng(100); // Score의 메서드
    s.setMath(100); // Score의 메서드
    s.setMusic(100); // Score3의 메서드
    s.setArt(100); // Score3의 메서드
    System.out.printf("%s: %d(%.1f)\n", s.getName(), s.getSum(), s.getAver());
    
    System.out.println("=================================================");
    
    Score s2 = new Score();
    s2.setName("임꺽정");
    s2.setKor(90);     //Score의 Compute() 호출 -> 3과목의 합계와 평균 계산
    s2.setEng(90);     //Score의 Compute() 호출 -> 3과목의 합계와 평균 계산
    s2.setMath(90);    //Score의 Compute() 호출 -> 3과목의 합계와 평균 계산
    System.out.printf("%s: %d(%.1f)\n", s2.getName(), s2.getSum(), s2.getAver());
    
    //국 영 수 점수를 설정할 때는 Score의 compute()를 호출한다.
    //음악 미술 점수를 설정할 때는 Score2의 compute()를 호출한다.
    //따라서 음악 미술 점수를 먼저 설정한 다음 국 영 수 점수를 설정하면, 합계와 평균이 잘못 계산된다.
    
    //해결책
    //1) 국 영 수 점수를 설정하는 setter에서 compute()대신에 compute2()를 호출하도록 변경
    //2) compute()가 음악과 미술 점수를 함께 계산하도록 변경
    //  -> 기존에 작성한 클래스를 변경하는 것은 유지보수에 바람직하지 않으므로, 기존 클래스인 Score를 손대지 않는 조건으로 Score의 메서드를 변경해야 한다.
    //  -> 이를 위한 목적으로 탄생한 문법이 다형성 기법 중 오버라이딩(Overriding)이다.
  }
}
