// hash code 응용 II - Wrapper Class를 key 객체로 사용하기
package ch15;

import java.util.HashMap;

public class Test10_1 {
  public static void main(String[] args) {
    // hash 코드는 Map에서 값을 저장하기 위해 key로 사용한다.
    HashMap<Integer, Student> map = new HashMap<>();
    
    // Map은 값을 저장할 때 key를 이용한다.
    // => key: 값을 저장할 위치를 계산할 때 사용한다.
    //  -> key 객체의 hashCode()를 호출하여 그 리턴 값을 사용해 위치를 계산한다.
    //  -> 따라서 key객체의 hashcode가 다르면 위치도 다르다.
    // => map.put(key, value);
    
    //key로 사용할 객체를 준비한다.
    Integer k1 = new Integer(101);
    Integer k2 = new Integer(102);
    Integer k3 = new Integer(103);
    
    
//    Integer k4 = Integer.valueOf(104);
//    Integer k5 = Integer.valueOf(105);

    
    //위에서 준비한 key객체를 가지고 student객체를 보관한다.
    map.put(k1, new Student("홍길동", 20, false));
    map.put(k2, new Student("임꺽정", 30, true));
    map.put(k3, new Student("유관순", 17, true));
    
    // put(Object key, Object value)
    // => put() 메서드는 key로 넘겨받은 객체에 대해 hashCode()를 호출하여 
    //    정수 값을 얻는다.
    // => 그렇게 티런 받은 정수 값(hashcode)를 사용하여 Student 객체를 저장할 위치를 계산한다.
    // => 그런 후 그 위치에 해당하는 배열(배열로 관리한다면)에 저장한다.

    // 다음과 같이 int를 key로 사용할 수 있다.
    // => key 값으로 int를 넘겨준다? 
    //    내부적으로 auto-boxing을 수행하여 Integer 객체를 만든다.
    //    그리고 그 객체를 넘겨주는 것이다.
    map.put(104, new Student("안중근", 24, true));
    map.put(105, new Student("윤봉길", 22, false));
    
    
    //값을 저장할 때 사용한 key로 다시 객체를 꺼내보자!
    System.out.println(map.get(k2));
    
    //k2와 같은 정수값을 가지는 key를 새로 생성한다.
    Integer k6 = new Integer(102);
    
    //k2와 같은 값을 갖는 k6로 값을 꺼내보자!
    System.out.println(map.get(k6));

    //다음과 같이 k2와 k6는 다른 객체이다.
    System.out.println(k2 == k6); 
    
    //그러나 k3와 k6는 같은 해시코드를 갖는다.
    System.out.println(k2.hashCode()); // hash code는 같다.
    System.out.println(k6.hashCode()); // hash code는 같다.
    
    //또한 equals의 리턴값도 true이다.
    System.out.println(k2.equals(k6)); // equals()의 비교 결과도 같다.
    // 결론!
    // => k2와 k6는 다른 객체지만, hashCode()의 리턴값이 같고, equals()의 리턴값 역시 true이다.
    //  -> 따라서 두 객체는 같은 key로 간주된다.
    
    // get(key) 실행원리
    // => key 파라미터로 받은 객체에 대해 hashCode() 호출하여 정수 값을 얻는다.
    // => 그리고 정수 값을 이용하여 값이 저장된 위치를 찾는다.
    //    원래의 키와 내용물이 같은지 equals()로 한 번 더 비교한다.
    //    만약 같다면 같은 key로 간주하여 해당 값을 꺼내 리턴한다.
    //
    // 따라서 k2로 저장한 값을 k6로 꺼낼 수 있다.
    
    // 왜? hashCode()의 리턴 값이 같고, equals()의 리턴 값이 true이기 때문에 같은 key로 간주한다.
    // 
    //Object 클래스에서 상속받은 hashCode()는 인스턴스 필드의 값이 같은지 따지지 않고, 무조건 고유의 해시값을 리턴한다.
    //또한 equals()는 인스턴스 필드의 값이 같은지 비교하지 않고 같은 인스턴스인지만 따진다.
    //
    //그러나 Integer 등 Wrapper class와 String class는 Object class에서 상속받은 hashCode()와 equals()를 오버라이딩 했기 때문에,
    //인스턴스가 다르더라도, 인스턴스 필드의 값이 같으면 hashCode()의 리턴값이 같고, equals()가 true를 리턴한다.
    //
    //그래서 Wrapper class나 String class는 HashMap/HashSet의 key로 사용할 수 있는 것이다.
    //실무에서도 String이나 primitive type를 key로 자주 사용한다.
  }

}







