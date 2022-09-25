package com.login.loginUser.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Clase Usuario - contiene la lógica de negocio para la creación de un usuario
 * @author Jonathan Fonseca
 */
@Entity
@Table(name = "`user`")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser" )
    private Long idUser;

    @Column(name = "nameUser" ,nullable = false)
    @NotBlank(message = "The field name is required ")
    private String nameUser;

    @Column(name = "lastNameUser" , nullable = false)
    @NotBlank(message = "The field last name is required ")
    private String lastNameUser;

    @Column(name = "phoneUser" , nullable = false, length = 10)
    @NotBlank(message = "The field phone number is required ")
    private Long phoneUser;

    @Column(name = "mailUser" , nullable = false, unique = true)
    @Email
    @NotBlank(message = "The field mail user is required ")
    private String mailUser;

    @Column(name = "password" , nullable = false)
    @Pattern(regexp = "\\A(?=\\S*?[0-9])(?=\\S*?[a-z])(?=\\S*?[A-Z])(?=\\S*?[@#$%^&+=])\\S{8,15}\\z",
            message = "La contraseña debe tener una longitud mínima de 8 caracteres y máxima de 15 " +
                      " debe contener letras mayúsculas minúscula, número y un carácter como @#$%^&")
    @NotBlank(message = "The field password is required ")
    private String password;

    @Transient
    private String confirmPassword;

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", nameUser='" + nameUser + '\'' +
                ", lastNameUser='" + lastNameUser + '\'' +
                ", phoneUser=" + phoneUser +
                ", mailUser='" + mailUser + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
