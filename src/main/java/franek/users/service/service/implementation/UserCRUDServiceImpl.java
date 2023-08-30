package franek.users.service.service.implementation;

import franek.users.mapper.UserMapper;
import franek.users.repository.UserRepository;
import franek.users.repository.entity.UserEntity;
import franek.users.rest.dto.UserRestDto;
import franek.users.service.dto.UserDto;
import franek.users.service.service.UserCRUDService;
import franek.users.service.service.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCRUDServiceImpl implements UserCRUDService {

    private static final int ELDER_AGE = 60;

    private static final int ADULT_AGE = 18;

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserValidator userValidator;

    @Override
    public List<UserRestDto> getAllUsersRest() {
        List<UserEntity> userEntities = this.userRepository.findAll();
        return userEntities
                .stream()
                .map(this.userMapper::mapEntityToDto)
                .map(this.userMapper::mapDtoToRestDto)
                .map(this::adjustUserForAge)
                .collect(Collectors.toList());
    }

    @Override
    public UserRestDto getUserRestById(Long id) {

        UserDto userFromDb = this.userMapper.mapEntityToDto(
                this.userRepository.findById(id)
                        .orElseThrow(NoSuchElementException::new));
        UserRestDto userToReturn = this.userMapper.mapDtoToRestDto(userFromDb);
        return this.adjustUserForAge(userToReturn);
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity userToDelete = this.userRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
        this.userRepository.delete(userToDelete);
    }

    @Override
    public UserRestDto updateUser(UserRestDto userRestDto) {
        this.userRepository.findById(userRestDto.getId())
                .orElseThrow(NoSuchElementException::new);
        UserDto userDtoToUpdate = this.userMapper.mapRestDtoToDto(userRestDto);
        this.userValidator.isUserValid(userDtoToUpdate);
        UserEntity userToUpdate = this.userMapper.mapDtoToEntity(userDtoToUpdate);
        UserDto userToReturn = this.userMapper
                .mapEntityToDto(this.userRepository.save(userToUpdate));
        return this.adjustUserForAge(this.userMapper.mapDtoToRestDto(userToReturn));
    }

    @Override
    public UserRestDto createUser(UserRestDto userRestDto) {
        UserDto userToSave = this.userMapper.mapRestDtoToDto(userRestDto);
        this.userValidator.isUserValid(userToSave);
        UserEntity userEntityToSave = this.userMapper.mapDtoToEntity(userToSave);
        UserDto savedUser = this.userMapper.mapEntityToDto(this.userRepository.save(userEntityToSave));
        return this.adjustUserForAge(this.userMapper.mapDtoToRestDto(savedUser));
    }

    private UserRestDto adjustUserForAge(UserRestDto userToBeSet) {
        int actualAge = this.countActualAge(userToBeSet.getBirthDate());

        if (actualAge > ELDER_AGE) {
            userToBeSet.setElder(true);
        } else if (actualAge < ADULT_AGE) {
            userToBeSet.setPhoneNumber(null);
            userToBeSet.setUnderage(true);
        }
        return userToBeSet;
    }

    private int countActualAge(LocalDate birthDate) {
        LocalDate todayDate = LocalDate.now();
        int actualAge = todayDate.getYear() - birthDate.getYear();
        if (birthDate.isAfter(todayDate.withYear(birthDate.getYear()))) {
            actualAge--;
        }
        return actualAge;
    }
}
