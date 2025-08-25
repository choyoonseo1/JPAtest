package com.my.jpaTest.service;

import com.my.jpaTest.entity.Member;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ContextServiceTest {
        @Autowired
    EntityManager em;

        @Autowired
    ContextService contextService;

    @Test
    @DisplayName("1차 캐시 테스트")
    void firstCash() {
        Member m = contextService.memberInsert();
        System.out.println("========" + m);
    }

    @Test
    @DisplayName("데이터 영속성 보장테스트")

    void 데이터_영속성_보장테스트(){
        Member a_1 = Member.builder()
                .memberId("hong")
                .name("홍길동`")
                .build();
        Member b_1 = Member.builder()
                .memberId("hong")
                .name("홍길동`")
                .build();
        System.out.println("위" + a_1.equals(b_1));
        // 엔티티매니저의 영속성 컨테스트영역에서 가져와서 똑같음.
        Member a = em.find(Member.class, "jang");
        Member b = em.find(Member.class, "jang");
        System.out.println(a.equals(b));
    }

    @Test
    @DisplayName("transction 쓰기 지연 테스트")
    void transcactionTest() {
        contextService.transactionTest();
    }

    @Test
    @DisplayName("Dirty checking 테스트")
    // Dirty Checking :  변경감지
    void dirtyChecking() {
        contextService.dirtyCheckingTest();
    }

    @Test
    @DisplayName("delete 테스트")
        // Dirty Checking :  변경감지
    void deleteMember() {
        contextService.deleteMember();
    }
}