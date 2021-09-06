package dao.content.impl;

import dao.content.HvssContentDao;
import dao.content.HvssUserDao;
import dao.factory.HvssDaoImpl;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.HvssTypeEnum;
import entities.content.HvssContent;
import entities.user.HvssUser;
import entities.user.HvssUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Component
public class HvssUserDaoImpl extends HvssDaoImpl<Long, HvssUser> implements HvssUserDao {

    @Autowired
    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        q.setParameter("password", passwordEncoder.encode(userAuthenticationData.getPassword()));

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
        String query = "from " + entityClass.getName() + " c where user_token_id = :userTokenId";
        Query q = entityManager.createQuery(query);
        q.setParameter("userTokenId",userTokenId == null ? "" : userTokenId);

        try {
            if(q.getResultList().size() > 0){
                return (HvssUser) q.getResultList().get(0);
            } else {
                return null;
            }
        } catch (NoResultException e){
            return null;
        }catch (PersistenceException e){
            return null;
        } catch (UnexpectedRollbackException e){
            return null;
        }
    }

    @Override
    @Transactional(readOnly=true)
    public HvssUser findUserByRememberTokenId(String rememberToken){
        String query = "from " + entityClass.getName() + " c where login_remember_token = :rememberLoginToken";
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

    @Override
    @Transactional(readOnly=true)
    public HvssUser findUserByEmail(String email){
        String query = "from " + entityClass.getName() + " c where email_address = :email";
        Query q = entityManager.createQuery(query);
        q.setParameter("email",email);

        try {
            return (HvssUser) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }catch (PersistenceException e){
            return null;
        }
    }
}
