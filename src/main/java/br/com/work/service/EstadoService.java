package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Estado;
import br.com.work.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;

	public Estado salvarEstado(Estado estado) {
		estado = repository.save(estado);
		return estado;
	}

	public Estado buscarEstadoPorId(Long id) {
		Estado estado = repository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return estado;
	}

	public List<Estado> buscarListaEstados() {
		List<Estado> estados = repository.findAll();
		return estados;
	}

	public Page<Estado> buscarEstadosPaginados(Pageable pageable) {
		Page<Estado> estados = repository.findAll(pageable);
		return estados;
	}

	public List<Estado> buscarEstadosPorNome(String nome) {
		List<Estado> estados = repository.buscarEstadoPorNome(nome);
		return estados;
	}

	public void deletarEstado(Long id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		repository.deleteById(id);
	}

}
