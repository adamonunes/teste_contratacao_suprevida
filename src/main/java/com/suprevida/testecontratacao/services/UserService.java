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
import com.suprevida.testecontratacao.dto.UpdateUserDto;
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

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o name e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.name(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
       return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public void createUser(CreateUserDto createUserDto) {

        // Cria um novo usuário com os dados fornecidos
        User newUser = User.builder()
                .name(createUserDto.name())
                // Codifica a senha do usuário com o algoritmo bcrypt
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                // Atribui ao usuário uma permissão específica
                .roles(List.of(Role.builder().name(createUserDto.role()).build()))
//                .roles(roles)
                .build();

        // Salva o novo usuário no banco de dados
        userRepository.save(newUser);
    }
    
    public Optional<User> findUser(Long id) {
    	return userRepository.findById(id);
    }
    
//    Método responsável por trazer todos os usuário
    public List<User> getAllUsers() {
    	return userRepository.findAll();
    }
    
    public void deleteUser(Long id) {
    	userRepository.deleteById(id);
    }
}
