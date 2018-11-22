package ua.lviv.iot.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public abstract class AbstractDao<T> implements IDao<T> {
    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    protected abstract Class<T> getEntityClass();

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public T findById(final Integer id) {
        return (T) entityManager.find(getEntityClass(), id);
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public T persist(T object) {
        entityManager.persist(object);
        return object;
    }

    @Transactional(value = Transactional.TxType.REQUIRED)
    @Override
    public void remove(final Integer id) {
        T result = (T) entityManager.find(getEntityClass(), id);
        entityManager.remove(result);
    }

}