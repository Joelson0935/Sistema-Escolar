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

	@GetMapping("/Buscar/{cep}")
	public ResponseEntity<Endereco> buscarEnderecoPorId(@RequestParam(name = "cep") String cep) {
		Endereco endereco = service.buscarEnderecoPorId(cep);
		if (endereco != null) {
			return ResponseEntity.ok(endereco);
		}
		return ResponseEntity.notFound().build();
	}

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

	@PutMapping("/Atualizar/{cep}")
	public ResponseEntity<Endereco> atualizarEnderecoPorId(@Valid @PathVariable String cep,
			@RequestBody Endereco endereco) {
		Endereco endereco1 = service.buscarEnderecoPorId(cep);
		if (cep != null) {
			endereco1 = service.salvarEndereco(endereco);
			return ResponseEntity.ok(endereco1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteEndereco")
	public ResponseEntity<Void> deletarEnderecoPorId(@RequestParam(name = "deleteCep") String deleteCep) {
		service.buscarEnderecoPorId(deleteCep);
		if (deleteCep == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarEndereco(deleteCep);
		return ResponseEntity.noContent().build();
	}

}
