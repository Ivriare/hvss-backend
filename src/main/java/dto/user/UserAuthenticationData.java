package dto.user;

import lombok.Data;

@Data
public class UserAuthenticationData {
    String username;
    String password;
    Boolean rememberUser;
    String sessionToken;
}
