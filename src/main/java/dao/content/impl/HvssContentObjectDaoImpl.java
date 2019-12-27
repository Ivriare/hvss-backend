package dao.content.impl;

import dao.content.HvssContentObjectDao;
import dao.content.HvssUserDao;
import dao.factory.HvssDaoImpl;
import dto.user.UserAuthenticationData;
import dto.user.UserRegisterData;
import entities.HvssContentObject;
import entities.user.HvssUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

@Component
public class HvssContentObjectDaoImpl extends HvssDaoImpl<Long, HvssContentObject> implements HvssContentObjectDao {

    @Autowired
    EntityManager entityManager;

    @Transactional(readOnly=true)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public <T extends HvssContentObject> T getContentObject(String uid) {
        String query = "from " + entityClass.getName() + " c where uid = :uid";
        Query q = entityManager.createQuery(query);
        q.setParameter("uid", uid);
        try {
            return (T) q.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}
