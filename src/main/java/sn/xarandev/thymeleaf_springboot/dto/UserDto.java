package sn.xarandev.thymeleaf_springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.xarandev.thymeleaf_springboot.entities.RoleEnum;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto implements Serializable {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private RoleEnum role;

}
