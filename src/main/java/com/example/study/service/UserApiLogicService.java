package com.example.study.service;

import com.example.study.interpace.CrudInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;

    // todo 1. request data 가져옴
    // todo 2. user 생성
    // todo 3. 생성 된 데이터 -> UserApiResponse Return

    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {

        // todo 1-1. request data
        UserApiRequest userApiRequest = request.getData();

        // todo 2-1. User 생성
        User user = User.builder()
                .account(userApiRequest.getAccount())
                .password(userApiRequest.getPassword())
                .status("REGISTERED")
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = userRepository.save(user);

        // todo 3-1. 생성 된 데이터 기준 -> UserApiResponse Return
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {

        // id -> repository getOne, getById
        // user -> userApiResponse return
        return userRepository.findById(id)
                .map(this::response) // this::response === user -> response(user) 인텔리제이 추천
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // todo 1. data get
        UserApiRequest userApiRequest = request.getData();

        // todo 2. id -> user find
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            // todo 3. data -> update
            user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setEmail(userApiRequest.getEmail())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setUnregisteredAt(userApiRequest.getUnregisteredAt());
            return user;
        }) // todo 4. userApiResponse
        .map(user -> userRepository.save(user))     // update -> newUser
        .map(updateUser -> response(updateUser))    // userApiResponse
        .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        // todo 1. id -> repository -> user
        Optional<User> optional = userRepository.findById(id);

        // todo 2. repository -> delete
        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));

    }


    private Header<UserApiResponse> response(User user){
        // user return -> userApiResponse

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // todo 암호화
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        // Header + data return
        return Header.OK(userApiResponse);
    }

}
