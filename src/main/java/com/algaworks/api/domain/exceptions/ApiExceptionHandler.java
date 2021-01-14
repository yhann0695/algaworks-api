package com.algaworks.api.domain.exceptions;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.algaworks.api.domain.exceptions.ProblemaExceptions.Campo;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(NegocioException.class)
	protected ResponseEntity<Object> handleSecurity(NegocioException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}
	
	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> handleSecurity(NotFoundException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
				request);
	}
	
	

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		this.adicionarErroLista(ex);
		ProblemaExceptions problema = setarProblema(status, ex.getMessage());
		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

	private void adicionarErroLista(MethodArgumentNotValidException ex) {
		List<Campo> campos = new ArrayList<ProblemaExceptions.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new ProblemaExceptions.Campo(nome, mensagem));
		}
	}

	private ProblemaExceptions setarProblema(HttpStatus status, String mensagem) {
		ProblemaExceptions problema = new ProblemaExceptions();
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos, Faça o preenchimento correto e tente novamente");
		problema.setDataHora(OffsetDateTime.now());
		return problema;
	}
}
