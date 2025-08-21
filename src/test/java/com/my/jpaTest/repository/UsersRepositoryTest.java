package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class UsersRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName(" 1. findByName Test")
    void findByName() {
        String name = "Basil Stektee";
        usersRepository
                .findByName(name)
                .stream()
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("pink 색상 상위 3개 찾기 테스트")
    void findByColor() {
        String color = "pink";
        usersRepository
                .findTop3ByLikeColor(color)
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("성별이 여자이고 색상이 Red인 자료")
    void findByGenderAndLikeColor(Gender gender, String color) {
        usersRepository.findByGenderAndLikeColor(Gender.Female, "Red")
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("findByCreatedAtAfter_Test")
    void findByCreatedAtAfter() {
        LocalDate yesterday = LocalDate.now().minusDays(2L);
        // 기준을 0시 0분 0초로 맞춰주는 함수. atStartOfDay
        // 0시 0분 0초에 가입 한 사람 누락 방지.
        LocalDateTime start = yesterday.atTime(23, 59, 59);
        usersRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L))
                .forEach(x -> System.out.println(x));
    }

    @Test
    @DisplayName("최근 한달 자료 찾기")
    void findByCreatedAtBetween() {
        // 한달 이전의 기준일 설정
        LocalDate baseData = LocalDate.now().minusMonths(1L);
        // 한달 전 날에다 시, 분, 초 추가
        LocalDateTime start = baseData.atTime(0, 0, 0);
        LocalDateTime end = LocalDateTime.now();
        usersRepository.findByCreatedAtBetween(start, end)
                .forEach(x -> System.out.println(x));
        // now로 하게 되면 앞전에 있는 정보들이 누락된다.
        // 기준은 항상 0시 0분 0초가 되어야한다.
    }
}