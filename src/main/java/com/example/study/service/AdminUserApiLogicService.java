package com.example.study.service;

import com.example.study.model.entity.AdminUser;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.AdminUserApiRequest;
import com.example.study.model.network.response.AdminUserApiResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AdminUserApiLogicService extends BaseService<AdminUserApiRequest, AdminUserApiResponse, AdminUser> {

    @Override
    public Header<AdminUserApiResponse> create(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        AdminUser adminUser = AdminUser.builder()
                .account(body.getAccount())
                .password(body.getPassword())
                .status(body.getStatus())
                .role(body.getRole())
                .registeredAt(LocalDateTime.now())
                .build();

        AdminUser newAdminUser = baseRepository.save(adminUser);

        return response(newAdminUser);
    }

    @Override
    public Header<AdminUserApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<AdminUserApiResponse> update(Header<AdminUserApiRequest> request) {

        AdminUserApiRequest body = request.getData();

        return baseRepository.findById(body.getId())
                .map(adminUser -> {
                        adminUser
                                .setAccount(body.getAccount())
                                .setPassword(body.getPassword())
                                .setStatus(body.getStatus())
                                .setRole(body.getRole());
                        return adminUser;
                })
                .map(newAdminUser -> baseRepository.save(newAdminUser))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("없데이트!"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(adminUser -> {
                    baseRepository.delete(adminUser);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("삭제 할 데이터가 존재하지 않음"));
    }

    private Header<AdminUserApiResponse> response(AdminUser adminUser) {

        AdminUserApiResponse data = AdminUserApiResponse.builder()
                .id(adminUser.getId())
                .account(adminUser.getAccount())
                .password(adminUser.getPassword())
                .status(adminUser.getStatus())
                .role(adminUser.getRole())
                .build();

        return Header.OK(data);

    }
}
