package franek.users.service.service;

import franek.users.service.dto.UserDto;

public interface UserValidator {

    boolean isUserValid(UserDto userDto);
}
