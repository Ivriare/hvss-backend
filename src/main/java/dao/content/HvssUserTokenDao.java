package dao.content;

import dao.HvssDao;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.user.HvssUser;
import entities.user.HvssUserToken;

public interface HvssUserTokenDao extends HvssDao<Long , HvssUserToken> {

    HvssUserToken getTokenIdByTokenUid(String uid, String sessionToken);
}
