package com.my.jpaTest.service;

import com.my.jpaTest.dto.MemberDto;
import com.my.jpaTest.dto.MemberProjection;
import com.my.jpaTest.entity.Member;
import com.my.jpaTest.entity.Team;
import com.my.jpaTest.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class QueryService {
    @Autowired
    EntityManager em;

    public List<Member> dynamicQuery() {
        //이름이 이만기인 자료를 검색
        String sql = "SELECT m FROM Member in WHERE m.memberId=:id";
        // 리포지 > mem type , Member로만 받을 것 이기 때문
        TypedQuery<Member> query = em.createQuery(sql, Member.class)
                .setParameter("id", "lee");
        List<Member> members = query.getResultList();
        return members;
    }

    // 팀 전체 검색하기
    public List<Team> findAllTeam() {
        String sql = "SELECT t FROM Team t";
        Query query = em.createQuery(sql);
        List<Team> teamList = query.getResultList();
        return teamList;
    }

    // 멤버테이블 중에서 씨름팀에 속해있는 멤버만.
    public List<Member> findMemberSsirum() {
        String sql = "SELECT m FROM Member m " +
                " WHERE m.team.teamName LIKE : teamName";
        TypedQuery<Member> result = em.createQuery(sql, Member.class)
                .setParameter("teamName", "씨름%");
        return result.getResultList();
    }

    //씨름팀의 인원 수
    public Long teamCount() {
        String sql = "SELECT COUNT (m) FROM Member m" +
                "WHERE m.team.teamName LIKE :teamName";
        Query query = em.createQuery(sql)
                .setParameter("teamName", "씨름%");
        Long result = (Long) query.getSingleResult();
        return result;
    }

    // 멤버의 dto로 결과 받기
    public List<MemberDto> getMemberDto() {
        String sql = "SELECT NEW" +
                "com.my.jpaTest.dto.MemberDto(m.name, m.team.teamName)" +
                "FROM member m";
        TypedQuery<MemberDto> query = em.createQuery(sql, MemberDto.class);
        return query.getResultList();
    }
    @Autowired
    MemberRepository memberRepository;
    // Projection을 이용해서 데이터 받아오기

    public List<MemberProjection> getProjection() {
       return memberRepository.findProjection();
    }
}
