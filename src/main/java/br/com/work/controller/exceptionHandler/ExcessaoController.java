package br.com.work.controller.exceptionHandler;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcessaoController {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Erro> objectoNaoEncontrado(RuntimeException e, HttpServletRequest request) {
		Erro erro = new Erro("Objeto não encontrado", HttpStatus.NOT_FOUND.value(), LocalDate.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ListaErros> argumentoNaoValido(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		ListaErros listaErros = new ListaErros("Argumento não validado", HttpStatus.BAD_REQUEST.value(),
				LocalDate.now());
		for (FieldError field : e.getBindingResult().getFieldErrors()) {
			listaErros.setCampos(field.getField(), field.getDefaultMessage());
		}
		return new ResponseEntity<ListaErros>(listaErros, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Erro> notReadable(HttpMessageNotReadableException e, HttpServletRequest request){
		Erro erro = new Erro("Enumerador escrito errado.", HttpStatus.BAD_REQUEST.value(), LocalDate.now());
		return new ResponseEntity<Erro>(erro, HttpStatus.BAD_REQUEST);
	}
}
