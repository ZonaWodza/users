package franek.users.repository.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="USER_TABLE")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="NAME", nullable = false)
    private String name;

    @Column(name="FIRST_SURNAME", nullable = false)
    private String firstSurname;

    @Column(name="SECOND_SURNAME")
    private String secondSurname;

    @Column(name="EMAIL", nullable = false)

    private String email;

    @Column(name="PASSWORD", nullable = false)

    private String password;

    @Column(name="BIRTH_DATE", nullable = false)

    private LocalDate birthDate;

    @Column(name="REGISTRATION_DATE", nullable = false)

    private LocalDate registrationDate;

    @Column(name="PHONE_NUMBER", nullable = false)

    private String phoneNumber;
}
