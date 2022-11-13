package com.ford.spare.service;

import com.ford.spare.common.ApiResponse;
import com.ford.spare.common.UUIDGenerator;
import com.ford.spare.model.SparePart;
import com.ford.spare.model.UpdateSparePartRequest;
import com.ford.spare.model.User;
import com.ford.spare.repository.SpareRepository;
import com.ford.spare.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Log4j2
public class SpareService {
    @Autowired
    SpareRepository spareRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UUIDGenerator uuidGenerator;

    public ApiResponse addSpareItem(SparePart sparePart){
        log.info("Add Spare Item:"+sparePart);

        Map<String,Boolean> validUser = isUserValid(sparePart.getAdminId());
        log.info("Valid User:"+validUser);
        ApiResponse response = new ApiResponse();
        if(validUser.get("isUserAdmin")){
            String spareId = uuidGenerator.getUUID();
            sparePart.setSpareId(spareId);
            SparePart savedSparePart =  spareRepository.save(sparePart);
             response = new ApiResponse(HttpStatus.OK.toString(), "Spare Item Added",savedSparePart,HttpStatus.OK);
        }else{
            response = new ApiResponse(HttpStatus.NOT_ACCEPTABLE.toString(), "Invalid Operation",sparePart.getAdminId(),HttpStatus.NOT_ACCEPTABLE);
        }
        return response;
    }
    public ApiResponse getAllSpareItems(){
        List<SparePart> savedSparePart =  spareRepository.findAll();
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "All Spare Item",savedSparePart,HttpStatus.OK);
        return response;
    }
    public ApiResponse removeSpareItem(String spareId,String userId){
        log.info("Remove Spare Item:"+spareId);
        Map<String,Boolean> validUser = isUserValid(userId);
        ApiResponse apiResponse = new ApiResponse();
        if(validUser.get("isUserAdmin")){
            Optional<SparePart> sparePart =  spareRepository.findByTheId(spareId);
            log.info("Spare Part Found:"+sparePart);
            if(!sparePart.isPresent()){
                apiResponse = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "Spare Item Not Found",spareId,HttpStatus.NO_CONTENT);
            }else{
                spareRepository.deleteBySpareId((sparePart.get().getSpareId()));
                apiResponse = new ApiResponse(HttpStatus.OK.toString(), "Spare Item Removed",spareId,HttpStatus.OK);
            }
        }else{
            apiResponse = new ApiResponse(HttpStatus.NOT_ACCEPTABLE.toString(), "Invalid Operation",userId,HttpStatus.NOT_ACCEPTABLE);
        }
        return apiResponse;
    }

    public ApiResponse updateSpareItem(UpdateSparePartRequest updateSparePartRequest){
        log.info("Update Spare Item:"+updateSparePartRequest);
        Map<String,Boolean> validUser = isUserValid(updateSparePartRequest.getUserId());
        ApiResponse apiResponse = new ApiResponse();
        if(validUser.get("isUserAdmin")){
            Optional<SparePart> sp =  spareRepository.findByTheId(updateSparePartRequest.getSpareId());
            if(!sp.isPresent()){
                apiResponse = new ApiResponse(HttpStatus.NO_CONTENT.toString(), "Spare Item Not Found",updateSparePartRequest.getSpareId(),HttpStatus.NO_CONTENT);
            }else{
                SparePart updatedSparePart = new SparePart();
                updatedSparePart.set_id(sp.get().get_id());
                updatedSparePart.setAdminId(sp.get().getAdminId());
                updatedSparePart.setSpareId(updateSparePartRequest.getSpareId());
                updatedSparePart.setAvailability(updateSparePartRequest.isAvailability());
                updatedSparePart.setInventory(updateSparePartRequest.getInventory());
                updatedSparePart.setName(updateSparePartRequest.getName());

                SparePart updated = spareRepository.save(updatedSparePart);
                apiResponse = new ApiResponse(HttpStatus.OK.toString(), "Spare Item Updated",updated,HttpStatus.OK);
            }
        }else{
            apiResponse = new ApiResponse(HttpStatus.NOT_ACCEPTABLE.toString(), "Invalid Operation",updateSparePartRequest.getUserId(),HttpStatus.NOT_ACCEPTABLE);
        }

        return apiResponse;
    }

    private Map<String,Boolean> isUserValid(String userId) {
        log.info("isUserAdmin:"+userId);
        Map<String,Boolean> resultsMap = new HashMap();
        // verify if userid is admin
        Optional<User> user = userRepository.findByTheUserId(userId);
        log.info("User:"+user.get());
        if(user.isPresent() && user.get().getAdmin().equalsIgnoreCase("true")){
            resultsMap.put("isValidUser",true);
            resultsMap.put("isUserAdmin",true);
        }else{
            resultsMap.put("isValidUser",false);
            resultsMap.put("isUserAdmin",false);
        }
        return resultsMap;
    }


}
