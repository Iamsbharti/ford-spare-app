package com.ford.spare.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SparePartOrderRequest {
    private String spareId;
    private String name;
    private Integer orderCount;
}
