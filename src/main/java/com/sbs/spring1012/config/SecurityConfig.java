//package com.sbs.spring1012.config;
//
//import com.sbs.spring1012.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    MemberService memberService;
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .loginPage("/members/login") // 로그인 페이지 URL
//                .defaultSuccessUrl("/")      // 로그인 성공 시 이동할 URL
//                .usernameParameter("email")  // 로그인 시 사용할 파라미터 이름으로 email 지정
//                .failureUrl("/members/login/error") // 로그인 실패시 이동할 URL
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout")) // 로그아웃 URL
//                .logoutSuccessUrl("/");  // 로그아웃 성공 시 이동할 페이지
//    }
//    @Bean // 비밀번호를 DB에 그대로 저장하는 회원 정보가 노출 될 수 있으므로 암호화가 필수적으로 필요
//    public PasswordEncoder passwordEncoder() {
//        return  new BCryptPasswordEncoder();
//    }
//    @Override
//    // 인증은 AuthenticationManager를 통해 이루어지며 AuthenticationManagerBuilder가 AuthenticationManager를 생성
//    //  비밀번호 암호화를 위해 passwordEncoder를 지정
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
//}
