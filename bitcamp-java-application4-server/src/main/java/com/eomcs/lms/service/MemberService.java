package com.eomcs.lms.service;

import java.util.List;
import java.util.Map;
import com.eomcs.lms.domain.Member;

// 역할:
// => 게시물 관리 업무를 수행
// => 트랜잭션 제어
// => 여러 페이지 컨트롤러가 사용한다.
public interface MemberService {
  List<Member> list() throws Exception;
  Member get(int no) throws Exception;
  void insert(Member member) throws Exception;
  void update(Member member) throws Exception;
  void delete(int no) throws Exception;
  List<Member> search(String keyword) throws Exception;
  Member login(Map<String,Object> params) throws Exception;
}
