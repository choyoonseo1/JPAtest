package com.my.jpaTest.service;

import com.my.jpaTest.entity.*;
import com.my.jpaTest.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
class InitDataServiceTest {

    @Autowired InitDataService initDataService;
    @Autowired EntertainmentRepository entertainmentRepository;
    @Autowired GirlGroupRepository groupRepository;
    @Autowired IdolMemberRepository memberRepository;

    @PersistenceContext EntityManager em;

    @Test
    @DisplayName("initData로 기본 데이터가 저장된다")
    @Transactional
    void initData_saves() {
        // when
        initDataService.initData();
        em.flush(); em.clear();

        // then
        assertThat(entertainmentRepository.count()).isEqualTo(2);
        assertThat(groupRepository.count()).isEqualTo(2);
        assertThat(memberRepository.count()).isEqualTo(4);

        // 내용 확인
        GirlGroup ive = groupRepository.findById("ive").orElseThrow();
        assertThat(ive.getEntertainment().getName()).isEqualTo("스타쉽");

        IdolMember wonyoung = memberRepository.findById("장원영").orElseThrow();
        assertThat(wonyoung.getGroup().getName()).isEqualTo("아이브");
    }
}
