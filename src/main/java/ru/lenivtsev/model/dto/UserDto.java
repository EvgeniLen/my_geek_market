package ru.lenivtsev.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.lenivtsev.model.Basket;
import ru.lenivtsev.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    @NotBlank(message = "can not be empty!!!")
    private String userName;

    @NotBlank(message = "can not be empty!!!")
    private String firstName;

    @NotBlank(message = "can not be empty!!!")
    private String lastName;

    @NotBlank
    @Email
    private String email;

    //@Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    private String password;

    @JsonIgnore
    private String matchingPassword;

    private Set<Role> roles;


    public UserDto(Long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
