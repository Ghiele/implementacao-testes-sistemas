package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.repository.UserRepository;
import com.italoghiele.projetointegrador.security.UserSS;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findOneByEmailIgnoreCaseAndDeletedFalse(email).orElse(null);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        UserSS userSS = UserSS.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(user.getProfiles().stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toSet()))
                .build();
        return userSS;
    }
}
