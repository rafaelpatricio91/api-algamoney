package com.rafa.apialgamoney.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler
{
//	possibilita o resgate da mensagem lá do message.properties
	@Autowired
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) 
	{
		//pega msg la do message.properties
		String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
		//pega msg padrao do spring
		String mensagemDesenvolvedor = ex.getCause().toString();
		//add elas em uma lista
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		//passa a lista, headers, status e a requisição
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request)
		{
			List<Erro> erros = criarListaErros(ex.getBindingResult());
			return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
		} 
	
	private List<Erro> criarListaErros(BindingResult result)
	{
		List<Erro> erros = new ArrayList<>();
		
		//BindingResult tem acesso aos campos com erro. Ele itera nesses erros
		for (FieldError error : result.getFieldErrors ())
		{
			//passa o campo/campos com erro.
			String mensagemUsuario = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = error.toString(); //passa erro default do spring
			
			erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		}
		
		return erros;
	}
	
	public static class Erro 
	{
		
		private String mensagemUsuario;
		private String mensagemDesenvolvedor;
		
		public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
			this.mensagemUsuario = mensagemUsuario;
			this.mensagemDesenvolvedor = mensagemDesenvolvedor;
		}

		public String getMensagemUsuario() {
			return mensagemUsuario;
		}

		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
		
	}
}
