package com.trt9.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trt9.cursomc.domain.Categoria;
import com.trt9.cursomc.domain.Cidade;
import com.trt9.cursomc.domain.Pagamento;
import com.trt9.cursomc.domain.Pedido;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
