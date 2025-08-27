package com.my.jpaTest.repository;

import com.my.jpaTest.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntertainmentRepository extends JpaRepository<Entertainment, String> {
}

