package com.ford.spare.repository;

import com.ford.spare.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Orders, Integer> {
    List<Orders> findAll();
    Orders save(Orders orders);
    @Query("{ 'orderId' : ?0 }")
    Optional<Orders> findByTheOrderId(String orderId);
    void delete(Orders orders);
}