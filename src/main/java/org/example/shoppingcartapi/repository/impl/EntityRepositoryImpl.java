package org.example.shoppingcartapi.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.example.shoppingcartapi.repository.EntityRepository;

import java.util.Optional;

/**
 * @author Daniél Garrido
 */
public class EntityRepositoryImpl<T> implements EntityRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T getByIdSimple(Class<T> tClass, Long id) throws EntityNotFoundException {
        Optional<T> objOpt = Optional.ofNullable(entityManager.find(tClass, id));
        if (objOpt.isEmpty()) {
            throw new EntityNotFoundException("Entity was not found for " + tClass.getSimpleName() + "[ID: " + id + "]");
        }
        return objOpt.get();
    }
}
