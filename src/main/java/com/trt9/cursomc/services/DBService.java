package com.trt9.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trt9.cursomc.domain.Categoria;
import com.trt9.cursomc.domain.Cidade;
import com.trt9.cursomc.domain.Cliente;
import com.trt9.cursomc.domain.Endereco;
import com.trt9.cursomc.domain.Estado;
import com.trt9.cursomc.domain.ItemPedido;
import com.trt9.cursomc.domain.Pagamento;
import com.trt9.cursomc.domain.PagamentoComBoleto;
import com.trt9.cursomc.domain.PagamentoComCartao;
import com.trt9.cursomc.domain.Pedido;
import com.trt9.cursomc.domain.Produto;
import com.trt9.cursomc.domain.enums.EstadoPagamento;
import com.trt9.cursomc.domain.enums.TipoCliente;
import com.trt9.cursomc.repositories.CategoriaRepository;
import com.trt9.cursomc.repositories.CidadeRepository;
import com.trt9.cursomc.repositories.ClienteRepository;
import com.trt9.cursomc.repositories.EnderecoRepository;
import com.trt9.cursomc.repositories.EstadoRepository;
import com.trt9.cursomc.repositories.ItemPedidoRepository;
import com.trt9.cursomc.repositories.PagamentoRepository;
import com.trt9.cursomc.repositories.PedidoRepository;
import com.trt9.cursomc.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	CategoriaRepository categoriarepository;

	@Autowired
	ProdutoRepository produtorepository;

	@Autowired
	EstadoRepository estadorepository;

	@Autowired
	CidadeRepository cidaderepository;

	@Autowired
	ClienteRepository clienterepository;

	@Autowired
	EnderecoRepository enderecorepository;
	
	@Autowired
	PedidoRepository pedidorepository;

	@Autowired
	PagamentoRepository pagamentorepository;

	@Autowired
	ItemPedidoRepository itempedidorepository;
	
	public void instantiateTestDatabase() throws ParseException {

		System.out.println("vou testar aqui");
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");
		Categoria cat3 = new Categoria(null,"Cama, mesa e Banho");
		Categoria cat4 = new Categoria(null,"Vestuário Feminino");
		Categoria cat5 = new Categoria(null,"Vestuário Masculino");
		Categoria cat6 = new Categoria(null,"Vestuário Infantil");
		Categoria cat7 = new Categoria(null,"Perfumaria");

		Produto p1 = new Produto(null,"Computador",2000.00);
		Produto p2 = new Produto(null,"Impressora",800.00);
		Produto p3 = new Produto(null,"Mouse",80.00);
		Produto p4 = new Produto(null,"Mesa",2000.00);
		Produto p5 = new Produto(null,"toalha",10.00);
		Produto p6 = new Produto(null,"Colcha",80.00);
		Produto p7 = new Produto(null,"Tv true color",2000.00);
		Produto p8 = new Produto(null,"Roçadeira",800.00);
		Produto p9 = new Produto(null,"Abajour",180.00);
		Produto p10= new Produto(null,"Pendente",40.00);
		Produto p11= new Produto(null,"Shampoo",8.00);

		
		// Criando relacionamentos
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4 ));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriarepository.saveAll(Arrays.asList(cat1,cat2,cat3,cat4,cat5,cat6,cat7));
		produtorepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadorepository.saveAll(Arrays.asList(est1,est2));
		cidaderepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1= new Cliente(null,"Maria Silva","wgaboardi@gmail.com","36378912377",TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("273663323","93865664"));
		
		Endereco ende1 = new Endereco(null, "RUA FLORES", "500", "CASA", "CENTRO", "80000000", cli1,c1);
		Endereco ende2 = new Endereco(null, "RUA XV", "800", "SOBRADO 6", "HUGO", "83000000", cli1,c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(ende1,ende2));
		
		clienterepository.saveAll(Arrays.asList(cli1));
		enderecorepository.saveAll(Arrays.asList(ende1,ende2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm");  
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1,ende1); 
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1,ende2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidorepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentorepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itempedidorepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}
}
