package com.suprevida.testecontratacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.suprevida.testecontratacao.dto.CreateUserDto;
import com.suprevida.testecontratacao.dto.LoginUserDto;
import com.suprevida.testecontratacao.dto.RecoveryJwtTokenDto;
import com.suprevida.testecontratacao.entities.Role;
import com.suprevida.testecontratacao.entities.User;
import com.suprevida.testecontratacao.repositories.UserRepository;
import com.suprevida.testecontratacao.security.UserDetailsImpl;
import com.suprevida.testecontratacao.security.authentication.JwtTokenService;
import com.suprevida.testecontratacao.security.config.SecurityConfiguration;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.name(), loginUserDto.password());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

       return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    public void createUser(CreateUserDto createUserDto) {

        User newUser = User.builder()
                .name(createUserDto.name())
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
                .build();

        userRepository.save(newUser);
    }
    
    public Optional<User> findUser(Long id) {
    	return userRepository.findById(id);
    }
    
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
}
