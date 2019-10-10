package com.eomcs.lms.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.lms.service.MemberService;

// MemberService 구현체
@Service
public class DefaultMemberService implements MemberService {

  @Resource
  private MemberDao memberDao;

  @Override
  public void insert(Member member) throws Exception {
    
    memberDao.insert(member);
  }

  @Override
  public void delete(int no) throws Exception {
    if (memberDao.delete(no) == 0) {
      throw new Exception("해당 데이터가 없습니다.");
    }
  }

  @Override
  public Member get(int no) throws Exception {
    Member member = memberDao.findBy(no);
    if (member == null) {
      throw new Exception("해당 번호의 데이터가 없습니다!");
    } 
    return member;
  }

  @Override
  public List<Member> list() throws Exception {
    List<Member> members = memberDao.findAll();
    return members;
  }

  @Override
  public void update(Member member) throws Exception {
    memberDao.update(member);
  }
  
  @Override
  public List<Member> search(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }
  
  @Override
  public Member login(Map<String, Object> params) throws Exception {
    return memberDao.findByEmailPassword(params);
  }
}
