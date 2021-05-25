package com.example.study.controller;

import com.example.study.model.SearchParam;
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
    public String getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return "9ood";
    }

}