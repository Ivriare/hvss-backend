package utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class HashIdentifierUtils {

    public String createNewHashId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}
