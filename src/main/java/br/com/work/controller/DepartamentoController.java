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

import br.com.work.domain.Departamento;
import br.com.work.domain.dtos.DepartamentoDto;
import br.com.work.service.DepartamentoService;

@RestController
@RequestMapping("/Departamentos")
public class DepartamentoController {

	@Autowired
	private DepartamentoService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Departamento> salvarDepartamento(@Valid @RequestBody Departamento departamento) {
		departamento = service.salvarDepartamento(departamento);
		return new ResponseEntity<Departamento>(departamento, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable Long id) {
		Departamento departamento = service.buscarDepartamentoPorId(id);
		if (departamento != null) {
			return ResponseEntity.ok(departamento);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<Departamento>> buscarDepartamentoPorNome(@RequestParam(name = "nome") String nome) {
		List<Departamento> departamentos = service.buscarDepartamentosPorNome(nome);
		if (departamentos == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(departamentos);
	}

	@GetMapping("/BuscarTodosDepartamentos")
	public ResponseEntity<List<DepartamentoDto>> buscarListaDepartamentos() {
		List<Departamento> departamentos = service.buscarListaDepartamentos();
		List<DepartamentoDto> departamentosDto = departamentos.stream()
				.map(departamento -> new DepartamentoDto(departamento)).collect(Collectors.toList());
		if (departamentosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(departamentosDto);
	}

	@GetMapping("/TodosDepartamentosPaginados")
	public ResponseEntity<Page<DepartamentoDto>> buscarListaDepartamentosPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Departamento> departamentos = service.buscarDepartamentosPaginados(pageable);
		Page<DepartamentoDto> departamentosDto = departamentos.map(departamento -> new DepartamentoDto(departamento));
		if (departamentosDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(departamentosDto);
	}

	@PutMapping("/Atualizar/{departamentoId}")
	public ResponseEntity<Departamento> atualizarDepartamentoPorId(@Valid @PathVariable Long departamentoId,
			@RequestBody Departamento departamento) {
		Departamento departamento1 = service.buscarDepartamentoPorId(departamentoId);
		if (departamentoId != null) {
			departamento.setId(departamentoId);
			departamento1 = service.salvarDepartamento(departamento);
			return ResponseEntity.ok(departamento1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteDepartamento")
	public ResponseEntity<Void> deletarDepartamentoPorId(@RequestParam(name = "deleteId") Long deleteId) {
		service.buscarDepartamentoPorId(deleteId);
		if (deleteId == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarDepartamento(deleteId);
		return ResponseEntity.noContent().build();
	}

}
