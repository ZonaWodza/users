package franek.users.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private Long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String phoneNumber;
}
