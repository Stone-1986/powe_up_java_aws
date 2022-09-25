package com.login.loginUser.service.contract;

import com.login.loginUser.model.User;

import java.util.Optional;

/**
 *  Esta Interfaz permite establece los métodos necesarios
 *  para resolver la lógica de negocio dentro de nuestra aplicación
 *  @author Jonathan Fonseca Clavijo
 */
public interface UserDAO extends GenericDAO<User> {

   /**
    * Retorna un usuario consultado por nombre y apellido
    * @param nameUser nombre de usuario
    * @param lastNameUser apellido de usuario
    * @return usuario
    */
   Optional<User> findUserByNameAndSurname(String nameUser, String lastNameUser);

   /**
    * Retorna un usuario consultado por su correo electrónico del usuario
    * @param mailUser correo electrónico del usuario
    * @return usuario
    */
   Optional<User> findUserByEmail(String mailUser);

   User updateUser(Long idUser, User user);


}
