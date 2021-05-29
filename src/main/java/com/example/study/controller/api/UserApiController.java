package com.example.study.controller.api;

import com.example.study.interpace.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {

    @Autowired
    private UserRepository userRepository;

    @Override
    @PostMapping("") // /api/user 로 매핑
    public Header create() {
        return null;
    }

    @Override
    @GetMapping("{ids}") // /api/user/{ids} 로 매핑 -> @PathVariable 사용차 ids로
    public Header read(@PathVariable(name = "ids") Long id) {
        return null;
    }

    @Override
    @PutMapping("") // /api/user
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header delete(Long id) {
        return null;
    }


}
