package ch28.c;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// 애노테이션 사용 범위
// 여러 타입에 사용하고 싶으면 배열로 표현
@Target({ElementType.LOCAL_VARIABLE,ElementType.METHOD}) // 로컬변수 선언에 붙일 수 있다.
public @interface MyAnnotation5 {

}
