package si.fri.resources;

import jakarta.inject.Inject;
import si.fri.dto.CreateUserDto;
import si.fri.service.UserService;

public class UserResourceImpl implements UserResource {

    @Inject
    UserService userService;

    @Override
    public String createUser(CreateUserDto createUserDto) {

        return userService.createUser(createUserDto);
    }

}
