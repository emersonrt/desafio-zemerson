
package com.emersonrt.desafio.zemerson.utils.cruddao;

/**
 *
 * @author emerson
 * @param <T>
 */

public interface CrudDao<T> {

    void create(T obj);
    
    T find(Object id);

    T update(T obj);

    void delete(Object id);
    
}
