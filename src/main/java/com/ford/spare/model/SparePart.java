package com.ford.spare.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "spare")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePart {
    @Id
    private ObjectId _id;
    private String spareId;
    private String name;
    private boolean availability;
    private Integer inventory;
    private String adminId;
}
