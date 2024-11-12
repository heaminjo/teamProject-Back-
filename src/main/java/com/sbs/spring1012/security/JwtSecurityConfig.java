package com.sbs.spring1012.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final com.sbs.spring1012.jwt.TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) {
        com.sbs.spring1012.jwt.JwtFilter customFilter = new com.sbs.spring1012.jwt.JwtFilter(tokenProvider); // JwtFilter 객체 생성
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class); // JwtFilter를 UsernamePasswordAuthenticationFilter 전에 넣는다.
    }
}