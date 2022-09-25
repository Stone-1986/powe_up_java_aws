package com.login.loginUser.service.implementation;

import com.login.loginUser.controller.exeption.BadRequestException;
import com.login.loginUser.model.User;
import com.login.loginUser.repository.UserRepository;
import com.login.loginUser.service.contract.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserDAOImplement extends GenericDAOImplement<User, UserRepository> implements UserDAO {

    @Autowired
    public UserDAOImplement(UserRepository repository) {
        super(repository);
    }

    /**
     * Retorna un usuario consultado por nombre y apellido
     * @param nameUser nombre de usuario
     * @param lastNameUser apellido de usuario
     * @return usuario
     */
    @Override
    @Transactional(readOnly =true)
    public Optional<User> findUserByNameAndSurname(String nameUser, String lastNameUser) {
        return repository.findUserByNameAndSurname(nameUser, lastNameUser);
    }

    /**
     * Retorna un usuario consultado por su correo electrónico del usuario
     * @param mailUser correo electrónico del usuario
     * @return usuario
     */
    @Override
    @Transactional(readOnly =true)
    public Optional<User> findUserByEmail(String mailUser) {
        return repository.findUserByEmail(mailUser);
    }

    /**
     * Permite Actualizar el correo y/o número de telefono de un usuario
     * @param idUser id de usuario, no debe ser nulo
     * @param user usuario
     * @return un usuario con los datos de correo y/o número  actualizados
     * @throws BadRequestException - caso se intente actualizar un usuario no existente
     */
    @Override
    @Transactional
    public User updateUser(Long idUser, User user) throws BadRequestException {
       User userUp = repository.findById(idUser).orElseThrow(
               ()-> new BadRequestException(String.format("El usuario con id %d no existe::", idUser))
       );
       userUp.setPhoneUser(user.getPhoneUser());
       userUp.setMailUser(user.getMailUser());

        return repository.save(userUp);
    }

}
