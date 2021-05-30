package com.example.study.service;

import com.example.study.model.entity.OrderDetail;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderDetailApiRequest;
import com.example.study.model.network.response.OrderDetailApiResponse;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.OrderGroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    private final OrderGroupRepository orderGroupRepository;
    private final ItemRepository itemRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {

        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(body.getStatus())
                .arrivalDate(LocalDateTime.now().plusDays(2))
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getId()))
                .item(itemRepository.getOne(body.getId()))
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없어용"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        return baseRepository.findById(body.getId())
                .map(orderDetail -> {
                        orderDetail
                                .setStatus(body.getStatus())
                                .setArrivalDate(body.getArrivalDate())
                                .setQuantity(body.getQuantity())
                                .setTotalPrice(body.getTotalPrice())
                                .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                                .setItem(itemRepository.getOne(body.getItemId()))
                                ;
                        return orderDetail;
                })
                .map(newOrderDetail -> baseRepository.save(newOrderDetail))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("없데이트!"));
    }

    @Override
    public Header delete(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> {
                    baseRepository.delete(orderDetail);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("삭제할 데이터가 존재 하지 않음"));
    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {
        OrderDetailApiResponse data = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();
        return Header.OK(data);
    }

}
