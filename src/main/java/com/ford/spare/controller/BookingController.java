package com.ford.spare.controller;


import com.ford.spare.common.ApiResponse;
import com.ford.spare.model.Orders;
import com.ford.spare.model.SparePart;
import com.ford.spare.service.BookingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/order")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/all")
    private ResponseEntity getAllOrders(){
        ApiResponse apiResponse = bookingService.getAllSpareItems() ;
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/spare/parts")
    private ResponseEntity bookAOrder(@RequestBody Orders orders){
        ApiResponse apiResponse = bookingService.bookSpareItem(orders) ;
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }

}
