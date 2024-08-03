package sn.xarandev.thymeleaf_springboot.service;

import sn.xarandev.thymeleaf_springboot.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<UserDto> getAll();
    UserDto save(UserDto userDto);
    Optional<UserDto> login (String email, String password);
}
