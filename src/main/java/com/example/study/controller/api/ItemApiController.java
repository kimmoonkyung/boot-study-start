package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.interpace.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@Slf4j
@RestController
@RequestMapping("/api/item")
@AllArgsConstructor
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

//    private final ItemApiLogicService itemApiLogicService;
//
//    // 추상화
//    @PostConstruct
//    public void init(){
//        this.baseService = itemApiLogicService;
//    }

//    @Override
//    @PostMapping("") // /api/item
//    public Header<ItemApiResponse> create(@RequestBody Header<ItemApiRequest> request) {
//        log.info("ITEM CREATE : {}", request);
//        return itemApiLogicService.create(request);
//    }
//
//    @Override
//    @GetMapping("{id}") // /api/item/{id}
//    public Header<ItemApiResponse> read(@PathVariable Long id) {
//        log.info("ITEM READ : {}", id);
//        return itemApiLogicService.read(id);
//    }
//
//    @Override
//    @PutMapping("")
//    public Header<ItemApiResponse> update(@RequestBody Header<ItemApiRequest> request) {
//        log.info("ITEM UPDATE : {}", request);
//        return itemApiLogicService.update(request);
//    }
//
//    @Override
//    @DeleteMapping("{id}")
//    public Header delete(@PathVariable Long id) {
//        log.info("ITEM DELETE : {}", id);
//        return itemApiLogicService.delete(id);
//    }

}
