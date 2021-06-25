package com.trt9.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trt9.cursomc.domain.Cidade;
import com.trt9.cursomc.domain.Cliente;
import com.trt9.cursomc.domain.Endereco;
import com.trt9.cursomc.domain.enums.TipoCliente;
import com.trt9.cursomc.dto.ClienteDTO;
import com.trt9.cursomc.dto.ClienteNewDTO;
import com.trt9.cursomc.repositories.CidadeRepository;
import com.trt9.cursomc.repositories.ClienteRepository;
import com.trt9.cursomc.repositories.EnderecoRepository;
import com.trt9.cursomc.services.exceptions.DataIntegrityException;
import com.trt9.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	@Autowired
	private EnderecoRepository enderecoRepository;


	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo:" + Cliente.class.getName()));
	}
	
	public List<Cliente> findAll() {
		List<Cliente> obj = repo.findAll();
		return obj;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null); // forcar insercao
		obj= repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj); // salvar somente o que precisa
		return repo.save(newObj);
	}

	//atualizar somente dados necessarios do dto
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
	}

	public void delete(Integer id) {
		find(id);
		try {
		  repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que tem pedidos relacionados!");
		}
	}
	
	// insert categoria baseado no Dto
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(), null, null); //nao tem cpf nem tipo
	}	

	// insert categoria baseado no Dto
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(),objDto.getEmail(), objDto.getCpfouCnpj(), TipoCliente.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(),null,null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if (objDto.getTelefone2()!=null)
			cli.getTelefones().add(objDto.getTelefone2());
		if (objDto.getTelefone3()!=null)
			cli.getTelefones().add(objDto.getTelefone3());
		return cli;
	}	
	
}
