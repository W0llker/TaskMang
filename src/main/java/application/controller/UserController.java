package application.controller;

import api.controller.CrudControllerApi;
import api.controller.UserControllerApi;
import api.dto.AuthenticationRequest;
import api.dto.AuthenticationResponse;
import api.dto.exception.ExceptionResponse;
import api.dto.user.UserRequest;
import api.dto.user.UserResponse;
import application.security.JwtTokenUtils;
import application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController extends CrudControllerApi<UserRequest, UserResponse> implements UserControllerApi {
    private final UserService service;
    private JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserController(UserService service, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        super(service);
        this.service = service;
        this.jwtTokenUtils = jwtTokenUtils;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public ResponseEntity<?> login(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), "Неправильный пароль или логин"), HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = service.loadUserByUsername(request.getEmail());
        String token = jwtTokenUtils.generatedToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(request.getEmail(), token));
    }
}
