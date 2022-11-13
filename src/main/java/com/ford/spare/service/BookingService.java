package com.ford.spare.service;

import com.ford.spare.common.ApiResponse;
import com.ford.spare.common.UUIDGenerator;
import com.ford.spare.model.Orders;
import com.ford.spare.model.SparePart;
import com.ford.spare.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class BookingService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UUIDGenerator uuidGenerator;

    public ApiResponse getAllSpareItems(){
        log.info("Get All Spare Items:");
        List<Orders> savedSparePart =  orderRepository.findAll();
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "All Orders",savedSparePart,HttpStatus.OK);
        return response;
    }

    public ApiResponse bookSpareItem(Orders orders){
        log.info("Book Spare Item::"+orders);
        String orderId = uuidGenerator.getUUID();
        orders.setOrderId(orderId);
        Orders savedOrders =  orderRepository.save(orders);
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "Spare Item Booked",savedOrders,HttpStatus.OK);
        return response;
    }
}
