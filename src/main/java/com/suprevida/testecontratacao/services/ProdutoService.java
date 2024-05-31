package com.suprevida.testecontratacao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suprevida.testecontratacao.dto.ProdutoRequestDTO;
import com.suprevida.testecontratacao.entities.Produto;
import com.suprevida.testecontratacao.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}
	
	public Produto findProduto(Long id) {
		return produtoRepository.findById(id).get();
	}
	
	public Produto createProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Optional<Produto> updateProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
		Optional<Produto> produtoOpt = produtoRepository.findById(id);
		
		if(produtoOpt.isPresent()) {
			if(produtoRequestDTO.nome() != null) {
				produtoOpt.get().setNome(produtoRequestDTO.nome());
			}
			if(produtoRequestDTO.descricao() != null) {
				produtoOpt.get().setDescricao(produtoRequestDTO.descricao());
			}
			if(produtoRequestDTO.preco() != null) {
				produtoOpt.get().setPreco(produtoRequestDTO.preco());
			}
			
			produtoRepository.save(produtoOpt.get());
			return produtoOpt;
		} else {
			return Optional.empty();
		}
	}
	
	public void deleteProduto(Long id) {
		produtoRepository.deleteById(id);
	}
}
