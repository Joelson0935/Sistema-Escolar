package br.com.work.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import br.com.work.domain.Estado;
import br.com.work.domain.dtos.EstadoDto;
import br.com.work.service.EstadoService;

@RestController
@RequestMapping("/Estados")
public class EstadoController {

	@Autowired
	private EstadoService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Estado> salvarCidade(@RequestBody Estado estado) {
		estado = service.salvarEstado(estado);
		return new ResponseEntity<Estado>(estado, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Estado> buscarEstadoPorId(@PathVariable Long id) {
		Estado estado = service.buscarEstadoPorId(id);
		if (estado != null) {
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<Estado>> buscarEstadoPorNome(@RequestParam(name = "nome") String nome) {
		List<Estado> estados = service.buscarEstadosPorNome(nome);
		if (estados == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estados);
	}

	@GetMapping("/BuscarTodosEstados")
	public ResponseEntity<List<EstadoDto>> buscarListaEstados() {
		List<Estado> estados = service.buscarListaEstados();
		List<EstadoDto> estadosDto = estados.stream().map(estado -> new EstadoDto(estado)).collect(Collectors.toList());
		if (estadosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estadosDto);
	}

	@GetMapping("/TodosEstadosPaginados")
	public ResponseEntity<Page<EstadoDto>> buscarListaEstadosPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Estado> estados = service.buscarEstadosPaginados(pageable);
		Page<EstadoDto> estadosDto = estados.map(estado -> new EstadoDto(estado));
		if (estadosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(estadosDto);
	}

	@PutMapping("/Atualizar/{estadoId}")
	public ResponseEntity<Estado> atualizarEstadoPorId(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estado1 = service.buscarEstadoPorId(estadoId);
		if (estadoId != null) {
			estado.setId(estadoId);
			estado1 = service.salvarEstado(estado);
			return ResponseEntity.ok(estado1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteEstado")
	public ResponseEntity<Void> deletarEstadoPorId(@RequestParam(name = "deleteId") Long deleteId) {
		service.buscarEstadoPorId(deleteId);
		if (deleteId == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarEstado(deleteId);
		return ResponseEntity.noContent().build();
	}

}
