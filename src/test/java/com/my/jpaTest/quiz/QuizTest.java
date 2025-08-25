package com.my.jpaTest.quiz;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.Users;
import com.my.jpaTest.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class QuizTest {
    @Autowired
    UsersRepository repository;

    @Test
    @Transactional
    @DisplayName("Given/When/Then으로 테스트 하기")
    void assertThatTest() {
        // 신규데이터 추가 테스트
        // Given
        Users jin = Users.builder()
                .name("안유진")
                .email("jin@korea.com")
                .gender(Gender.Female)
                .likeColor("Red")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        // When
        repository.save(jin);
        // Then
        // 이름으로 검색한 결과와 jin 이랑 같으면...성공
        Users result = repository.findByName("안유진").get(0);
        // 검사
        Assertions.assertThat(result.getEmail()).isEqualTo(jin.getEmail());
    }

    //    @Test
//    @DisplayName("문제 1")
//    void findByGenderAndNameContainsOrNameContains() {
//        repository.findByGenderAndNameContainsOrNameContains(Gender.Female, "w", "m")
//                .forEach(x-> System.out.println(x));
//        /
//    }
//    @Test

    /// /    @DisplayName("문제 6")
    /// /    void errorDataList() {
    /// /        List<Users> users = repository.findAll();
    /// /        for (Users user : users) {
    /// /            if (user.getCreatedAt().isBefore(user.getUpdatedAt())) {
    /// /                System.out.println(user);
    /// /            }
    /// /        }

//    @Test
//    @DisplayName("문제 8")
//    void likeColorSort() {
//        List<Users> result = repository.findAll(
//                Sort.by(Sort.Order.asc("likeColor"),
//                        Sort.Order.desc("name"))
//        );
//        for (int i = 0; i <= 20; i++) {
//                System.out.println(result.get(i));
//            }
//        }

//    @Test
//    @DisplayName("문제 10")
//    void sortAndPaging(){
//        Sort sort = Sort.by(Sort.Order.desc("updatedAt"));
//        Pageable pageable =  PageRequest.of(0, 10, sort);
//        repository.findAll(pageable).getContent()
//                .forEach(x-> System.out.println(x));
//    }
    @Test
    @DisplayName("문제 10")
    void manDataPaging() {
        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(1, 3, sort);
    }
}

