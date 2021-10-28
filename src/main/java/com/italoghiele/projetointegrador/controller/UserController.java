package com.italoghiele.projetointegrador.controller;

import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.UserResponse;
import com.italoghiele.projetointegrador.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//@Secured({ Roles.USER_MANAGER })
public class UserController extends AbstractController {

    private final UserService userService;

    @PostMapping
    public UserResponse create(@Valid @NotNull @RequestBody UserRequest request){
        return userService.create(request);
    }

    @GetMapping(value = "/{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/email")
    public UserResponse findByEmail(@RequestBody String email) {
        return userService.findByEmail(email);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public UserResponse updateById(@PathVariable Long id, @Valid @NotNull @RequestBody UserRequest request) {
        return userService.updateById(id, request);
    }
}