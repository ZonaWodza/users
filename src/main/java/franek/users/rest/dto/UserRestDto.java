package franek.users.rest.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRestDto {


    private Long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String password;
    private LocalDate birthDate;
    private LocalDate registrationDate;
    private String phoneNumber;
    private boolean isUnderage;
    private boolean isElder;

}
