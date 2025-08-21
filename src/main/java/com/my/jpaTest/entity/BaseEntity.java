package com.my.jpaTest.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDateTime;
@Data
public class BaseEntity {
    // 새로운 행이 추가될 때 만 입력일이 자동추가
    @CreatedDate
        LocalDateTime insertedAt;
    // 데이터가 수정될 때 자동을 수정일이 기록
        @LastModifiedDate
        LocalDateTime updatedAt;
}
