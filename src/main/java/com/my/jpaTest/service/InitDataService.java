package com.my.jpaTest.service;

import com.my.jpaTest.entity.*;
import com.my.jpaTest.repository.EntertainmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class InitDataService {

    private final EntertainmentRepository entertainmentRepository;

    public void initData() {
        // 엔터 1: 스타쉽 → 아이브 → (안유진, 장원영)
        Entertainment starship = Entertainment.builder()
                .id("starship").name("스타쉽").build();

        GirlGroup ive = GirlGroup.builder()
                .id("ive").name("아이브").build();

        IdolMember anYujin = IdolMember.builder()
                .id("안유진").name("유진").build();
        IdolMember jangWonyoung = IdolMember.builder()
                .id("장원영").name("원영").build();

        starship.addGroup(ive);
        ive.addMember(anYujin);
        ive.addMember(jangWonyoung);

        // 엔터 2: YG → 블랙핑크 → (제니, 지수)
        Entertainment yg = Entertainment.builder()
                .id("YG").name("와이지").build();

        GirlGroup blackPink = GirlGroup.builder()
                .id("blackPink").name("블핑").build();

        IdolMember jennie = IdolMember.builder()
                .id("제니").name("째니").build();
        IdolMember jisoo = IdolMember.builder()
                .id("지수").name("지수다").build();

        yg.addGroup(blackPink);
        blackPink.addMember(jennie);
        blackPink.addMember(jisoo);

        // 부모만 저장해도 자식까지 저장 (cascade = PERSIST)
        entertainmentRepository.save(starship);
        entertainmentRepository.save(yg);
    }
}
