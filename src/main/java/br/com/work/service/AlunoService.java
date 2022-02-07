package br.com.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.work.domain.Aluno;
import br.com.work.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository repository;

	public Aluno salvarAluno(Aluno aluno) {
		aluno.getEndereco().getId();
		aluno = repository.save(aluno);
		return aluno;
	}

	public Aluno buscarAlunoPorId(Long id) {
		Aluno aluno = repository.findById(id).orElseThrow(() -> new RuntimeException(id + " Não encontrado."));
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

	public List<Aluno> buscarAlunosPorNome(String nome) {
		List<Aluno> alunos = repository.buscarAlunoPorNome(nome);
		return alunos;
	}

	public void deletarAluno(Long id) {
		repository.findById(id).orElseThrow(() -> new RuntimeException(id + " não foi encontrado"));
		repository.deleteById(id);
	}

}
