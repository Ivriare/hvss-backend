package dao.content.impl;

import dao.content.HvssUserDao;
import dao.content.HvssUserTokenDao;
import dao.factory.HvssDaoImpl;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.user.HvssUser;
import entities.user.HvssUserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Component
public class HvssUserTokenDaoImpl extends HvssDaoImpl<Long, HvssUserToken> implements HvssUserTokenDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public HvssUserToken getTokenIdByTokenUid(String uid, String sessionToken) {
        String query = "from " + entityClass.getName() + " c where uid = :uid AND SESSION_TOKEN = :sessionToken";
        Query q = entityManager.createQuery(query);
        q.setParameter("uid", uid);
        q.setParameter("sessionToken", sessionToken);

        try {
            return (HvssUserToken) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        } catch (PersistenceException e){
            return null;
        }
    }
}
