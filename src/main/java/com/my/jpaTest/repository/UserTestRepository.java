package com.my.jpaTest.repository;

import com.my.jpaTest.dto.Gender;
import com.my.jpaTest.entity.UserTest;
import com.my.jpaTest.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTestRepository extends JpaRepository<UserTest, Long> {

}
