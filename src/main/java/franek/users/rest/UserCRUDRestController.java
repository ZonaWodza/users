package franek.users.rest;


import franek.users.rest.constants.RestConstants;
import franek.users.rest.dto.UserRestDto;
import franek.users.service.service.UserCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserCRUDRestController {

    private final UserCRUDService userCRUDService;

    @GetMapping(RestConstants.USER_ID_PATH_VARIABLE)
    public UserRestDto getUser(@PathVariable Long id) {
        return userCRUDService.getUserRestById(id);
    }

    @GetMapping(RestConstants.USERS)
    public List<UserRestDto> getUsers() {
        return userCRUDService.getAllUsersRest();
    }

    @PostMapping(RestConstants.USER)
    public UserRestDto createUser(@RequestBody UserRestDto userToCreate) {
        return this.userCRUDService.createUser(userToCreate);
    }

    @PutMapping(RestConstants.UPDATE_ID_PATH_VARIABLE)
    public UserRestDto updateUser(@RequestBody UserRestDto userToUpdate) {
        return this.userCRUDService.createUser(userToUpdate);
    }

    @DeleteMapping(RestConstants.USER_ID_PATH_VARIABLE)
    public void deleteUser(@PathVariable Long id) {
        this.userCRUDService.deleteUser(id);
    }

}
