package services;

import dao.content.HvssUserDao;
import dao.content.HvssUserTokenDao;
import dto.user.UserAuthenticationData;
import dto.user.UserDataTransfer;
import dto.user.UserRegisterData;
import dto.user.UserStatus;
import entities.user.HvssUser;
import entities.user.HvssUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.HashIdentifierUtils;

import javax.transaction.UserTransaction;

@Component
public class UserService {

    @Autowired
    HvssUserDao hvssUserDao;

    @Autowired
    HvssUserTokenDao hvssUserTokenDao;

    @Autowired
    HashIdentifierUtils hashIdentifierUtils;

    public UserDataTransfer authenticateUser(UserAuthenticationData userAuthenticationData){
        UserDataTransfer userDataTransfer = new UserDataTransfer();
        HvssUser user = hvssUserDao.findUserByUsernameAndPassword(userAuthenticationData);
        if(user != null){

            HvssUserToken hvssUserToken = new HvssUserToken();
            hvssUserToken.setUid(hashIdentifierUtils.createNewHashId());
            hvssUserToken.setSessionToken(userAuthenticationData.getSessionToken());
            userDataTransfer.setUserToken(hvssUserToken.getUid());
            userDataTransfer.setSessionToken(userAuthenticationData.getSessionToken());
            userDataTransfer.setUserStatus(UserStatus.AUTHENTICATED);

            user.setLoginRememberToken(hashIdentifierUtils.createNewHashId());
            if(userAuthenticationData.getRememberUser()) {
                userDataTransfer.setRememberUserToken(user.getLoginRememberToken());
            }
            user.setUserTokenId(hvssUserToken);
            hvssUserDao.merge(user);
        } else {
            userDataTransfer.setUserStatus(UserStatus.REJECTED);
        }
        return userDataTransfer;
    }

    public UserDataTransfer registerUser(UserRegisterData userRegisterData){
        UserDataTransfer userDataTransfer = new UserDataTransfer();
        HvssUser user = hvssUserDao.checkUserIdExists(userRegisterData);
        if(user == null) {
            user = new HvssUser(userRegisterData);
            user.setUid(hashIdentifierUtils.createNewHashId());
            hvssUserDao.persist(user);
            userDataTransfer.setUserStatus(UserStatus.CREATED);
            UserAuthenticationData userAuthenticationData = new UserAuthenticationData();
            userAuthenticationData.setUsername(userRegisterData.getUsername());
            userAuthenticationData.setPassword(userRegisterData.getPassword());
            user = hvssUserDao.findUserByUsernameAndPassword(userAuthenticationData);
            HvssUserToken hvssUserToken = new HvssUserToken();
            hvssUserToken.setUid(hashIdentifierUtils.createNewHashId());
            hvssUserToken.setSessionToken(userRegisterData.getSessionToken());
            userDataTransfer.setSessionToken(hvssUserToken.getSessionToken());
            user.setUserTokenId(hvssUserToken);
            hvssUserDao.merge(user);
            userDataTransfer.setUserToken(hvssUserToken.getUid());
        } else {
            userDataTransfer.setUserStatus(UserStatus.EXISTS);
        }
        return userDataTransfer;
    }

    // Uses REMEMBER_USER_TOKEN to create a new auth token
    public UserDataTransfer refreshAuthToken(UserDataTransfer userDataTransfer){
        HvssUser user = hvssUserDao.findUserByRememberTokenId(userDataTransfer.getRememberUserToken());
        if(user != null) {
            HvssUserToken hvssUserToken = new HvssUserToken();
            hvssUserToken.setUid(hashIdentifierUtils.createNewHashId());
            hvssUserToken.setSessionToken(userDataTransfer.getSessionToken());
            user.setUserTokenId(hvssUserToken);
            hvssUserDao.merge(user);
            userDataTransfer.setUserToken(hvssUserToken.getUid());
            userDataTransfer.setUserStatus(UserStatus.UPDATED);
        } else {
            userDataTransfer.setUserStatus(UserStatus.NOT_EXIST);
        }
        return userDataTransfer;
    }

    public UserDataTransfer logoutUser(UserDataTransfer userDataTransfer){
        HvssUser user = getUserByToken(userDataTransfer.getUserToken(), userDataTransfer.getSessionToken());
        if(user != null) {
            user.setUserTokenId(null);
            user.setLoginRememberToken(null);
            hvssUserDao.merge(user);
            userDataTransfer.setUserStatus(UserStatus.LOGGED_OUT);
        } else {
            userDataTransfer.setUserStatus(UserStatus.NOT_EXIST);
        }
        return userDataTransfer;
    }

    public HvssUser getUserByToken(String authToken, String sessionToken){
        HvssUserToken userToken = hvssUserTokenDao.getTokenIdByTokenUid(authToken, sessionToken);
        if(userToken != null){
            return hvssUserDao.findUserByUserTokenId(userToken.getId());
        }
        return null;
    }

    private HvssUser manageUserAuthenticationToken(String authenticationToken) {

        return null;

    }
}
