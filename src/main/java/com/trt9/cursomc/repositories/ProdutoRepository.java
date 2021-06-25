package com.trt9.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trt9.cursomc.domain.Categoria;
import com.trt9.cursomc.domain.Produto;

@Repository
@Transactional(readOnly=true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//monta automaticamente a string acima (nao precisa da query nem dos params)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(
	//@Param("nome") 
	String nome,
	//@Param("categorias") 
	List<Categoria> categorias,
	Pageable pageRequest);

}
