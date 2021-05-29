package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;

// 스프링부트 or 그레이들 버젼 때문인지 예제 진행이 테스트코드 진행이 안 되어서 @ExtendWith(MokitoExtension.class)
// 기존 -> extends StudyApplicationTests
//@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest extends StudyApplicationTests {

    // DI (Dependency Injection)
    // 스프링부트 버젼 때문인지 강의(19년도 강의임)로는 테스트 코드
    // 예제 진행이 안되어서 @Autowired를 @Mock로 변경/
    // @Mock 를 사용해서 실제 디비에 값이 반영 되진 않는다.
//    @Mock
//    private UserRepository userRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){

        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test03@gmail.com";
        String phoneNumber = "010-1111-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
//        user.setCreatedAt(createdAt);
//        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assertions.assertNotNull(newUser);

        newUser.setAccount("CHANGE");
        userRepository.save(newUser);
        /*
        User user = new User();
        // user.setId(); auto increment라 셋팅 안해도 됨
        user.setAccount("mkkim");
        user.setEmail("mkkim@test.com");
        user.setPhoneNumber("010-1234-5678");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("mkkim");

        User newUser = userRepository.save(user);
        System.out.println("all : " + userRepository.findAll());
        System.out.println("user : " + newUser);
        // 세이브로 유저가 생성이 되었는데 --> 아니다 나는 user를 조회한거지 save로 생성된 user를 조회한게 아니다.
        // 왜 파인드올로 찾지 못하는걸까
        // mock과 관련되어 있는걸까
        // --> 아 ㅡㅡ updatedAt가 updateAt로 오타 여서 나는 에러 였다 ㅡㅡ

         */
    }

    @Test
    @Transactional
    public void read(){
        /*
        Optional<User> user = userRepository.findById(1L);
        user.ifPresent(selectUser -> {
            selectUser.getOrderDetailList().forEach(detail -> {
                Item item = detail.getItem();
                System.out.println("#### : " + item);
            });
        });
         */

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1234-5678");
        if(user != null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {

                System.out.println("--------------주문묶음--------------");
                System.out.println("수령인: " + orderGroup.getRevName());
                System.out.println("수령지: " + orderGroup.getRevAddress());
                System.out.println("총금액: " + orderGroup.getTotalPrice());
                System.out.println("총수량: " + orderGroup.getTotalQuantity());

                System.out.println("--------------주문상세--------------");

                orderGroup.getOrderDetailList().forEach(orderDetail -> {
                    System.out.println("파트너사 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리: " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품: " + orderDetail.getItem().getName());
                    System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter());
                    System.out.println("주문 상태: " + orderDetail.getStatus());
                    System.out.println("도착예정일자: " + orderDetail.getArrivalDate());
                });
            });
        }
        Assertions.assertNotNull(user);
    }

    public void update(){

    }

    public void delete(){

    }
}
