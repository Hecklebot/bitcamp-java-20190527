package design_pattern.observer2.before.v4;

import java.io.Reader;

public class TextAnalyzer_practice {
  Reader in;
  
  public TextAnalyzer_practice(Reader in) {
    this.in = in;
  }
  
  public void execute() {
    try { 
      int ch;
      int count = 0;
      int totalLine = 0;
      int totalLinecomment = 0;
      boolean startLineComment = false;
      int countSlash = 0;
      boolean isEmpty = true;
      while ((ch = in.read()) != -1) {
        count++;
        if (ch == '\n') {
          totalLine++;
          isEmpty = true;
          
        } else {
          isEmpty = false;
        }
        
        if(!startLineComment) {     // 1. startLineComment가 true면
          if(ch =='/') {            // 2. 다음 글자가 /인지 확인하고
            if(countSlash == 0) {   // 3. countSlash가 0이면(첫 /이면)
              countSlash++;         // 4. countSlash++(/가 한번 나왔다고 표시)
            } else {                // 5. countSlash가 0 이상이면(/가 한번 나왔으면)
              totalLinecomment++;   // 6. "//"이니 한 줄 주석 수 +1
              countSlash = 0;       // 7. 다시 countSlash를 0으로 만듦(다음 /는 첫 /)
            }
          }
        } else if(ch == '\n') {     // 8. startLineComment가 false일 때 줄바꿈이 되면
          startLineComment = false; // 9. startLineComment를 false로 초기화(줄바꿈되어 주석이 끝이니까)
        }
        
      }
      
      if (!isEmpty) {
        totalLine++;
      }
      
      System.out.printf("총 읽은 문자 수: %d\n", count);
      System.out.printf("총 줄 수: %d\n", totalLine);
      System.out.printf("총 한 줄 주석 수: %d\n", totalLinecomment);
      
    } catch (Exception e) {
      System.out.println("분석 중 오류 발생!");
      
    } finally {
      //주의!
      //=> 자원 해제는 그 자원을 관리하는 객체가 책임져야 한다.
      //=> TextAnalyzer는 단지 Reader 자원을 생성자에서 받아서 
      //   execute()에서 사용할 뿐이다.
      //=> 따라서 다음과 같이 사용이 끝났다고 해서
      //   여기서 자원을 해제해서는 안된다.
      //=> 이 객체에 자원을 넘겨준 놈이 자원 해제의 책임을 져야 한다.
      //try {in.close();} catch (Exception e) {}
    }
    
  }
  
}