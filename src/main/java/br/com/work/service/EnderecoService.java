package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Endereco;
import br.com.work.repository.EnderecoRepository;
import br.com.work.service.exception.ObjectNotFound;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	public Endereco salvarEndereco(Endereco endereco) {
		endereco = repository.save(endereco);
		return endereco;
	}

	public Endereco buscarEnderecoPorId(String cep) {
		Endereco endereco = repository.findById(cep).orElseThrow(() -> new ObjectNotFound(cep + " Não encontrado."));
		return endereco;
	}

	public List<Endereco> buscarListaEnderecos() {
		List<Endereco> enderecos = repository.findAll();
		return enderecos;
	}

	public Page<Endereco> buscarEnderecosPaginados(Pageable pageable) {
		Page<Endereco> enderecos = repository.findAll(pageable);
		return enderecos;
	}

//	public List<Endereco> buscarEnderecosPorNome(String nome) {
//		List<Endereco> enderecos = repository.buscarEnderecoPorNome(nome);
//		return enderecos;
//	}

	public void deletarEndereco(String cep) {
		repository.findById(cep).orElseThrow(() -> new ObjectNotFound(cep + " não foi encontrado"));
		repository.deleteById(cep);
	}
}
