package com.my.jpaTest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//One
public class Team {
    @Id
    private String teamName;
    private String teamId;

    // 팀의 소속된 멤버의 리스트를 저장
    // 연관관계 설정 : Member클래스의 team정보
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    // Member의 private Team team을 가리킨다.
    private List<Member> memberList = new ArrayList<>();

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
