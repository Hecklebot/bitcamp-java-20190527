package com.eomcs.lms.service;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

// 역할:
// => 게시물 관리 업무를 수행
// => 트랜잭션 제어
// => 여러 페이지 컨트롤러가 사용한다.
public interface PhotoBoardService {
  List<PhotoBoard> list() throws Exception;
  PhotoBoard get(int no) throws Exception;
  void insert(PhotoBoard photoboard) throws Exception;
  void update(PhotoBoard photoboard) throws Exception;
  void delete(int no) throws Exception;
}
