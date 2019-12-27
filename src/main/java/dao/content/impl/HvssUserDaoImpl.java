package dao.content.impl;

import dao.content.HvssContentDao;
import dao.content.HvssUserDao;
import dao.factory.HvssDaoImpl;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.HvssTypeEnum;
import entities.content.HvssContent;
import entities.user.HvssUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Component
public class HvssUserDaoImpl extends HvssDaoImpl<Long, HvssUser> implements HvssUserDao {

    @Autowired
    EntityManager entityManager;

    @Transactional(readOnly=true)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HvssUser checkUserIdExists(UserRegisterData userRegisterData) {
        String query = "from " + entityClass.getName() + " c where username = :username or email_address = :email";
        Query q = entityManager.createQuery(query);
        q.setParameter("username", userRegisterData.getUsername());
        q.setParameter("email", userRegisterData.getEmailAddress());
        try {
            return (HvssUser) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public HvssUser findUserByUsernameAndPassword(UserAuthenticationData userAuthenticationData) {
        String query = "from " + entityClass.getName() + " c where username = :username AND password = :password";
        Query q = entityManager.createQuery(query);
        q.setParameter("username", userAuthenticationData.getUsername());
        q.setParameter("password", userAuthenticationData.getPassword());

        try {
            return (HvssUser) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }catch (PersistenceException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public HvssUser findUserByUserTokenId(Long userTokenId) {
        String query = "from " + entityClass.getName() + " c where USER_TOKEN_ID = :userTokenId";
        Query q = entityManager.createQuery(query);
        q.setParameter("userTokenId",userTokenId);

        try {
            return (HvssUser) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }catch (PersistenceException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public HvssUser findUserByRememberTokenId(String rememberToken){
        String query = "from " + entityClass.getName() + " c where LOGIN_REMEMBER_TOKEN = :rememberLoginToken";
        Query q = entityManager.createQuery(query);
        q.setParameter("rememberLoginToken",rememberToken);

        try {
            return (HvssUser) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }catch (PersistenceException e){
            return null;
        }
    }
}
