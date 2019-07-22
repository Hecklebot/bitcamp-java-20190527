package ch18.g3;

import ch18.g2.Printer;

//신규 프로젝트에서는 워터마킹 기능을 갖는 프린터가 필요하다.
// -> 기존 인터페이스인 Printer에 워터마킹 기능을 추가하게 되면, 기존에 구현한 프린터들에도 워터마킹 기능을 추가해야 한다.
// -> 구현하지 않으면 컴파일 에러가 발생한다. 이를 해결하는 전통적인 방식은 다음과 같이 새 기능을 추가한 인터페이스를 새로 만드는 것이다.
public interface Printer2 extends Printer {
  //기존 Printer의 기능은 Printer 인터페이스에서 상속받아 그대로 사용한다.
  //그리고 다음과 같이 새 규칙을 추가한다.
  void watermark(String title);
}
