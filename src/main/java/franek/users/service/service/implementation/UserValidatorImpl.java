package franek.users.service.service.implementation;

import franek.users.service.dto.UserDto;
import franek.users.service.exceptions.ValidationException;
import franek.users.service.service.UserValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.regex.Pattern;

@Service
public class UserValidatorImpl implements UserValidator {

    private static final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    @Override
    public boolean isUserValid(UserDto userDto) {
        if (userDto.getBirthDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Birth date cannot be in the future");
        }
        if (userDto.getEmail() == null || !Pattern.compile(EMAIL_REGEX).matcher(userDto.getEmail()).matches()) {
            throw new ValidationException("Wrong email format");
        }
        return true;
    }
}
