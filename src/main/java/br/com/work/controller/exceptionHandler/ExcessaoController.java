package br.com.work.controller.exceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.work.service.exception.AutorizacaoException;
import br.com.work.service.exception.ObjectNotFound;

@ControllerAdvice
public class ExcessaoController {

	@ExceptionHandler(ObjectNotFound.class)
	public ResponseEntity<Erro> objectoNaoEncontrado(ObjectNotFound e) {
		Erro erro = new Erro(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ListaErros> argumentoNaoValido(MethodArgumentNotValidException e) {
		ListaErros listaErros = new ListaErros("Argumento não validado", HttpStatus.BAD_REQUEST.value(),
				LocalDate.now());
		for (FieldError field : e.getBindingResult().getFieldErrors()) {
			listaErros.setCampos(field.getField(), field.getDefaultMessage());
		}
		return new ResponseEntity<ListaErros>(listaErros, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Erro> notReadable(HttpMessageNotReadableException e) {
		Erro erro = new Erro("Enumerador escrito errado.", HttpStatus.BAD_REQUEST.value(), LocalDate.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(AutorizacaoException.class)
	public ResponseEntity<Erro> naoAutorizado(AutorizacaoException e) {
		var erro = new Erro(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDate.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Erro> SQLException(SQLIntegrityConstraintViolationException e) {
		var erro = new Erro(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDate.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
