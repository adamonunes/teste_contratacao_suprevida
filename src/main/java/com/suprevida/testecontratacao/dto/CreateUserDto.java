package com.suprevida.testecontratacao.dto;

import com.suprevida.testecontratacao.enums.RoleName;

public record CreateUserDto(
        String name,
        String password,
        RoleName role
) {
}
