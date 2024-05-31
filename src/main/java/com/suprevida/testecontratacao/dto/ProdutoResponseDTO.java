package com.suprevida.testecontratacao.dto;

import java.math.BigDecimal;

import com.suprevida.testecontratacao.entities.Produto;

public record ProdutoResponseDTO(Long id, String nome, String descricao, BigDecimal preco) {

	public ProdutoResponseDTO(Produto produto) {
		this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());
	}
}
