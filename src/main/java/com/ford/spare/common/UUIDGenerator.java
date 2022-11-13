package com.ford.spare.common;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class UUIDGenerator {
    public String getUUID(){
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
