package com.suprevida.testecontratacao.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO (
    String nome,
    String descricao,
    BigDecimal preco
)
{}
