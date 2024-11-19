package com.sbs.spring1012.controller;

import com.sbs.spring1012.dto.CategoryDto;
import com.sbs.spring1012.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class CategoryController {
    //CategoryService 주입을 위한 final 필드
    private final CategoryService categoryService;

    @PostMapping("/new")
    public ResponseEntity<CategoryDto> createCategory(@RequestParam String categoryName){
        CategoryDto categoryDto = categoryService.createCategory(categoryName); // 새 카테고리 생성
        log.info("카테고리 저장하기: {}", categoryDto);
        return ResponseEntity.ok(categoryDto); // 저장된 카테고리 DTO를 반환
    }
}