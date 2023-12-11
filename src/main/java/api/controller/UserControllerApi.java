package api.controller;

import api.dto.AuthenticationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerApi {
    @PostMapping("/login")
    ResponseEntity login(@RequestBody @Valid AuthenticationRequest authentication);
}
