package com.login.loginUser.repository;

import com.login.loginUser.dataTest.Data;
import com.login.loginUser.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.login.loginUser.dataTest.Data.user01;
import static com.login.loginUser.dataTest.Data.user02;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Buscar usuario por nombre y apellido")
    void findUserByNameAndSurname() {

        //Given
        // Se tiene guardan usuarios en la base de datos
        User userSave = userRepository.save(user01(false));

        //When
        // Se busca el usuario por nombre y apellido
       User expected = userRepository.findUserByNameAndSurname(
               user01(false).getNameUser(),
               user01(false).getLastNameUser()
       ).orElseThrow(()-> new RuntimeException("Usuario nulo"));

       // them
       // Se retorna los datos del usuario consultado
        assertThat(expected).isInstanceOf(User.class);
        assertThat(expected).isEqualTo(userSave);
    }

    @Test
    @DisplayName("Buscar usuario por correo electrónico")
    void findUserByEmail() {

        //Given
        // Se tiene guardan usuarios en la base de datos
       User userSave = userRepository.save(user02());

        //When
        // Se busca el usuario por correo electrónico
        User expected = userRepository.findUserByEmail(Data.user02().getMailUser())
                .orElseThrow(()-> new RuntimeException("Usuario nulo"));

        //Then
        assertThat(expected).isInstanceOf(User.class);
        assertThat(expected).isEqualTo(userSave);
        assertThat(expected.getMailUser()).isEqualTo(userSave.getMailUser());
    }

}