// v61: Back-end와 Front-end 분리하기
package com.eomcs.lms;

// 작업1: 게시물 관리
// => /webapp/html/board-1/list.html 생성
// => /webapp/html/board-1/form.html 생성
//
// 작업2: npc(node package manager)으로 외부 CSS, JS 라이브러리 파일 관리하기
// => 콘솔에서 /webapp 폴더로 이동
// => 'npm init' 실행
//  - package.json 파일 생성
//  - 자바스크립트 라이버러리에 대한 설정 파일
// => 'npm install bootstrap'
//  - npm을 통해 외부 라이브러리를 다운로드 받는다.
//  - npm을 실행한 폴더에 node_modules 디렉토리를 생성하고
//    이 디렉토리에 라이브러리를 다운로드 한다.
//  - packagr.json에 라이브러리 정보가 등록된다.
//    만약 등록되지 않았다면 --save 옵션을 붙여 실행하라
//      - 'npm install bootstrap --save'
//      - 'npm install --save bootstrap' 둘 다 가능
//  - package.json 파일에 등록된 라이브러리를 모두 자동으로 다운로드 하고 싶다면,
//    다음과 같이 패키지 이름을 지정하지 않고 실행하라
//      - 'npm install'
//
// => /webapp/html/board-2/list.html 변경
//  - css, javascript 파일 경로를 npm 폴더 경로로 변경한다.
//
// dummy 클래스!
// => 기존 버전에서 계속 존재했던 클래스라서 그대로 둠.
// => 단지 버전의 목표에 대한 설명을 기록하기 위해 존재함.
// => 프로젝트에서 사용되지 않음!
//
public class App {
}







