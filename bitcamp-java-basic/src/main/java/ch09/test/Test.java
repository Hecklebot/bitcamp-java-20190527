package ch09.test;

public class Test {

  public static void main(String[] args) {
    //계산하기
    // -> 2 * 3 - 2 + 7 = ?

    //개별적으로 관리되어야 하는 값은 인스턴스에 저장한다.
    
    //계산식 1의 결과를 저장할 메모리 생성
    Calculator calc1 = new Calculator();
    
    //계산식 2의 결과를 저장할 메모리 생성
    Calculator calc2 = new Calculator();
    
    
    //클래스 메소드로 파라미터를 매번 주는 것 보다 인스턴스 메소드로 만들어 인스턴스를 활용하면 편-안
    calc1.plus(2);
    calc2.plus(7);
    
    calc1.multiple(3);
    calc2.divide(2);
    
    calc1.minus(2);
    calc2.plus(4);
    
    calc1.plus(7);
    
    System.out.printf("계산식 1의 결과: %d\n", calc1.result);
    System.out.printf("계산식 2의 결과: %d\n", calc2.result);

  }

}
