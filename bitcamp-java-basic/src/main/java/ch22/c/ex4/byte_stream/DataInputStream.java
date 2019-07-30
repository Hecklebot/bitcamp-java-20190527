package ch22.c.ex4.byte_stream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class DataInputStream extends DecoratorInputStream {

  public DataInputStream(InputStream other) throws FileNotFoundException {
    super(other);
  }

  //read()는 공통 기능이기 때문에 상위 클래스의 기능을 그대로 사용한다.
  @Override
  public int read() throws IOException {
    // TODO Auto-generated method stub
    return other.read();
  }
  
  // DataInputStream만의 기능은 따로 정의해 사용할 수 있게 한다.
  public int readInt() throws IOException {
    int value = 0;
    value |= read() << 24;
    value |= read() << 16;
    value |= read() << 8;
    value |= read();
    return value;
  }

  public short readShort() throws IOException {
    short value = 0;
    value |= read() << 8;
    value |= read();
    return value;
  }

  public long readLong() throws IOException {
    long value = 0L;
    value |= (long)read() << 56;
    value |= (long)read() << 48;
    value |= (long)read() << 40;
    value |= (long)read() << 32;
    value |= (long)read() << 24;
    value |= (long)read() << 16;
    value |= (long)read() << 8;
    value |= (long)read();
    return value;
  }

  public boolean readBoolean() throws IOException {
    return read() == 1 ? true : false;
  }

  public String readUTF() throws IOException {
    // 먼저 UTF-8 바이트의 갯수를 의미하는 2바이트를 읽는다.
    int length = readShort();

    // 해당 갯수만큼 바이트 배열을 만든다.
    byte[] bytes = new byte[length];

    // 준비한 바이트 배열에 파일 데이터를 읽어온다.
    read(bytes);
    
    // 읽어 온 바이트 배열을 가지고 String 객체를 만든다.
    return new String(bytes, "UTF-8");
  }

}
