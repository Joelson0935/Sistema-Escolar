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

import br.com.work.domain.Endereco;
import br.com.work.domain.Funcionario;
import br.com.work.domain.dtos.FuncionarioDto;
import br.com.work.service.FuncionarioService;

@RestController
@RequestMapping("/Funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody Funcionario funcionario) {
		funcionario = service.salvarFuncionario(funcionario);
		return new ResponseEntity<Funcionario>(funcionario, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
		Funcionario funcionario = service.buscarFuncionarioPorId(id);
		if (funcionario != null) {
			return ResponseEntity.ok(funcionario);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/Cep")
	public ResponseEntity<Endereco> buscarEndereco(@RequestParam(name = "cep") String cep) {
		Endereco endereco = service.buscarEndereco(cep);
		if (endereco == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(endereco);
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<Funcionario>> buscarFuncionarioPorNome(@RequestParam(name = "nome") String nome) {
		List<Funcionario> funcionarios = service.buscarFuncionariosPorNome(nome);
		if (funcionarios == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/BuscarTodosFuncionarios")
	public ResponseEntity<List<FuncionarioDto>> buscarListaFuncionarios() {
		List<Funcionario> funcionarios = service.buscarListaFuncionarios();
		List<FuncionarioDto> funcionariosDto = funcionarios.stream().map(funcionario -> new FuncionarioDto(funcionario))
				.collect(Collectors.toList());
		if (funcionariosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcionariosDto);
	}

	@GetMapping("/TodosFuncionariosPaginados")
	public ResponseEntity<Page<FuncionarioDto>> buscarListaFuncionariosPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Funcionario> funcionarios = service.buscarFuncionariosPaginados(pageable);
		Page<FuncionarioDto> funcionariosDto = funcionarios.map(funcionario -> new FuncionarioDto(funcionario));
		if (funcionariosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcionariosDto);
	}

	@PutMapping("/Atualizar/{funcionarioId}")
	public ResponseEntity<Funcionario> atualizarFuncionarioPorId(@Valid @PathVariable Long funcionarioId,
			@RequestBody Funcionario funcionario) {
		Funcionario funcionario1 = service.buscarFuncionarioPorId(funcionarioId);
		if (funcionarioId != null) {
			funcionario.setId(funcionarioId);
			funcionario1 = service.salvarFuncionario(funcionario);
			return ResponseEntity.ok(funcionario1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteFuncionario")
	public ResponseEntity<Void> deletarFuncionarioPorId(@RequestParam(name = "deleteId") Long deleteId) {
		service.buscarFuncionarioPorId(deleteId);
		if (deleteId == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarFuncionario(deleteId);
		return ResponseEntity.noContent().build();
	}

}
