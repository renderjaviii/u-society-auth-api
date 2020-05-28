package common.authentication.app.rest;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import common.authentication.app.api.ApiError;
import common.authentication.app.api.UserApi;
import common.authentication.app.rest.request.CreateUserRequest;
import common.authentication.app.rest.response.CreateUserResponse;
import common.authentication.domain.exception.GenericException;
import common.authentication.domain.exception.UserValidationException;
import common.authentication.domain.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Validated
@RestController
@RequestMapping(path = "v1")
public class UserController extends CommonController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create user.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "User created."),
            @ApiResponse(code = 400, message = "Input data error.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @PostMapping(path = "/",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreateUserResponse> create(@Valid @RequestBody final CreateUserRequest request)
            throws GenericException {
        return new ResponseEntity<>(userService.create(request), CREATED);
    }

    @ApiOperation(value = "User logged in token info.")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "User logged in."),
            @ApiResponse(code = 400, message = "Input data error.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @GetMapping(path = "/token-info/",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Authentication> getTokenInfo(
            @ApiParam(value = "Username") @NotNull @RequestParam(value = "username") final String username)
            throws UserValidationException {
        validateUser(username);
        return new ResponseEntity<>(userService.getTokenInfo(), OK);
    }

    @ApiOperation(value = "Verify email.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User data."),
            @ApiResponse(code = 400, message = "Input data error.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @PostMapping(path = "/{username}/verify-email",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> verifyEmail(@PathVariable(value = "username") final String username,
                                            @RequestParam(name = "otpCode") final String otpCode)
            throws GenericException {
        userService.enableAccount(username, otpCode);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAuthority('ADMIN_PRIVILEGE')")
    @ApiOperation(value = "Get user.")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "User data."),
            @ApiResponse(code = 400, message = "Input data error.", response = ApiError.class),
            @ApiResponse(code = 500, message = "Internal server error.", response = ApiError.class) })
    @GetMapping(path = "/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserApi> get(@PathVariable(value = "username") final String username)
            throws GenericException {
        validateUser(username);
        return ResponseEntity.ok(userService.get(username));
    }

}
