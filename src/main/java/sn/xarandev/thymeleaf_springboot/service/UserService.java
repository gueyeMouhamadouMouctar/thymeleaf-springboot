package sn.xarandev.thymeleaf_springboot.service;


import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import sn.xarandev.thymeleaf_springboot.dao.IUserDao;
import sn.xarandev.thymeleaf_springboot.dto.UserDto;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import sn.xarandev.thymeleaf_springboot.entities.UserEntity;
import sn.xarandev.thymeleaf_springboot.exception.EntityNotFoundException;
import sn.xarandev.thymeleaf_springboot.mapper.UserMapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@AllArgsConstructor
@Setter
public class UserService implements IUserService {

    private IUserDao userDao ;
    private UserMapper userMapper;
    private MessageSource messageSource;

    @Override
    public UserDto save(UserDto userDto) {
        String email = userDto.getEmail();
        Optional<UserEntity> existingUser = userDao.findByEmail(email);
        if (existingUser.isPresent()) {
            //throw new DuplicateException(String.format("User with the email address '%s' already exists.", email));
        }

        return userMapper.toUserDto(userDao.save(userMapper.toUserEntity(userDto)));
    }

    @Override
    public List<UserDto> getAll() {
        return userMapper.listUserEntityToListUserDto(userDao.findAll());
    }



    @Override
    public Optional<UserDto> login(String email, String password) {
        return userDao.findByEmailAndPassword(email, password)
                .map(userEntity -> Optional.of(userMapper.toUserDto(userEntity))
                ).orElseThrow(
                        () -> new EntityNotFoundException(
                                messageSource.getMessage("user.notfound",
                                        new Object[]{email, password},
                                        Locale.getDefault())
                        ));
    }
}
