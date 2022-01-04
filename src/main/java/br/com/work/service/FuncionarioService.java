package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Funcionario;
import br.com.work.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public Funcionario salvarFuncionario(Funcionario funcionario) {
		funcionario.getDepartamento().getId();
		funcionario = repository.save(funcionario);
		return funcionario;
	}

	public Funcionario buscarFuncionarioPorId(Long id) {
		Funcionario funcionario = repository.findById(id)
				.orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
		return funcionario;
	}

	public List<Funcionario> buscarListaFuncionarios() {
		List<Funcionario> funcionarios = repository.findAll();
		return funcionarios;
	}

	public Page<Funcionario> buscarFuncionariosPaginados(Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findAll(pageable);
		return funcionarios;
	}

	public List<Funcionario> buscarFuncionariosPorNome(String nome) {
		List<Funcionario> funcionarios = repository.buscarFuncionarioPorNome(nome);
		return funcionarios;
	}

	public void deletarFuncionario(Long id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		repository.deleteById(id);
	}
}
