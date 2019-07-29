package ch22.c.ex4;

import java.io.IOException;
import java.io.OutputStream;

public abstract class DecoratorOutputStream extends OutputStream {
  
  // 데코레이터 역할을 수행하는 객체는 항상 다른 InputStream을 포함해야 한다.
  // 즉, 다른 InputStream 객체의 기능을 붙일 것이기 때문이다.
  OutputStream other;
  
  protected DecoratorOutputStream (OutputStream other) {
    this.other = other;
  }

  @Override
  public void flush() throws IOException {
    other.flush();
  }
  
}
