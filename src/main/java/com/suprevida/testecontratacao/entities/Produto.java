package com.suprevida.testecontratacao.entities;

import java.math.BigDecimal;

import com.suprevida.testecontratacao.dto.ProdutoRequestDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produto")
@Entity(name = "Produto")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;
    
    @Column
    private String descricao;
    
    @Column
    private BigDecimal preco;
    
    public Produto(ProdutoRequestDTO produtoRequestDTO) {
    	this.nome = produtoRequestDTO.nome();
    	this.descricao = produtoRequestDTO.descricao();
    	this.preco = produtoRequestDTO.preco();
    }
}
