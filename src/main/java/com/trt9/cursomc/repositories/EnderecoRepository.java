package com.trt9.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trt9.cursomc.domain.Categoria;
import com.trt9.cursomc.domain.Endereco;
import com.trt9.cursomc.domain.Estado;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
