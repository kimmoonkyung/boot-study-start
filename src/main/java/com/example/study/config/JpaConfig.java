package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// 여기는 설정 파일에 대한 것이 들어갑니다 어노테이션
@Configuration
// Jpa 감시 활성
@EnableJpaAuditing
public class JpaConfig {
}
