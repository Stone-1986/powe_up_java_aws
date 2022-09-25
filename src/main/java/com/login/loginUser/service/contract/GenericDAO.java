package com.login.loginUser.service.contract;

import com.login.loginUser.controller.exeption.BadRequestException;

import java.util.Map;

/**
 * Interfaz para operaciones CRUD genéricas
 * @param <E> Entidad
 */
public interface GenericDAO<E> {

   /**
    * Permite Consultar una entidad dado su id
    * @param id id Entidad
    * @return entidad consultada
    * @throws BadRequestException - en caso de que la entidad no exista
    */
   E findById(Long id);

   /**
    * Guarda una entidad determinad
    * @param entity Entidad no debe ser nulo
    * @return entidad guardada - nunca sera nula
    */
   E save(E entity);

   /**
    * Devuelve todas las instancias del tipo.
    * @return  Todas entidades de un tipo
    */
   Iterable<E> findAll();

   /**
    * Elimina una entidad dado su ID
    * @param id id Entidad
    * @return retorna un Map <"delete", True>
    * @throws BadRequestException - en caso de que la entidad no exista
    */
   Map<String, Boolean>  deleteById(Long id);
}
