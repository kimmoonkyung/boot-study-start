package com.example.study.service;

import com.example.study.model.entity.Category;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.CategoryApiRequest;
import com.example.study.model.network.response.CategoryApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {


    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();
//        log.info("## Category Create : {}", body);
        Category category = Category.builder()
                .type(body.getType())
                .title(body.getTitle())
                .build();

        Category newCategory = baseRepository.save(category);
//        log.info("## newCategory : {}", body);
        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {

        CategoryApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(category -> {
                        category
                                .setType(body.getType())
                                .setTitle(body.getTitle());
                        return category;
                })
                .map(baseRepository::save)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("없데이트!"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(category -> {
                    baseRepository.delete(category);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터없음"));
    }

    private Header<CategoryApiResponse> response(Category category){
//        log.info("## response category : {}", category);

        CategoryApiResponse data = CategoryApiResponse.builder()
                .id(category.getId())
                .title(category.getTitle())
                .type(category.getType())
                .build();

        return Header.OK(data);

    }

}
