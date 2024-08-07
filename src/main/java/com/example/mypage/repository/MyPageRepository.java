package com.example.mypage.repository;

import com.example.mypage.domain.MyPage;
import com.example.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyPageRepository {

  private final EntityManager em;

  public void save(MyPage myPage) {
    em.persist(myPage);
  }
  public Optional<MyPage> findById(Long id) {
    MyPage myPage = em.createQuery("select m from MyPage m where m.id = :id", MyPage.class)
        .setParameter("id", id)
        .getSingleResult();
    return Optional.ofNullable(myPage);
  }

  public void updateMemberInfo(Long id, String email, String phonenumber, String bank, String account, Date birthday, String password) {
    MyPage myPage = em.find(MyPage.class, id);
    if (myPage != null) {
      myPage.setEmail(email);
      myPage.setPhonenumber(phonenumber);
      myPage.setBank(bank);
      myPage.setAccount(account);
      myPage.setBirthday(birthday);
      myPage.getMember().setPassword(password);

    }
  }

  public Optional<MyPage> findByMemberId(Long memberId) {
      MyPage myPage = em.createQuery("SELECT mp FROM MyPage mp WHERE mp.member.id = :memberId", MyPage.class)
              .setParameter("memberId", memberId)
              .getSingleResult();
      return Optional.of(myPage);
  }
}
