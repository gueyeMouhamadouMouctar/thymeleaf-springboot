package sn.xarandev.thymeleaf_springboot.mapper;


import org.mapstruct.Mapper;
import sn.xarandev.thymeleaf_springboot.dto.UserDto;
import sn.xarandev.thymeleaf_springboot.entities.UserEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDto(UserEntity userEntity);
    UserEntity toUserEntity(UserDto userDto);
    List<UserDto> listUserEntityToListUserDto(List<UserEntity> userEntities);
}
