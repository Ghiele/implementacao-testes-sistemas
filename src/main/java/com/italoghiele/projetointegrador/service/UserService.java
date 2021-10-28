package com.italoghiele.projetointegrador.service;

import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.response.UserResponse;
import com.italoghiele.projetointegrador.security.UserSS;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService {

    UserResponse create(@NotNull @RequestBody UserRequest request);

    UserResponse findById(@NotNull Long id);

    UserResponse findByEmail(@NotNull String email);

    List<UserResponse> findAll();

    void deleteById(@NotNull Long id);

    UserResponse updateById(@NotNull Long id, @NotNull @RequestBody UserRequest request);
}
