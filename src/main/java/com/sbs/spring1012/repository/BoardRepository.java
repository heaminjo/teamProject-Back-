package com.sbs.spring1012.repository;

import com.sbs.spring1012.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
    Page<Board> findAll(Pageable pageable);

    @Query("SELECT b FROM Board b WHERE " +
            "(:keyword IS NULL OR (:keyword IS NOT NULL AND " +
            "(LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :keyword, '%'))))) AND " +
            "LOWER(b.category.categoryName) LIKE LOWER(CONCAT('%', :categoryName, '%'))")
    Page<Board> findByKeywordAndCategoryName(
            @Param("keyword") String keyword,
            @Param("categoryName") String categoryName,
            Pageable pageable
    );
}
