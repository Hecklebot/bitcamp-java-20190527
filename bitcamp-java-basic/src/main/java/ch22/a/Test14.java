// java.io.File 클래스 : 연습 과제 - bin/main 폴더를 뒤져서 모든 클래스 파일의 이름을 출력하라.
package ch22.a;

import java.io.File;

public class Test14 {

  public static void main(String[] args) throws Exception {
    // 클래스 이름을 출력할 때 패키지 이름을 포함해야 한다.
    // 예) ch01.Test01
    // 예) ch22.a.Test14
    //
    File dir = new File("bin/main");

    findClass2(dir, "");
    System.out.println("완료!");
  }

  static void findClass(File path, String packageName) {
    // 1) path가 파일이면 끝이 패키지 이름과 파일 이름을 합쳐 출력한다. 단, 파일 이름이 .class면 제외하고 출력한다.
    if (path.isFile()) {
      System.out.print(
          String.format("%s.%s\n", packageName, path.getName().replace(".class", "")).substring(1));
      return;
    }

    // 2) path가 디렉토리라면 하위 디렉토리와 파일 목록을 얻는다. 단 필터를 이용해 디렉토리와 클래스 파일 목록만 추출한다.
    File[] files = path.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));

    // 3) findClass를 재귀호출해 하위 디렉토리를 검사한다.
    for (File f : files) {
      if (f.isDirectory()) {
        findClass(f, packageName + "." + f.getName());
      } else {
        findClass(f, packageName);
      }
    }

  }
  
  static void findClass2(File path, String packageName) {

    // 1) path가 파일이면 끝이 패키지 이름과 파일 이름을 합쳐 출력한다. 단, 파일 이름이 .class면 제외하고 출력한다.
    File[] files = path.listFiles(f -> f.isDirectory() || f.getName().endsWith(".class"));
    // 2) path가 디렉토리라면 하위 디렉토리와 파일 목록을 얻는다. 단 필터를 이용해 디렉토리와 클래스 파일 목록만 추출한다.
    for (File f : files) {
      if (f.isDirectory()) {
        findClass(f, packageName + "." + f.getName());
      } else {
        System.out.println(String.format(
            "%s.%s", packageName, 
            f.getName().replace(".class", ""))
              .substring(1));
      }
    }

    // 3) findClass를 재귀호출해 하위 디렉토리를 검사한다.

  
  }
  
  
}


