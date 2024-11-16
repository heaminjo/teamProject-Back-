package com.sbs.spring1012.repository;

import com.sbs.spring1012.entity.Comment;
import com.sbs.spring1012.entity.GreatComment;
import com.sbs.spring1012.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GreateCommentRepository extends JpaRepository<GreatComment,Long> {
    Optional<GreatComment> findByCommentAndMember(Comment comment, Member member);
}
