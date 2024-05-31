package com.suprevida.testecontratacao.dto;

import com.suprevida.testecontratacao.enums.RoleName;

public record UpdateUserDto(
        String password,
        RoleName role
) {
}
