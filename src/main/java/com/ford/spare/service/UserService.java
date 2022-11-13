package com.ford.spare.service;

import com.ford.spare.common.ApiResponse;
import com.ford.spare.common.UUIDGenerator;
import com.ford.spare.model.User;
import com.ford.spare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UUIDGenerator uuidGenerator;

    public ApiResponse registerUser(User user) {
        String userId = uuidGenerator.getUUID();
        user.setUserId(userId);
        User savedUser =  userRepository.save(user);
        ApiResponse response = new ApiResponse(HttpStatus.OK.toString(), "User Registered ",savedUser,HttpStatus.OK);
        return response;
    }

    public ApiResponse loginUser(String loginId, String password) {
        Optional<User> user = userRepository.findByTheUserId(loginId);
        if(user.isPresent()){
            if(user.get().getPassword().equalsIgnoreCase(password)){
                user.get().setPassword("");
                return new  ApiResponse(HttpStatus.OK.toString(), "User Login Success",user,HttpStatus.OK);
            }else{
                return new ApiResponse(HttpStatus.UNAUTHORIZED.toString(),"Login failed",loginId,HttpStatus.UNAUTHORIZED);
            }
        }else{
            return new ApiResponse(HttpStatus.NO_CONTENT.toString(),"User Not Found",loginId,HttpStatus.NO_CONTENT);
        }
    }


}
