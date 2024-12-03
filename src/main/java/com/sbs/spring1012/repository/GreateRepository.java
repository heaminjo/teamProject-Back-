package com.sbs.spring1012.repository;

import com.sbs.spring1012.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreateRepository extends JpaRepository<Great,Long> {
    Boolean existsByBoardAndMember(Board board, Member member);
    Optional<Great> findByBoardAndMember(Board board ,Member member);
}
