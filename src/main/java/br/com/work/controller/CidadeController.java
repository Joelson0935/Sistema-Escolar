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

import br.com.work.domain.Cidade;
import br.com.work.domain.dtos.CidadeDto;
import br.com.work.service.CidadeService;

@RestController
@RequestMapping("/Cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@PostMapping("/Salvar")
	public ResponseEntity<Cidade> salvarCidade(@Valid @RequestBody Cidade cidade) {
		cidade = service.salvarCidade(cidade);
		return new ResponseEntity<Cidade>(cidade, HttpStatus.CREATED);
	}

	@GetMapping("/Buscar/{id}")
	public ResponseEntity<Cidade> buscarCidadePorId(@PathVariable Long id) {
		Cidade cidade = service.buscarCidadePorId(id);
		if (cidade != null) {
			return ResponseEntity.ok(cidade);
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/BuscarPorNome")
	public ResponseEntity<List<Cidade>> buscarCidadePorNome(@RequestParam(name = "nome") String nome) {
		List<Cidade> cidades = service.buscarCidadesPorNome(nome);
		if (cidades == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cidades);
	}

	@GetMapping("/BuscarTodasCidades")
	public ResponseEntity<List<CidadeDto>> buscarListaCidades() {
		List<Cidade> cidades = service.buscarListaCidades();
		List<CidadeDto> cidadesDto = cidades.stream().map(cidade -> new CidadeDto(cidade)).collect(Collectors.toList());
		if (cidadesDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cidadesDto);
	}

	@GetMapping("/TodasCidadesPaginados")
	public ResponseEntity<Page<CidadeDto>> buscarListaCidadesPaginados(
			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
		Page<Cidade> cidades = service.buscarCidadesPaginadas(pageable);
		Page<CidadeDto> cidadesDto = cidades.map(cidade -> new CidadeDto(cidade));
		if (cidadesDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cidadesDto);
	}

	@PutMapping("/Atualizar/{cidadeId}")
	public ResponseEntity<Cidade> atualizarCidadePorId(@Valid @PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		Cidade cidade1 = service.buscarCidadePorId(cidadeId);
		if (cidadeId != null) {
			cidade.setId(cidadeId);
			cidade1 = service.salvarCidade(cidade);
			return ResponseEntity.ok(cidade1);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/DeleteCidade")
	public ResponseEntity<Void> deletarCidadePorId(@RequestParam(name = "deleteId") Long deleteId) {
		service.buscarCidadePorId(deleteId);
		if (deleteId == null) {
			return ResponseEntity.notFound().build();
		}
		service.deletarCidade(deleteId);
		return ResponseEntity.noContent().build();
	}

}
