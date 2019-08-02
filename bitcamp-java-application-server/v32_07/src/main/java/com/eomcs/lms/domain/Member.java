package com.eomcs.lms.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
  // serialVersionUID가 같으면 받은 클래스와 보낸 클래스의 같은 필드끼리 연결한다.
  // 근데 번호를 맞추는 것이 번거롭고, 자바에 종속되어 다른 언어와 교환할 수 없다.
  // 그래서 실무에서는 인스턴스를 직접 주고받기 보단, DataIOStream으로 데이터를 규칙에 따라 보내는 경우가 많다.
  private static final long serialVersionUID = 1L; 
  private int no;
  private String name;
  private String email;
  private String password;
  private String tel;
  private String photo;
  private Date registeredDate;
  
  @Override
  public String toString() {
    return "Member [no=" + no + ", name=" + name + ", email=" + email + ", password=" + password
        + ", tel=" + tel + ", photo=" + photo + ", registeredDate=" + registeredDate + "]";
  }
  
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getPhoto() {
    return photo;
  }
  public void setPhoto(String photo) {
    this.photo = photo;
  }
  public Date getRegisteredDate() {
    return registeredDate;
  }
  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
  
}
