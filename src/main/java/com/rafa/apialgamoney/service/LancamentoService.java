package com.rafa.apialgamoney.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafa.apialgamoney.model.Lancamento;
import com.rafa.apialgamoney.model.Pessoa;
import com.rafa.apialgamoney.repository.LancamentoRepository;
import com.rafa.apialgamoney.repository.PessoaRepository;
import com.rafa.apialgamoney.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService
{
	@Autowired
	private LancamentoRepository lancamentos;
	@Autowired
	private PessoaRepository pessoas;
	
	public Lancamento criar(Lancamento lancamento)
	{
		Pessoa pessoa = pessoas.findOne(lancamento.getPessoa().getCodigo());
		
		if (pessoa == null || pessoa.isInativo() )
		{
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentos.save(lancamento);
	}
}
