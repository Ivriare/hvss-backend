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
import org.springframework.transaction.UnexpectedRollbackException;
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
    public HvssUserToken getTokenIdByTokenUid(String oid, String sessionToken) {
        String query = "from " + entityClass.getName() + "  c where oid = :oid AND session_token = :sessionToken";
        Query q = entityManager.createQuery(query);
        q.setParameter("oid",  oid == null ? "" : oid);
        q.setParameter("sessionToken", sessionToken == null ? "" : sessionToken);

        try {
            if(q.getResultList().size() > 0){
                return (HvssUserToken) q.getResultList().get(0);
            } else {
                return null;
            }
        } catch (PersistenceException | UnexpectedRollbackException e){
            return null;
        }
    }
}
