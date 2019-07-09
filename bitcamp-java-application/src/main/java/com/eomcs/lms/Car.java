package com.eomcs.lms;

public class Car {
  //static(class) field
  //-> 클래스가 로딩될 때 Method area에 자동 생성된다.
  //-> 클래스는 딱 한번만 로딩되기 때문에 static field도 딱 한 번만 생성된다.
  static int count;

  //instance field
  //-> new 명령을 실행할 때 heap에 생성된다.
  //-> new 명령을 실행하는 갯수만큼 instance field가 생성된다.
  int no;
  String model;
}
