package franek.users.mapper;

import franek.users.repository.entity.UserEntity;
import franek.users.rest.dto.UserRestDto;
import franek.users.service.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper{



    @Override
    public UserDto mapEntityToDto(UserEntity entity) {
        UserDto userDto = new UserDto();

        userDto.setId(entity.getId());
        userDto.setName(entity.getName());
        userDto.setFirstSurname(entity.getFirstSurname());
        userDto.setSecondSurname(entity.getSecondSurname());
        userDto.setEmail(entity.getEmail());
        userDto.setPassword(entity.getPassword());
        userDto.setBirthDate(entity.getBirthDate());
        userDto.setRegistrationDate(entity.getRegistrationDate());
        userDto.setPhoneNumber(entity.getPhoneNumber());
        return userDto;
    }

    @Override
    public UserEntity mapDtoToEntity(UserDto userDto) {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(userDto.getId());
        userEntity.setName(userDto.getName());
        userEntity.setFirstSurname(userDto.getFirstSurname());
        userEntity.setSecondSurname(userDto.getSecondSurname());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(userDto.getPassword());
        userEntity.setBirthDate(userDto.getBirthDate());
        userEntity.setRegistrationDate(userDto.getRegistrationDate());
        userEntity.setPhoneNumber(userDto.getPhoneNumber());
        return userEntity;
    }

    @Override
    public UserRestDto mapDtoToRestDto(UserDto userDto) {
        UserRestDto userRestDto = new UserRestDto();

        userRestDto.setId(userDto.getId());
        userRestDto.setName(userDto.getName());
        userRestDto.setFirstSurname(userDto.getFirstSurname());
        userRestDto.setSecondSurname(userDto.getSecondSurname());
        userRestDto.setEmail(userDto.getEmail());
        userRestDto.setPassword(userDto.getPassword());
        userRestDto.setBirthDate(userDto.getBirthDate());
        userRestDto.setRegistrationDate(userDto.getRegistrationDate());
        userRestDto.setPhoneNumber(userDto.getPhoneNumber());
        return userRestDto;
    }

    @Override
    public UserDto mapRestDtoToDto(UserRestDto userRestDto) {
        UserDto userDto = new UserDto();

        userDto.setId(userRestDto.getId());
        userDto.setName(userRestDto.getName());
        userDto.setFirstSurname(userRestDto.getFirstSurname());
        userDto.setSecondSurname(userRestDto.getSecondSurname());
        userDto.setEmail(userRestDto.getEmail());
        userDto.setPassword(userRestDto.getPassword());
        userDto.setBirthDate(userRestDto.getBirthDate());
        userDto.setRegistrationDate(userRestDto.getRegistrationDate());
        userDto.setPhoneNumber(userRestDto.getPhoneNumber());
        return userDto;
    }
}
