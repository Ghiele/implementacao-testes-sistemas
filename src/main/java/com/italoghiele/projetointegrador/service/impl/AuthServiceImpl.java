package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.User;
import com.italoghiele.projetointegrador.repository.UserRepository;
import com.italoghiele.projetointegrador.service.AuthService;
import com.italoghiele.projetointegrador.service.EmailService;
import com.italoghiele.projetointegrador.service.exceptions.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.transaction.Transactional;
import java.util.Random;

@Service
@Validated
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){
        User user = userRepository.findOneByEmailIgnoreCaseAndDeletedFalse(email).orElse(null);
        if(user == null){
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        System.out.println("PASS: " + newPass);
        System.out.println("USER: " + user);

        user.setPassword(bCryptPasswordEncoder.encode(newPass));

        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0){
            return (char) (random.nextInt(10) + 48);
        } else if (opt == 1) {
            return (char) (random.nextInt(26) + 65);
        } else {
            return (char) (random.nextInt(26) + 97);
        }
    }
}
