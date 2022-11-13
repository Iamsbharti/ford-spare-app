package com.ford.spare.controller;

import com.ford.spare.common.ApiResponse;
import com.ford.spare.model.SparePart;
import com.ford.spare.model.UpdateSparePartRequest;
import com.ford.spare.service.SpareService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/spare")
public class SpareController {
    @Autowired
    SpareService spareService;

    @GetMapping("/all")
    private ResponseEntity getAllSpareItems(){
        ApiResponse apiResponse = spareService.getAllSpareItems();
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/add")
    private ResponseEntity addSpareItem(@RequestBody SparePart sparePart){
        ApiResponse apiResponse = spareService.addSpareItem(sparePart);
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }

    @DeleteMapping("/remove")
    private ResponseEntity removeSpareItem(@RequestParam String spareId, @RequestParam String userId){
        ApiResponse apiResponse = spareService.removeSpareItem(spareId,userId);
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }

    @PostMapping("/update")
    private ResponseEntity updateSpareItem(@RequestBody UpdateSparePartRequest updateSparePartRequest){
        ApiResponse apiResponse = spareService.updateSpareItem(updateSparePartRequest);
        return new ResponseEntity(apiResponse, apiResponse.getHttpStatus());
    }


}
