package com.ford.spare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    private ObjectId _id;
    private String orderId;
    private String userId;
    private List<SparePartOrderRequest> sparePartOrderRequestsList;
}
