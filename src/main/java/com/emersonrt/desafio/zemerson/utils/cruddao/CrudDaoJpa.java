package com.emersonrt.desafio.zemerson.utils.cruddao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 *
 * @author emerson
 * @param <T>
 */

@Transactional
public abstract class CrudDaoJpa<T> implements CrudDao<T> {

    @PersistenceContext
    private EntityManager em;
    
    private final Class<T> entityClass;

    public CrudDaoJpa(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    
    protected EntityManager getEntityManager() {
        return this.em;
    }
    
    @Override
    public void create(T entity) {
        getEntityManager().persist(entity);
    }
    
    @Override
    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public T update(T entity) {
        return getEntityManager().merge(entity);
    }

    @Override
    public void delete(Object id) {
        T entity = getEntityManager().getReference(entityClass, id);
        getEntityManager().remove(entity);
    }

}
