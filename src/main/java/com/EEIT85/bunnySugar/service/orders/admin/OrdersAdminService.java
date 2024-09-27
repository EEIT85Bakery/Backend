package com.EEIT85.bunnySugar.service.orders.admin;

import com.EEIT85.bunnySugar.dto.orders.Admin.OrderDetailsAdminSelectDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrderDetailsAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminSelectDto;
import com.EEIT85.bunnySugar.dto.orders.Admin.OrdersAdminUpdateDto;
import com.EEIT85.bunnySugar.dto.users.admin.MemberAdminSelectDto;
import com.EEIT85.bunnySugar.entity.OrderDetails;
import com.EEIT85.bunnySugar.entity.Orders;
import com.EEIT85.bunnySugar.entity.Users;
import com.EEIT85.bunnySugar.exception.OrderNotFoundException;
import com.EEIT85.bunnySugar.exception.ResourceNotFoundException;
import com.EEIT85.bunnySugar.repository.OrderDetailsRepository;
import com.EEIT85.bunnySugar.repository.OrdersRepository;
import com.EEIT85.bunnySugar.repository.ProductsRepository;
import com.EEIT85.bunnySugar.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdersAdminService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductsRepository productsRepository;

    // 查詢所有訂單並返回分頁
    public List<OrdersAdminSelectDto> getAllOrders(Pageable pageable) {
        return ordersRepository.findAllOrdersAdminSelectDto(pageable);
    }
    public Page<MemberAdminSelectDto> getAllMembers(Pageable pageable) {
        return userRepository.findAllMemberAdminSelectDto(pageable);
    }


    public OrdersAdminSelectDto getOrderById(Long id) {
        Orders order = ordersRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
        return convertToSelectDto(order);
    }

    @Transactional
    public OrdersAdminSelectDto updateOrder(OrdersAdminUpdateDto updateDto) {
        Orders order = ordersRepository.findById(updateDto.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        order.setPickupStatus(updateDto.getPickupStatus());

        for (OrderDetailsAdminUpdateDto detailDto : updateDto.getOrderDetails()) {
            OrderDetails detail = orderDetailsRepository.findById(detailDto.getId())
                    .orElseThrow(() -> new OrderNotFoundException("Order detail not found"));
            detail.setPickupTime(detailDto.getPickupTime());
            orderDetailsRepository.save(detail);
        }

        order.setUpdateTime(LocalDateTime.now());
        Orders updatedOrder = ordersRepository.save(order);
        return convertToSelectDto(updatedOrder);
    }

    private OrdersAdminSelectDto convertToSelectDto(Orders order) {
        OrdersAdminSelectDto dto = new OrdersAdminSelectDto();
        dto.setId(order.getId());
        dto.setOrderNumber(order.getOrderNumber());
        dto.setPickupStatus(order.getPickupStatus());
        dto.setTotal(order.getTotal());
        dto.setCouponName(order.getCouponName());
        dto.setUsedBunnyCoins(order.getUsedBunnyCoins());
        dto.setPaymentPrice(order.getPaymentPrice());
        dto.setCreateTime(order.getCreateTime());
        dto.setUpdateTime(order.getUpdateTime());

        List<OrderDetailsAdminSelectDto> detailDtos = order.getOrderDetails().stream()
                .map(this::convertToDetailSelectDto)
                .collect(Collectors.toList());
        dto.setOrderDetails(detailDtos);

        return dto;
    }

    private OrderDetailsAdminSelectDto convertToDetailSelectDto(OrderDetails detail) {
        OrderDetailsAdminSelectDto dto = new OrderDetailsAdminSelectDto();
        dto.setId(detail.getId());
        dto.setProductId(detail.getProduct().getId());
        dto.setProductName(detail.getProduct().getProductName());
        dto.setQuantity(detail.getQuantity());
        dto.setPrice(detail.getPrice());
        dto.setCreateTime(detail.getCreateTime());
        dto.setUpdateTime(detail.getUpdateTime());
        dto.setPickupTime(detail.getPickupTime());
        return dto;
    }
}
