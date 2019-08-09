// java.sql.Connection 객체
package ch25.a;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test05 {

  public static void main(String[] args) {
    // DriverManager.getConnetion()
    // => org.mariadb.jdbc.Driver.connect()
    //
    // 이렇게 클래스 이름을 적고 선언하면 mariaDB 클래스에 종속된다. 그러면 다른 DB로 바꾸는 게 힘들다.
    // -> 그러니 주소를 문자열로 받아 연결하는 게 낫다.
    // Connection con2 = new org.mariadb.jdbc.Driver().connect("",null); 
    try (Connection con = DriverManager
        .getConnection("jdbc:mariadb://localhost/bitcampdb?user=bitcamp&password=1111")) {
      // "jdbc:mariadb://localhost/bitcampdb", "bitcamp", "1111" 과 같다.
      System.out.println("DBMS에 연결됨!");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}


