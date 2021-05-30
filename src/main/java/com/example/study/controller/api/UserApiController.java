package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.interpace.CrudInterface;
import com.example.study.interpace.PagingInterface;
import com.example.study.model.entity.User;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.model.network.response.UserOrderInfoApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {
//public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    private final UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id) {
        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("")
    public Header<List<UserApiResponse>> search(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, size = 15) Pageable pageable) {
        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }

//    private final UserApiLogicService userApiLogicService;
//
//    // 추상화
//    @PostConstruct
//    public void init() {
//        this.baseService = userApiLogicService;
//    }
//    @Override
//    @PostMapping("") // api/user 로 매핑 됨
//    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
//        log.info("create : {}", request);
//        return userApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}") // /api/user/{id}
//    public Header<UserApiResponse> read(@PathVariable Long id) {
//        log.info("read id : {}", id);
//        return userApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("") // /api/user
//    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
//        log.info("update : {}", request);
//        return userApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}") // /api/user/{id}
//    public Header<UserApiResponse> delete(@PathVariable Long id) {
//        log.info("delete id : {}", id);
//        return userApiLogicService.delete(id);
//    }
}
