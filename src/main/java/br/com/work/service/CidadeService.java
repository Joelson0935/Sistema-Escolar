package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Cidade;
import br.com.work.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;

	public Cidade salvarCidade(Cidade cidade) {
		cidade.getEstado().getId();
		cidade = repository.save(cidade);
		return cidade;
	}

	public Cidade buscarCidadePorId(Long id) {
		Cidade cidade = repository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return cidade;
	}

	public List<Cidade> buscarListaCidades() {
		List<Cidade> cidades = repository.findAll();
		return cidades;
	}

	public Page<Cidade> buscarCidadesPaginadas(Pageable pageable) {
		Page<Cidade> cidades = repository.findAll(pageable);
		return cidades;
	}

	public List<Cidade> buscarCidadesPorNome(String nome) {
		List<Cidade> cidades = repository.buscarCidadePorNome(nome);
		return cidades;
	}

	public void deletarCidade(Long id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		repository.deleteById(id);
	}

}
