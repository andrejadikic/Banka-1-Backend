package rs.edu.raf.banka1.mapper;

import org.springframework.stereotype.Component;
import rs.edu.raf.banka1.model.User;
import rs.edu.raf.banka1.requests.CreateUserRequest;
import rs.edu.raf.banka1.requests.EditUserRequest;
import rs.edu.raf.banka1.responses.UserResponse;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private PermissionMapper permissionMapper;

    public UserMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public UserResponse userToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhoneNumber(user.getPhoneNumber());
        userResponse.setPosition(user.getPosition());
        userResponse.setActive(user.getActive());
        userResponse.setPermissions(user.getPermissions().stream().map(permissionMapper::permissionToPermissionDto).
                collect(Collectors.toList()));
        return userResponse;
    }

    public User userResponseToUser(UserResponse userResponse) {
        User user = new User();
        user.setFirstName(userResponse.getFirstName());
        user.setLastName(userResponse.getLastName());
        user.setEmail(userResponse.getEmail());
        user.setPhoneNumber(userResponse.getPhoneNumber());
        user.setPosition(userResponse.getPosition());
        user.setActive(userResponse.getActive());
        user.setPermissions(userResponse.getPermissions().stream().map(permissionMapper::permissionDtoToPermission).
                collect(Collectors.toSet()));
        return user;
    }

    public User createUserRequestToUser(CreateUserRequest createUserRequest) {
        User user = new User();
        user.setFirstName(createUserRequest.getFirstName());
        user.setLastName(createUserRequest.getLastName());
        user.setEmail(createUserRequest.getEmail());
        user.setJmbg(createUserRequest.getJmbg());
        user.setPhoneNumber(createUserRequest.getPhoneNumber());
        user.setPosition(createUserRequest.getPosition());
        user.setActive(createUserRequest.getActive());
        user.setPassword(UUID.randomUUID().toString());
        return user;
    }

    public User editUserRequestToUser(User user, EditUserRequest editUserRequest) {
        user.setPassword(editUserRequest.getPassword());
        user.setFirstName(editUserRequest.getFirstName());
        user.setLastName(editUserRequest.getLastName());
        user.setJmbg(editUserRequest.getJmbg());
        user.setPosition(editUserRequest.getPosition());
        user.setPhoneNumber(editUserRequest.getPhoneNumber());
        user.setActive(editUserRequest.isActive());
        return user;
    }

    public EditUserRequest userToEditUserRequest(User user) {
        EditUserRequest editUserRequest = new EditUserRequest();
        editUserRequest.setFirstName(user.getFirstName());
        editUserRequest.setLastName(user.getLastName());
        editUserRequest.setEmail(user.getEmail());
        editUserRequest.setJmbg(user.getJmbg());
        editUserRequest.setPhoneNumber(user.getPhoneNumber());
        editUserRequest.setPosition(user.getPosition());
        editUserRequest.setActive(user.getActive());
        editUserRequest.setPermissions(user.getPermissions().stream().map(permission -> permission.getName()).collect(Collectors.toList()));
        editUserRequest.setUserId(user.getUserId());
        editUserRequest.setPassword(user.getPassword());
        return editUserRequest;
    }
}
