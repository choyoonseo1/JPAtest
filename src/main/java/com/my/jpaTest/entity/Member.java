package com.my.jpaTest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
//Many
public class Member {
    @Id
    private String memberId;
    private String name;

    //팀 정보를 갖는 연관관계를 구성
    @ManyToOne
    @JoinColumn(name = "teamId")
    // Team의 private String teamId;을 가리킨다.
    private Team team;
}
