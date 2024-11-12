package com.sbs.spring1012.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessTokenDto {
    private String grantType;
    private String accessToken;
    private Long accessTokenExpiresIn;
}