// v2(유관순, 8월 15일에 추가함): mainClassName 값을 알아내기
package design_pattern.observer2.after.v4_02;

import java.io.FileReader;

public class Test {
  public static void main(String[] args) {
    try(FileReader in = new FileReader("build.gradle")){
      TextAnalyzer textAnalyzer = new TextAnalyzer(in);
      CharacterCountListener l1 = new CharacterCountListener();
      textAnalyzer.addCharacterListener(l1);
      LineCountListener l2 = new LineCountListener();
      textAnalyzer.addCharacterListener(l2);
      LineCommentListener l3 = new LineCommentListener();
      textAnalyzer.addCharacterListener(l3);
      MainClassNameListener l4 = new MainClassNameListener();
      textAnalyzer.addCharacterListener(l4);
      Print01Listener l5 = new Print01Listener();
      textAnalyzer.addCharacterListener(l5);
      
      
      textAnalyzer.execute();
      
      l1.displayResult();
      l2.displayResult();
      l3.displayResult();
      l4.displayResult();
      l5.displayResult();
      
      
      
    } catch(Exception e) {
      System.out.println("error");
    }
    
  }
}
