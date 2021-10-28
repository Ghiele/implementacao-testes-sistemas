package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.EmailRequest;
import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.UserResponse;
import com.italoghiele.projetointegrador.security.JWTUtil;
import com.italoghiele.projetointegrador.security.UserSS;
import com.italoghiele.projetointegrador.service.AuthService;
import com.italoghiele.projetointegrador.service.UserService;
import com.italoghiele.projetointegrador.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("auth")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class AuthController extends AbstractController {

    private final JWTUtil jwtUtil;

    private final AuthService authService;


    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response){
        UserSS userSS = UserServiceImpl.getAuthenticated();
        String token = jwtUtil.generateToken(userSS.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailRequest request){
        authService.sendNewPassword(request.getEmail());

        return ResponseEntity.noContent().build();
    }

}