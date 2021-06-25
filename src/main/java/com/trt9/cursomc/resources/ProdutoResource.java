package com.trt9.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trt9.cursomc.domain.Produto;
import com.trt9.cursomc.dto.ProdutoDTO;
import com.trt9.cursomc.resources.utils.URL;
import com.trt9.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome",defaultValue="") String nome, 
			@RequestParam(value="categorias",defaultValue="") String categorias, 
			@RequestParam(value="page",defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome") String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> lista = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		// cria uma lista dto a partir da outra lista
		Page<ProdutoDTO> listdto = lista.map(obj -> new ProdutoDTO(obj));
		// page java 8 compliance - nao precisa de stream nem de collector
		return ResponseEntity.ok().body(listdto);
	}
	
}
