package com.login.loginUser.controller;

import com.login.loginUser.controller.exeption.BadRequestException;
import com.login.loginUser.model.User;
import com.login.loginUser.service.contract.UserDAO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * esta clase es que se va a exponer por medio de las URL
 * se Implementan los métodos CRUD
 * @author Jonathan fonseca clavijo
 */
@RestController
@RequestMapping("/signIn")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserDAO userService;


    /**
     * Lanza una Petición POST para la creación de un usuario
     * @param user usuario
     * @return  usuario creado
     */
    @PostMapping
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    /**
     * Lanza una Petición GET para la consulta de un usuario por id
     * @param idUser id del usuario
     * @return Usuario consultado
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> obtainUserById(@PathVariable("id") Long idUser){
        return new ResponseEntity<>(userService.findById(idUser), HttpStatus.OK);
    }

    /**
     * Lanza una Petición PUT para la actualización de un usuario, actualiza el telefono y/o correo electrónico
     * @param idUser id del usuario
     * @param user Usuario
     * @return usuario modificado
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Long idUser, @RequestBody User user){
        return new ResponseEntity<>(userService.updateUser(idUser,user), HttpStatus.OK);
    }

    /**
     * Lanza una Petición DELETE para la eliminación de un usuario por id
     * @param idUser id del usuario
     */
    @DeleteMapping("/{id}")
    public Map<String, Boolean> removeUserById(@PathVariable("id") Long idUser) {
        return userService.deleteById(idUser);
    }

    /**
     * Lanza una Petición GET para la consulta de un usuario por nombre y apellido
     * @param nameUser KEY, nombre del usuario-VALUE
     * @param lastNameUser KEY, apellido del usuario-VALUE
     * @return usuario
     */
    @GetMapping("/nameUser-lastNameUser")
    public ResponseEntity<User>  findUserByNameAndSurname(@RequestParam String nameUser, @RequestParam String lastNameUser){
        Optional<User> oUser = userService.findUserByNameAndSurname(nameUser, lastNameUser);
        if(oUser.isEmpty()) {
            throw new BadRequestException(
                    String.format("No se encontró Persona con nombre %s y apellido %s", nameUser, lastNameUser));
        }
        return new ResponseEntity<>(oUser.get(), HttpStatus.OK);
    }

    /**
     * Lanza una Petición GET para la consulta de un usuario su correo electrónico
     * @param mailUser KEY, Value correo electrónico - VALUE
     * @return usuario
     */
    @GetMapping("/mailUser")
    public ResponseEntity<User> findUserByEmail(@RequestParam String mailUser){
        Optional<User> oUser = userService.findUserByEmail(mailUser);
        if(oUser.isEmpty()) {
            throw new BadRequestException(
                    String.format("No se encontró Persona con el correo %s", mailUser));
        }
        return new ResponseEntity<>(oUser.get(),HttpStatus.OK);
    }


}
