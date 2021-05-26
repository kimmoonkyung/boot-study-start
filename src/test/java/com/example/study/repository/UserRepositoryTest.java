package com.example.study.repository;

import com.example.study.model.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.Optional;

// 스프링부트 or 그레이들 버젼 때문인지 예제 진행이 테스트코드 진행이 안 되어서 @ExtendWith(MokitoExtension.class)
// 기존 -> extends StudyApplicationTests
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    // DI (Dependency Injection)
    // 스프링부트 버젼 때문인지 강의(19년도 강의임)로는 테스트 코드
    // 예제 진행이 안되어서 @Autowired를 @Mock로 변경/
    // @Mock 를 사용해서 실제 디비에 값이 반영 되진 않는다.
    @Mock
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        // user.setId(); auto increment라 셋팅 안해도 됨
        user.setAccount("mkkim");
        user.setEmail("mkkim@test.com");
        user.setPhoneNumber("010-1234-5678");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("mkkim");

        userRepository.save(user);
        System.out.println("all : " + userRepository.findAll());
        System.out.println("user : " + user);
        // 세이브로 유저가 생성이 되었는데
        // 왜 파인드올로 찾지 못하는걸까
        // mock과 관련되어 있는걸까
    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(user1 -> {
            System.out.println(user1);
        });
    }

    public void update(){

    }

    public void delete(){

    }
}
