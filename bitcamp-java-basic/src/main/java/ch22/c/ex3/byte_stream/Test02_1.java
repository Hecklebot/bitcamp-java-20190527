// DataOutputStream에 버퍼 기능 추가해 DataOutputStream2 만들기
package ch22.c.ex3.byte_stream;

public class Test02_1 {
  public static void main(String[] args) throws Exception {

    DataOutputStream2 out = new DataOutputStream2("temp/data.bin");

    short s = 0x1122;
    int i = 0x33445566;
    long l = 0x1122334455667788L;
    String str = "ABC가각간";
    boolean b = true;
    System.out.println("출력 시작");
    long start = System.currentTimeMillis();
    
    for (int cnt = 0; cnt < 100000; cnt++) {
      out.writeShort(s);
      out.writeInt(i);
      out.writeLong(l);
      out.writeUTF(str);
      out.writeBoolean(b);
    }

    long end = System.currentTimeMillis();
    
    System.out.println(end - start);

    out.close();
    System.out.println("출력 완료!");
  }
}


