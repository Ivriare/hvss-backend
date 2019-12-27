package dto.user;

import lombok.Data;

@Data
public class UserDataTransfer {

    UserStatus userStatus;
    String userToken;
    String rememberUserToken;
    String sessionToken;

}
