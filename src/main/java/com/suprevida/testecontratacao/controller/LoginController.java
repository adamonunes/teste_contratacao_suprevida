package com.suprevida.testecontratacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suprevida.testecontratacao.dto.LoginUserDto;
import com.suprevida.testecontratacao.dto.RecoveryJwtTokenDto;
import com.suprevida.testecontratacao.services.UserService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
