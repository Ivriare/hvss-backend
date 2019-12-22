package dao.factory;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dao.HvssDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public abstract class HvssDaoImpl<K, E> implements HvssDao<K, E> {

    protected Class<E> entityClass;

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=false)
    public void flush() {
        entityManager.flush();
    }

    @SuppressWarnings("unchecked")
    public HvssDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[1];
    }

    @Transactional(readOnly=false)
    public void persist(E entity) {
        entityManager.persist(entity);
    }

    @Transactional(readOnly=false)
    public void merge(E entity) {
        entityManager.merge(entity);
    }

    @Transactional(readOnly=false)
    public void remove(E entity) {
        entityManager.remove(entity);
    }

    @Transactional(readOnly=true)
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public void rollBack() {
        entityManager.getTransaction().rollback();
    }

    @Transactional(readOnly=true)
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List findAll() {
        String query = "from " + entityClass.getName() + " c";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }
}