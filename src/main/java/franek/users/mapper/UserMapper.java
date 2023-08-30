package franek.users.mapper;

import franek.users.repository.entity.UserEntity;
import franek.users.rest.dto.UserRestDto;
import franek.users.service.dto.UserDto;

public interface UserMapper {

    UserDto mapEntityToDto(UserEntity entity);

    UserEntity mapDtoToEntity (UserDto userDto);

    UserRestDto mapDtoToRestDto(UserDto userDto);

    UserDto mapRestDtoToDto(UserRestDto userRestDto);

}
