package com.suprevida.testecontratacao.dto;

import com.suprevida.testecontratacao.enums.RoleName;

public record CreateUserDto(
        String email,
        String password,
        RoleName role
) {
}
