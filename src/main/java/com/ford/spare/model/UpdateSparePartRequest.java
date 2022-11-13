package com.ford.spare.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSparePartRequest {
    private String spareId;
    private String name;
    private boolean availability;
    private Integer inventory;
    private String userId;
}
