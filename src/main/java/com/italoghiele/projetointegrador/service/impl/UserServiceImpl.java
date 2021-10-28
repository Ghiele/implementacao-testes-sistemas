package com.italoghiele.projetointegrador.service.impl;

import com.italoghiele.projetointegrador.domain.*;
import com.italoghiele.projetointegrador.domain.enums.Gender;
import com.italoghiele.projetointegrador.domain.enums.Profile;
import com.italoghiele.projetointegrador.dto.UserRequest;
import com.italoghiele.projetointegrador.dto.converter.UserConverter;
import com.italoghiele.projetointegrador.dto.response.UserResponse;
import com.italoghiele.projetointegrador.repository.*;
import com.italoghiele.projetointegrador.security.UserSS;
import com.italoghiele.projetointegrador.service.AddressService;
import com.italoghiele.projetointegrador.service.PhoneNumberService;
import com.italoghiele.projetointegrador.service.UserService;
import com.italoghiele.projetointegrador.service.exceptions.ObjectNotFoundException;
import com.italoghiele.projetointegrador.validation.MessageKey;
import com.italoghiele.projetointegrador.service.exceptions.AuthorizationException;
import com.italoghiele.projetointegrador.validation.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Validated
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AddressService addressService;
    private final PhoneNumberService phoneNumberService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserResponse create(UserRequest request){
        checkUniqueIdentifiersConflict(request);

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        request.getPhoneNumbers().forEach( pn -> phoneNumbers.add(phoneNumberService.create(pn)));

        List<Address> addresses = new ArrayList<>();
        request.getAddressesRequest().forEach(a -> addresses.add(addressService.findCreate(a)));

        Set<Integer> defaultProfile = new HashSet<>();
        defaultProfile.add(2);

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .phoneNumbers(phoneNumbers)
                .gender(request.getGender())
                .cpf(request.getCpf())
                .language(request.getLanguage())
                .deleted(false)
                .active(true)
                .addresses(addresses)
                .profiles(defaultProfile)
                .build();

        user = userRepository.save(user);

        if(user.getId() == 1) {
            user.addProfile(Profile.ADMIN);
            user = userRepository.save(user);
        }

        return toResponse(user);
    }

    public UserResponse findById(Long id) {
        UserSS userSS = getAuthenticated();

        if(userSS == null || !userSS.hasRole(Profile.ADMIN) && !id.equals(userSS.getId())){
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        return toResponse(user);
    }

    public UserResponse findByEmail(String email) {
        UserSS userSS = getAuthenticated();

        if(userSS == null || !userSS.hasRole(Profile.ADMIN) && !email.equals(userSS.getEmail())){
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findOneByEmailIgnoreCaseAndDeletedFalse(email).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        return toResponse(user);
    }

    public List<UserResponse> findAll() {
        UserSS user = getAuthenticated();

        if(user == null || !user.hasRole(Profile.ADMIN)){
            throw new AuthorizationException("Access denied");
        }

        List<UserResponse> users = new ArrayList<>();

        userRepository.findAll().forEach(u -> users.add(toResponse(u)));

        return users;
    }

    public void deleteById(Long id) {
        UserSS userSS = getAuthenticated();

        User user = userRepository.findById(id).orElse(null);

        if(userSS == null || !userSS.hasRole(Profile.ADMIN)){
            throw new AuthorizationException("Access denied");
        }

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }

    public UserResponse updateById(Long id, UserRequest userRequest) {
        UserSS userSS = getAuthenticated();

        if(userSS == null || !userSS.hasRole(Profile.ADMIN)){
            throw new AuthorizationException("Access denied");
        }

        User user = userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new ObjectNotFoundException("User not found");
        }

        User userToSave = UserConverter.convertRequestToEntity(userRequest, id);

        return UserConverter.convertEntityToResponse(userRepository.save(userToSave));
    }

    private void checkUniqueIdentifiersConflict(UserRequest request) {
        userRepository.findOneByEmailIgnoreCaseAndDeletedFalse(request.getEmail()).ifPresent(UserServiceImpl::throwEmailConflict);
    }

    private UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumbers(user.getPhoneNumbers())
                .gender(user.getGender().getDescription())
                .cpf(user.getCpf())
                .language(user.getLanguage().getDescription())
                .addresses(user.getAddresses())
                .build();
    }

    private static void throwEmailConflict(User user) {
        throw new ValidationException(MessageKey.USER_EMAIL_CONFLICT);
    }

    private static void validateIfUserExists(Long id) {
        throw new ValidationException(MessageKey.USER_EMAIL_CONFLICT);
    }

    private static void validateIfUserIsAdmin(User user) {
        throw new ValidationException(MessageKey.USER_EMAIL_CONFLICT);
    }

    public static UserSS getAuthenticated(){
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        catch (Exception e) {
            return null;
        }
    }



}
