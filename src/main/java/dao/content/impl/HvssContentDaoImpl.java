package dao.content.impl;

import dao.content.HvssContentDao;
import dao.factory.HvssDaoImpl;
import entities.content.HvssContent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Component
public class HvssContentDaoImpl extends HvssDaoImpl<Long, HvssContent> implements HvssContentDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional(readOnly=true)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<HvssContent> getContentListByGroupId(Long id) {
        String query = "from " + entityClass.getName() + " c where hvssContentGroup = " + id;
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
}
