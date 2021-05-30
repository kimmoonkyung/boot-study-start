package com.example.study.service;

import com.example.study.controller.CrudController;
import com.example.study.interpace.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.entity.User;
import com.example.study.model.enumclass.UserStatus;
import com.example.study.model.network.Header;
import com.example.study.model.network.Pagination;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    private final UserRepository userRepository;
    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final ItemApiLogicService itemApiLogicService;

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
                .status(UserStatus.REGISTERED)
                .email(userApiRequest.getEmail())
                .phoneNumber(userApiRequest.getPhoneNumber())
                .registeredAt(LocalDateTime.now())
                .build();
        User newUser = baseRepository.save(user);

        // todo 3-1. 생성 된 데이터 기준 -> UserApiResponse Return
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        log.info("## USER API SERVICE ID {}", id);
        // id -> repository getOne, getById
        // user -> userApiResponse return
        return baseRepository.findById(id)
                .map(user -> response(user)) // this::response === user -> response(user) 인텔리제이 추천
                //.map(userApiResponse -> Header.OK(userApiResponse))
                .map(Header::OK) // 위를 아래로 람다 사용 가능
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {

        // todo 1. data get
        UserApiRequest userApiRequest = request.getData();

        // todo 2. id -> user find
        Optional<User> optional = baseRepository.findById(userApiRequest.getId());

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
        .map(user -> baseRepository.save(user))     // update -> newUser
//        .map(updateUser -> response(updateUser))    // userApiResponse
        .map(user -> response(user))
        .map(Header::OK)
        .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {

        // todo 1. id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);

        // todo 2. repository -> delete
        return optional.map(user -> {
            baseRepository.delete(user);
            return Header.OK();
        })
        .orElseGet(() -> Header.ERROR("데이터 없음"));

    }


    private UserApiResponse response(User user){
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
        return userApiResponse;
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {

        Page<User> users = userRepository.findAll(pageable);

        List<UserApiResponse> userApiResponseList = users.stream()
                .map(user -> response(user)) // this::response 가능
                .collect(Collectors.toList());

        // List<UserApiResponse>
        // -> Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentPage(users.getNumber())
                .currentElements(users.getNumberOfElements())
                .build();

        return Header.OK(userApiResponseList, pagination);

    }

    // 존나 어려웡 ㅠ 근데 이렇게 주면 프론트 입장에서는 정말 편하겠다.
    // todo! 분석 조져 ( 프론트에 response 해주는 프로세스를 제대로 이해 하고 있다면 그렇게 어려운 코드는 아닌것으로 보인다 )
    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {

        // user
        User user = userRepository.getOne(id);
        UserApiResponse userApiResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroupList = user.getOrderGroupList();
        List<OrderGroupApiResponse> orderGroupApiResponseList =
            orderGroupList.stream()
                    .map(orderGroup -> {
                       OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup).getData();
                       // item api response
                       List<ItemApiResponse> itemApiResponseList = orderGroup.getOrderDetailList().stream()
                               .map(detail -> detail.getItem())
                               .map(item -> itemApiLogicService.response(item).getData())
                               .collect(Collectors.toList());
                       orderGroupApiResponse.setItemApiResponseList(itemApiResponseList);
                       return orderGroupApiResponse;
                    })
                    .collect(Collectors.toList());

        userApiResponse.setOrderGroupApiResponseList(orderGroupApiResponseList);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userApiResponse(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);

    }


}
