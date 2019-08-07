package com.rafa.apialgamoney.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.apialgamoney.event.RecursoCriadoEvent;
import com.rafa.apialgamoney.model.Categoria;
import com.rafa.apialgamoney.model.repository.CategoriaRepository;
import com.rafa.apialgamoney.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource
{
	@Autowired
	private CategoriaRepository categorias;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService service;
	
	@GetMapping
	public List<Categoria> listar()
	{
		return categorias.findAll();
//		return !listaCategorias.isEmpty() ? ResponseEntity.ok(listaCategorias) : ResponseEntity.noContent().build();
	}
				 //HttpServletResponse habilita métodos para poder passar o locale na uri
	@PostMapping //RequestBody associa os valores que estão vindo da requisição com o objeto informado
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response)
	{
		 Categoria categoriaSalva = categorias.save(categoria);
		 
		 //add em uri o codigo da entidade salva a partir da URI atual, que é /categorias/...
		 publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo() ));
			
		 return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		  //retorna a entidade e o status created
		  //também retorna o location
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo)
	{
		Categoria categoria = categorias.findOne(codigo);
		return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT) //como n tem retorno, o responsestatus que vai dizer qual é a resposta
										   //no content pois n há nada para retornar
	public void remover(@PathVariable Long codigo)
	{
		categorias.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria)
	{
		Categoria categoriaSalva = service.atualizar(codigo, categoria);
		
		return ResponseEntity.ok(categoriaSalva);
	}
	
}
