package com.login.loginUser.service.implementation;

import com.login.loginUser.controller.exeption.BadRequestException;
import com.login.loginUser.service.contract.GenericDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.Map;

public class GenericDAOImplement <E, R extends CrudRepository<E, Long>> implements GenericDAO<E> {

    protected final R repository;

    public GenericDAOImplement(R repository) {
        this.repository = repository;
    }

    /**
     * Permite Consultar una entidad dado su id
     * @param id id Entidad
     * @return entidad consultada
     * @throws BadRequestException - en caso de que la entidad no exista
     */
    @Override
    @Transactional(readOnly = true)
    public E findById(Long id) throws BadRequestException {
        return repository.findById(id).orElseThrow(
                ()-> new BadRequestException(String.format("El usuario con id %d no existe::", id)));
    }

    /**
     * Guarda una entidad determinad
     * @param entity Entidad no debe ser nulo
     * @return entidad guardada - nunca sera nula
     */
    @Override
    @Transactional
    public E save(E entity) {
        return repository.save(entity);
    }

    /**
     * Devuelve todas las instancias del tipo.
     * @return  Todas entidades de un tipo
     */
    @Override
    @Transactional(readOnly = true)
    public Iterable<E> findAll() {
        return repository.findAll();
    }

    /**
     * Elimina una entidad dado su ID
     * @param id id Entidad
     * @return retorna un Map <"delete", True>
     * @throws BadRequestException - en caso de que la entidad no exista
     */
    @Override
    @Transactional
    public Map<String, Boolean>  deleteById(Long id) throws BadRequestException {
        E optionalE = repository.findById(id).orElseThrow(
                ()-> new BadRequestException(String.format("El usuario con id %d no existe::", id)));

        repository.delete(optionalE);
        final Map<String, Boolean> response = new HashMap<>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
