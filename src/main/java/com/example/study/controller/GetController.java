package com.example.study.controller;

import com.example.study.model.SearchParam;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // /api/getMethod
    public String getRequest() {
        return "NICE 9OOD";
    }

    @GetMapping("/getParameter") // api/getParameter?id=3333&pwd=aabb
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd) {
        String password = "Fu1ck";
        System.out.println("id : " + id);
        System.out.println("pwd : " + pwd);
        return id + pwd;
    }

    // /api/getMultiParameter?account=mkkim&email=mkkim@email.com&page=10
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // { "account": "", "email": "", "page": 0 }
        return searchParam;
        // 스프링 부트에서는 잭슨라이브러리를 기본적으로 내장
        // 따로 설정하지 않는다면 객체를 리턴할 땐 자동적으로 제이슨으로 변환되어 리턴된다.
    }

    @GetMapping("/header")
    public Header getHeader() {
        // {"resultCode": "OK", "description": "OK"}
        return Header.builder().resultCode("OK").description("OK").build();
    }

}