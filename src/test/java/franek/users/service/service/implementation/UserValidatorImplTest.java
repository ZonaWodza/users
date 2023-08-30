package franek.users.service.service.implementation;

import franek.users.service.dto.UserDto;
import franek.users.service.exceptions.ValidationException;
import franek.users.service.service.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserValidatorImplTest {

    UserValidator userValidator = new UserValidatorImpl();

    @Test
    void shouldThrowErrorWhenEmailInWrongFormat() {
        //given
        UserDto userDto = new UserDto();
        userDto.setBirthDate(LocalDate.now().minusYears(10));
        userDto.setEmail("wrong format");

        //when
        Assertions.assertThrows(ValidationException.class, ()-> this.userValidator.isUserValid(userDto));
    }

    @Test
    void shouldThrowErrorWhenBirthDateInTheFuture() {
        //given
        UserDto userDto = new UserDto();
        userDto.setBirthDate(LocalDate.now().plusDays(1));
        userDto.setEmail("good@format.com");

        //when
        Assertions.assertThrows(ValidationException.class, ()-> this.userValidator.isUserValid(userDto));
    }

    @Test
    void isUserValidTest() {
        //given
        UserDto userDto = new UserDto();
        userDto.setBirthDate(LocalDate.now().minusYears(10));
        userDto.setEmail("good@format.com");

        //when
        Assertions.assertTrue(this.userValidator.isUserValid(userDto));
    }

}