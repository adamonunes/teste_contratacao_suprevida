package com.suprevida.testecontratacao.dto;

import java.util.List;

import com.suprevida.testecontratacao.entities.Role;

public record RecoveryUserDto(

        Long id,
        String name,
        List<Role> roles

) {
}
