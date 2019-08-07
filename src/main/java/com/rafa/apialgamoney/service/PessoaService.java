package com.rafa.apialgamoney.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rafa.apialgamoney.model.Pessoa;
import com.rafa.apialgamoney.model.repository.PessoaRepository;

@Service // componente spring
public class PessoaService
{
	@Autowired
	private PessoaRepository pessoas;
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa)
	{
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		
		//copia de pessoa para pessoasalva ignorando o codigo. pois ele ja esta setado em pessoasalva pelo findone e o de pessoa esta null pq vem pela url
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
		
		return pessoas.save(pessoaSalva);
	}

	
	public void atualizarAtivo(Long codigo, Boolean ativo)
	{
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoas.save(pessoaSalva);
	}
	
	private Pessoa buscarPessoaPeloCodigo(Long codigo)
	{
		Pessoa pessoaSalva = pessoas.findOne(codigo); //pega a pessoa do banco e joga e pessoaSalva
		if (pessoaSalva == null)
		{
			//esperava pelo menos 1 elemento e foi encontrado 0
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}
}
