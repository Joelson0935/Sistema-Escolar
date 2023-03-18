package br.com.work.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.work.domain.Aluno;
import br.com.work.domain.Endereco;
import br.com.work.domain.dtos.AlunoDto;
import br.com.work.repository.AlunoRepository;
import br.com.work.service.exception.AutorizacaoException;
import br.com.work.service.exception.ObjectNotFound;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public Aluno salvarAluno(Aluno aluno) {
		Endereco endereco = buscarCep(aluno.getEndereco().getCep());
		aluno.setEndereco(endereco);
		return repository.save(aluno);
	}

	public Aluno atualizar(Long id, Aluno aluno) {
		Aluno alunoEncontrado = repository.findById(id).orElseThrow(() -> new ObjectNotFound("Aluno não encontrado."));
		aluno.setId(id);
		alunoEncontrado = aluno;
		return repository.save(alunoEncontrado);
	}

	public Endereco buscarCep(String cep) {
		String url = "https://viacep.com.br/ws/" + cep + "/json/";
		var restTemplate = new RestTemplate();
		Endereco endereco = restTemplate.getForObject(url, Endereco.class);
		return endereco;
	}

	public Aluno buscarAlunoPorId(Long id) {
		Aluno aluno = repository.findById(id).orElseThrow(() -> new ObjectNotFound(id + " Não encontrado."));
		return aluno;
	}

	public List<Aluno> buscarListaAlunos() {
		List<Aluno> alunos = repository.findAll();
		return alunos;
	}

	public Page<Aluno> buscarAlunosPaginados(Pageable pageable) {
		Page<Aluno> alunos = repository.findAll(pageable);
		return alunos;
	}

	public List<AlunoDto> buscarAlunosPorNome(String nome) {
		List<Aluno> alunos = repository.buscarAlunoPorNome(nome);
		List<AlunoDto> dtoAlunos = alunos.stream().map((aluno) -> new AlunoDto(aluno)).collect(Collectors.toList());
		return dtoAlunos;
	}

	public void deletarAluno(Long id) {
		Aluno aluno = repository.findById(id).orElseThrow(() -> new ObjectNotFound(id + " não foi encontrado"));
		if (!aluno.getNome_completo().equals("Joanna Dark Ferrari")) {
			throw new AutorizacaoException("Aluno não pode ser excluído!");
		}
		repository.deleteById(id);
	}

}
