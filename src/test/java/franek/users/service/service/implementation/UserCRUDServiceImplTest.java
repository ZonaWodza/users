package franek.users.service.service.implementation;

import franek.users.mapper.UserMapper;
import franek.users.mapper.UserMapperImpl;
import franek.users.repository.UserRepository;
import franek.users.repository.entity.UserEntity;
import franek.users.rest.dto.UserRestDto;
import franek.users.service.service.UserValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserCRUDServiceImplTest {


    @Mock
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<UserEntity> userEntityArgumentCaptor;

    UserMapper userMapper = new UserMapperImpl();

    UserValidator userValidator = new UserValidatorImpl();

    UserCRUDServiceImpl userCRUDService;

    @BeforeEach()
    void setUp() {
        this.userCRUDService = new UserCRUDServiceImpl(this.userRepository, this.userMapper, this.userValidator);
    }

    @Test
    void getAllUsersRestTest() {

        //given
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.add(this.createUserEntityWithBirthDate(LocalDate.now().minusYears(20)));
        userEntities.add(this.createUserEntityWithBirthDate(LocalDate.now().minusYears(20)));
        userEntities.add(this.createUserEntityWithBirthDate(LocalDate.now().minusYears(20)));

        Mockito.when(this.userRepository.findAll())
                .thenReturn(userEntities);

        //when
        List<UserRestDto> resultList = this.userCRUDService.getAllUsersRest();

        //then
        Assertions.assertEquals(3, resultList.size());
        Mockito.verify(this.userRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(this.userRepository);

    }


    @Test
    void isNotUnderageWhenAgeIsExactly18() {

        //given
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(
                                this.createUserEntityWithBirthDate(LocalDate.now().minusYears(18))));

        //when
        UserRestDto resultUserRestDto = this.userCRUDService.getUserRestById(1L);

        //then
        Assertions.assertFalse(resultUserRestDto.isUnderage());
        Assertions.assertNotNull(resultUserRestDto.getPhoneNumber());

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void isUnderageWhenAgeIsOneDayBelow18() {

        //given
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(
                        this.createUserEntityWithBirthDate(LocalDate.now().minusYears(18).plusDays(1))));

        //when
        UserRestDto resultUserRestDto = this.userCRUDService.getUserRestById(1L);

        //then
        Assertions.assertTrue(resultUserRestDto.isUnderage());
        Assertions.assertNull(resultUserRestDto.getPhoneNumber());

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void isNotElderWhenAgeIsOneDayBefore61() {

        //given
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(
                        this.createUserEntityWithBirthDate(LocalDate.now().minusYears(61).plusDays(1))));

        //when
        UserRestDto resultUserRestDto = this.userCRUDService.getUserRestById(1L);

        //then
        Assertions.assertFalse(resultUserRestDto.isElder());
        Assertions.assertNotNull(resultUserRestDto.getPhoneNumber());

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void isElderWhenAgeIsExactly61() {

        //given
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(
                        this.createUserEntityWithBirthDate(LocalDate.now().minusYears(61))));

        //when
        UserRestDto resultUserRestDto = this.userCRUDService.getUserRestById(1L);

        //then
        Assertions.assertTrue(resultUserRestDto.isElder());
        Assertions.assertNotNull(resultUserRestDto.getPhoneNumber());

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void deleteUserTest() {

        //given
        UserEntity userToDelete = this.createUserEntityWithBirthDate(LocalDate.now().minusYears(20));
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(userToDelete));

        //when
        this.userCRUDService.deleteUser(1L);

        //then
        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(this.userRepository, Mockito.times(1)).delete(userToDelete);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void deleteUserShouldThrowExceptionWhenNoUserWithGivenIdInDB() {

        //given
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.empty());

        //when
        Assertions.assertThrows(NoSuchElementException.class, ()->this.userCRUDService.deleteUser(1L));

        //then
        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }

    @Test
    void updateUserTest() {

        //given
        UserEntity userFromDb = this.createUserEntityWithBirthDate(LocalDate.now().minusYears(30));
        UserRestDto userToUpdate = this.createUserRestDtoWithBirthDate(LocalDate.now().minusYears(10));
        userToUpdate.setName("Updated");
        userToUpdate.setId(1L);
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.of(userFromDb));
        Mockito.when(this.userRepository.save(this.userEntityArgumentCaptor.capture())).thenReturn(this.createUserEntityWithBirthDate(LocalDate.now().minusYears(10)));

        //when
        UserRestDto updatedResult = this.userCRUDService.updateUser(userToUpdate);

        //then
        Assertions.assertEquals("Updated", this.userEntityArgumentCaptor.getValue().getName());
        Assertions.assertNull(updatedResult.getPhoneNumber());

        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void updateUserShouldThrowExceptionWhenNoUserWithGivenIdInDB() {

        //given
        UserRestDto userToUpdate = this.createUserRestDtoWithBirthDate(LocalDate.now().minusYears(20));
        userToUpdate.setId(1L);
        Mockito.when(this.userRepository.findById(1L))
                .thenReturn(Optional.empty());

        //when
        Assertions.assertThrows(NoSuchElementException.class, ()->this.userCRUDService.updateUser(userToUpdate));

        //then
        Mockito.verify(this.userRepository, Mockito.times(1)).findById(1L);
        Mockito.verifyNoMoreInteractions(this.userRepository);
    }


    @Test
    void createUserTest() {

        //given
        UserEntity userEntityToReturn = this.createUserEntityWithBirthDate(LocalDate.now().minusYears(15));

        Mockito.when(this.userRepository.save(userEntityArgumentCaptor.capture())).thenReturn(userEntityToReturn);

        UserRestDto userToCreate = this.createUserRestDtoWithBirthDate(LocalDate.now().minusYears(20));
        userToCreate.setFirstSurname("Created");

        //when
        UserRestDto resultRestDto = this.userCRUDService.createUser(userToCreate);

        //then
        Assertions.assertNull(resultRestDto.getPhoneNumber());
        Assertions.assertEquals("Created", this.userEntityArgumentCaptor.getValue().getFirstSurname());
    }

    private UserEntity createUserEntityWithBirthDate(LocalDate birthDate) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test");
        userEntity.setEmail("test@test.com");
        userEntity.setPhoneNumber("+1234");
        userEntity.setBirthDate(birthDate);
        return userEntity;
    }

    private UserRestDto createUserRestDtoWithBirthDate(LocalDate birthDate) {
        UserRestDto userRestDto = new UserRestDto();
        userRestDto.setName("test");
        userRestDto.setEmail("test@test.com");
        userRestDto.setPhoneNumber("+1234");
        userRestDto.setBirthDate(birthDate);
        return userRestDto;
    }
}