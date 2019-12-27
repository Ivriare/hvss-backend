package dto.user;

import lombok.Data;

@Data
public class UserRegisterData {

    String username;
    String password;
    String emailAddress;
    String sessionToken;
}
