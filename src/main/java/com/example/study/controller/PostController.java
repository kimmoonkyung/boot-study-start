package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    // HTML <Form> 태그
    // ajax 검색 (비동기)
    // http post body -> data
    // json, xml, multipart-form(파일) / text-plain

    // @RequestMapping(method = RequestMethod.POST, path = "/postMethod")
    @PostMapping(value = "/postMethod") // 위랑 같음
    public SearchParam postMethod(@RequestBody SearchParam searchParam) {
        return searchParam;
    }

    @PutMapping("/putMethod")
    public void put() {

    }

    @PatchMapping("/patchMethod")
    public void patch() {

    }

}
