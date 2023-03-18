package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.work.domain.Endereco;
import br.com.work.domain.Funcionario;
import br.com.work.repository.FuncionarioRepository;
import br.com.work.service.exception.ObjectNotFound;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;

	public Funcionario salvarFuncionario(Funcionario funcionario) {
		Endereco endereco = buscarEndereco(funcionario.getEndereco().getCep());
		funcionario.setEndereco(endereco);
		funcionario.setDepartamento(funcionario.getDepartamento());
		return repository.save(funcionario);
	}

	public Funcionario buscarFuncionarioPorId(Long id) {
		Funcionario funcionario = repository.findById(id)
				.orElseThrow(() -> new ObjectNotFound(id + " Não encontrado."));
		return funcionario;
	}

	public Endereco buscarEndereco(String cep) {
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		RestTemplate restTemplate = new RestTemplate();
		Endereco endereco = restTemplate.getForObject(url, Endereco.class);
		return endereco;
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
		repository.findById(id).orElseThrow(() -> new ObjectNotFound(id + " não foi encontrado"));
		repository.deleteById(id);
	}
}
