package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Departamento;
import br.com.work.repository.DepartamentoRepository;
import br.com.work.service.exception.ObjectNotFound;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;

	public Departamento salvarDepartamento(Departamento departamento) {
		departamento = repository.save(departamento);
		return departamento;
	}

	public Departamento buscarDepartamentoPorId(Long id) {
		Departamento departamento = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFound(id + " Não encontrado."));
		return departamento;
	}

	public List<Departamento> buscarListaDepartamentos() {
		List<Departamento> departamentos = repository.findAll();
		return departamentos;
	}

	public Page<Departamento> buscarDepartamentosPaginados(Pageable pageable) {
		Page<Departamento> departamentos = repository.findAll(pageable);
		return departamentos;
	}

	public List<Departamento> buscarDepartamentosPorNome(String nome) {
		List<Departamento> departamentos = repository.buscarDepartamentoPorNome(nome);
		return departamentos;
	}

	public void deletarDepartamento(Long id) {
		repository.findById(id).orElseThrow(() -> new ObjectNotFound(id + " não foi encontrado"));
		repository.deleteById(id);
	}

}
