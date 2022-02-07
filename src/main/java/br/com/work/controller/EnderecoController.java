package br.com.work.controller;

import java.util.List;

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
import br.com.work.service.EnderecoService;

@RestController
@RequestMapping("/Enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Endereco> salvarEndereco(@Valid @RequestBody Endereco endereco) {
		endereco = service.salvarEndereco(endereco);
		return new ResponseEntity<Endereco>(endereco, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@PathVariable Long id) {
		Endereco endereco = service.buscarEnderecoPorId(id);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

//	@GetMapping("/BuscarPorNome")
//	public ResponseEntity<List<Endereco>> buscarEnderecoPorNome(@RequestParam(name = "nome") String nome) {
//		List<Endereco> enderecos = service.buscarEnderecosPorNome(nome);
//		if (enderecos == null) {
//			return ResponseEntity.notFound().build();
//		}
//		return ResponseEntity.ok(enderecos);
//	}

	@GetMapping("/BuscarTodosEnderecos")
	public ResponseEntity<List<Endereco>> buscarListaEnderecos() {
		List<Endereco> enderecos = service.buscarListaEnderecos();
		if (enderecos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecos);
	}

	@GetMapping("/TodosEnderecosPaginados")
	public ResponseEntity<Page<Endereco>> buscarListaEnderecosPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Endereco> enderecos = service.buscarEnderecosPaginados(pageable);
		if (enderecos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(enderecos);
	}

	@PutMapping("/Atualizar/{enderecoId}")
	public ResponseEntity<Endereco> atualizarEnderecoPorId(@Valid @PathVariable Long enderecoId,
			@RequestBody Endereco endereco) {
		Endereco endereco1 = service.buscarEnderecoPorId(enderecoId);
		if (enderecoId != null) {
			endereco.setId(enderecoId);
			endereco1 = service.salvarEndereco(endereco);
			return ResponseEntity.ok(endereco1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteEndereco")
	public ResponseEntity<Void> deletarEnderecoPorId(@RequestParam(name = "deleteId") Long deleteId) {
		service.buscarEnderecoPorId(deleteId);
		if (deleteId == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarEndereco(deleteId);
		return ResponseEntity.noContent().build();
	}

}
