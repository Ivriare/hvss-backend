package dao.content;

import dao.HvssDao;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.content.HvssContent;
import entities.user.HvssUser;

import java.util.List;

public interface HvssUserDao extends HvssDao<Long , HvssUser> {

    HvssUser checkUserIdExists(UserRegisterData userRegisterData);
    HvssUser findUserByUsernameAndPassword(UserAuthenticationData userAuthenticationData);
    HvssUser findUserByUserTokenId(Long userTokenId);
    HvssUser findUserByRememberTokenId(String rememberToken);
    HvssUser findUserByEmail(String email);
}
