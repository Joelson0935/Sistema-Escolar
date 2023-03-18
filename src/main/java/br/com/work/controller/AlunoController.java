package br.com.work.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.work.domain.Aluno;
import br.com.work.domain.Endereco;
import br.com.work.domain.dtos.AlunoDto;
import br.com.work.service.AlunoService;

@RestController
@RequestMapping("/Alunos")
public class AlunoController {

	@Autowired
	private AlunoService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Aluno> salvarAluno(@Valid @RequestBody Aluno aluno) {
		aluno = service.salvarAluno(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
		Aluno aluno = service.buscarAlunoPorId(id);
		if (aluno != null) {
			return ResponseEntity.ok(aluno);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/Cep")
	public ResponseEntity<Endereco> buscarEndereco(@RequestParam(name = "cep") String cep) {
		Endereco endereco = service.buscarCep(cep);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<AlunoDto>> buscarAlunoPorNome(@RequestParam(name = "nome") String nome) {
		List<AlunoDto> alunos = service.buscarAlunosPorNome(nome);
		if (alunos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alunos);
	}

	@GetMapping("/BuscarTodosAlunos")
	public ResponseEntity<List<AlunoDto>> buscarListaAlunos() {
		List<Aluno> alunos = service.buscarListaAlunos();
		List<AlunoDto> alunosDto = alunos.stream().map(aluno -> new AlunoDto(aluno)).collect(Collectors.toList());
		return ResponseEntity.ok(alunosDto);
	}

	@GetMapping("/TodosAlunosPaginados")
	public ResponseEntity<Page<AlunoDto>> buscarListaAlunosPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Aluno> alunos = service.buscarAlunosPaginados(pageable);
		Page<AlunoDto> alunosDto = alunos.map(aluno -> new AlunoDto(aluno));
		if (alunosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(alunosDto);
	}

	@PutMapping("/Atualizar/{alunoId}")
	public ResponseEntity<Aluno> atualizarAlunoPorId(@Valid @PathVariable Long alunoId, @RequestBody Aluno aluno) {
		Aluno aluno1 = service.buscarAlunoPorId(alunoId);
		if (alunoId != null) {
			aluno.setId(alunoId);
			aluno1 = service.salvarAluno(aluno);
			return ResponseEntity.ok(aluno1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteAluno")
	public ResponseEntity<Void> deletarAlunoPorId(@RequestParam(name = "deleteId") Long deleteId) {
		Aluno aluno = service.buscarAlunoPorId(deleteId);
		if (aluno == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarAluno(deleteId);
		return ResponseEntity.noContent().build();
	}

}
