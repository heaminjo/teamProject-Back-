package com.sbs.spring1012.repository;

import com.sbs.spring1012.dto.MemberResDto;
import com.sbs.spring1012.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Optional<Member> findByAlias(String alias);
    Optional<Member> findByEmailAndPwd(String email, String pwd);
    Optional<Member> findByEmail(String email);
}