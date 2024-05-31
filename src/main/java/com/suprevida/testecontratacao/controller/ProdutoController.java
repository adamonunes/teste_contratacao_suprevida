package com.suprevida.testecontratacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suprevida.testecontratacao.dto.ProdutoRequestDTO;
import com.suprevida.testecontratacao.entities.Produto;
import com.suprevida.testecontratacao.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@GetMapping
	public ResponseEntity<List<Produto>> findAllProduto(){
		return ResponseEntity.ok(produtoService.findAllProduto());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Produto> findAllProduto(@PathVariable Long id){
		return ResponseEntity.ok(produtoService.findProduto(id));
	}

	@PostMapping
	public ResponseEntity<Void> createProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO) {
		Produto newProduto = new Produto(produtoRequestDTO);
		produtoService.createProduto(newProduto);
		
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public ResponseEntity<String> updateProduto(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoRequestDTO) {
		
		if(produtoService.updateProduto(id, produtoRequestDTO).isEmpty()) {
			return new ResponseEntity<>("Produto não encontrado", HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>("Alteração feita com sucesso", HttpStatus.OK);
		}		
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteProduto(@PathVariable Long id){
		produtoService.deleteProduto(id);
		return new ResponseEntity<>("Produto deletado com sucesso", HttpStatus.OK);
	}
}
