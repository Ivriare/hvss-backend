package dao.content;

import dao.HvssDao;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.HvssContentObject;
import entities.user.HvssUser;

public interface HvssContentObjectDao extends HvssDao<Long , HvssContentObject> {
    <T extends HvssContentObject> T getContentObject(String uuid);
}
