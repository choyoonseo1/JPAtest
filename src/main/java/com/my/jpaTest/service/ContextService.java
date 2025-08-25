package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ContextService {
    @Autowired
    EntityManager em;

    // 저장하는 작업을 수행
    public Member memberInsert() {
        Member jang = Member.builder()
                .memberId("jang")
                .name("장원영")
                .build();
        // 영속성 공간에 저장하는 명령
        em.persist(jang);

        // member_id = jang인 레코드를 찾아서 반환
        // member table에서 key값이 jang인 친구를 찾음
        Member won = em.find(Member.class, "jang");
        return won;
    }
}
