package fr.adservio.crm.utilisateurs.api.web.controllers.v1;

import fr.adservio.crm.utilisateurs.api.config.ModelMapperConfig;
import fr.adservio.crm.utilisateurs.api.domain.User;
import fr.adservio.crm.utilisateurs.api.services.UserService;
import fr.adservio.crm.utilisateurs.api.web.controllers.errors.ResourceNotFound;
import fr.adservio.crm.utilisateurs.api.web.model.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Web entry controller to user ressource
 *
 * @author adservio
 */
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/v1/users")
@Api(value = "users", description = "Provides a simple, api interface to user entities")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Get a paginated response listing the Users.")
    @GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getAllUser(@RequestParam(value = "page", required = false, defaultValue = BaseController.PAGE) Integer page,
                                     @RequestParam(value = "pageSize", required = false, defaultValue = BaseController.PAGESIZE) Integer pageSize) {
        Page<User> userPage = userService.findAllUsers(page, pageSize);
        try {
            return ResponseEntity
                    .ok()
                    .location(new URI("/v1/users/"))
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .body(userPage);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @SneakyThrows
    @ApiOperation(value = "Get a specific user object identified by his id.")
    @GetMapping(value = "/{usedId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity getUserById(@Validated @PathVariable(value = "usedId") Long usedId) {
        User user = userService.findUserById(usedId)
                .orElseThrow(() -> new ResourceNotFound("Invalid user id :" + usedId));
        return ResponseEntity
                .ok()
                .eTag(Long.toString(usedId))
                .location(new URI("/v1/users/" + usedId))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(user);
    }

    @ApiOperation(value = "Delete User by Id.")
    @DeleteMapping(value = "/{usedId}")
    public ResponseEntity removeById(@Validated @PathVariable(value = "usedId") Long usedId) {
        if (!userService.findUserById(usedId).isPresent())
            throw new ResourceNotFound("Invalid user id : " + usedId);
        userService.removeUserById(usedId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Disable a User. If a user is enabled this endpoint will disable him ")
    @PostMapping(value = "/{userId}/disable")
    public ResponseEntity disableUserById(@Validated @PathVariable(value = "usedId") Long usedId) {
        User user = userService.findUserById(usedId)
                .orElseThrow(() -> new ResourceNotFound("Invalid user id :" + usedId));
        if (user.isEnabled()) {
            user.setEnabled(false);
            userService.update(user);
        }
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Enable a User. If a user is disabled this endpoint will enable him ")
    @PostMapping(value = "/{userId}/enable")
    public ResponseEntity enableUserById(@Validated @PathVariable(value = "usedId") Long usedId) {
        User user = userService.findUserById(usedId)
                .orElseThrow(() -> new ResourceNotFound("Invalid user id :" + usedId));
        if (!user.isEnabled()) {
            user.setEnabled(true);
            userService.update(user);
        }
        return ResponseEntity.noContent().build();
    }

    @SneakyThrows
    @ApiOperation(value = "Create a new User.")
    @PostMapping(value = "/create")
    public ResponseEntity createUser(@Validated @RequestBody UserDto userDto) {
        User newUser = convertToEntity(userDto);
        User createdUser = userService.createUser(newUser);
        return ResponseEntity.created(new URI("/v1/users/" + createdUser.getId())).build();
    }

    @ApiOperation(value = "Update an existing User.")
    @PostMapping(value = "/{userId}/userDetailsUpdate")
    public ResponseEntity updateUser(@PathVariable(value = "usedId") Long usedId, @Validated @RequestBody UserDto userDto) {
        User user = userService.findUserById(usedId)
                .orElseThrow(() -> new ResourceNotFound("Invalid user id :" + usedId));
        return ResponseEntity.ok().build();
    }


    private User convertToEntity(UserDto userDto) {
        return  modelMapper.map(userDto, User.class);
    }
}
