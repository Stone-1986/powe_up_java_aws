package com.login.loginUser.repository;

import com.login.loginUser.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 *  Interfaz genérica que recibe dos argumentos, el primero es la clase  que esta interfaz manejará (User)
 *  y el segundo es el tipo de dato del ID de la entidad. Permite el acceso a los datos de la clase User
 * @author Jnathan Fonseca
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {

     @Query("select u from User u where u.nameUser = ?1 and u.lastNameUser = ?2")
     Optional<User> findUserByNameAndSurname(String nameUser, String lastNameUser);

     @Query("select u from User u where u.mailUser = ?1")
     Optional<User> findUserByEmail(String mailUser);
}
