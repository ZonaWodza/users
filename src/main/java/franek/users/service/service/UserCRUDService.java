package franek.users.service.service;

import franek.users.rest.dto.UserRestDto;

import java.util.List;

public interface UserCRUDService {

    List<UserRestDto> getAllUsersRest();
    UserRestDto getUserRestById(Long id);

    void deleteUser(Long id);

    UserRestDto updateUser(UserRestDto userRestDto);

    UserRestDto createUser(UserRestDto userDto);

}
