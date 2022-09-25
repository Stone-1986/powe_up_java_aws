package com.login.loginUser.service.implementation;

import com.login.loginUser.model.User;
import com.login.loginUser.repository.UserRepository;
import com.login.loginUser.service.contract.UserDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.login.loginUser.dataTest.Data.user01;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDAOImplementTest {

    UserDAO userDAO;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userDAO = new UserDAOImplement(userRepository);
    }

    @Test
    @DisplayName("Buscar usuario por nombre y apellido")
    void findUserByNameAndSurname() {
        //When
        // Se busca el usuario por nombre y apellido
        userDAO.findUserByNameAndSurname(anyString(), anyString());

        //Then
        // Se retorna los datos del usuario consultado
        verify(userRepository).findUserByNameAndSurname(anyString(), anyString());
    }

    @Test
    @DisplayName("Buscar usuario por correo electrónico")
    void findUserByEmail() {
        //When
        // Se busca el usuario por correo electrónico
        userDAO.findUserByEmail(anyString());

        //Then
        // Se retorna los datos del usuario consultado
        verify(userRepository).findUserByEmail(anyString());
    }

    @Test
    @DisplayName("Guardar un usuario en la base de datos")
    void save() {
        //Given
        // se realiza la solicitud de guardar un usuario
        User userSave = userRepository.save(user01(false)); //expect

        //When
        // Se registran todos los atributos de un usuario
         userRepository.save(userSave);

        //Them
        //se guarda un nuevo usuario
        verify(userRepository).save(any(User.class));
    }

    @Test
    void findById(){

        //GIVEN
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(java.util.Optional.of(user01(true)));

        //When
       Optional<User> userExpected = java.util.Optional.of(userDAO.findById(id));

        //Then
        assertThat(userExpected).isEqualTo(java.util.Optional.of(user01(true)));

    }

}