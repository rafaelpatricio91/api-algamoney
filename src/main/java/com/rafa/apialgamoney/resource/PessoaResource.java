package com.rafa.apialgamoney.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.apialgamoney.event.RecursoCriadoEvent;
import com.rafa.apialgamoney.model.Pessoa;
import com.rafa.apialgamoney.model.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource
{
	@Autowired
	private PessoaRepository pessoas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	private List<Pessoa> listar()
	{
		return pessoas.findAll();
	}
	
	@GetMapping("/{codigo}")
	private ResponseEntity<Pessoa> buscarPorCodigo(@PathVariable Long codigo)
	{
		Pessoa pessoa = pessoas.findOne(codigo);
		return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response)
	{
		Pessoa pessoaSalva = pessoas.save(pessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo() ));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
		
	}
}
