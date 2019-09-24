 package com.rafa.apialgamoney.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rafa.apialgamoney.model.Lancamento;
import com.rafa.apialgamoney.repository.ResumoLancamento;
import com.rafa.apialgamoney.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery
{
	public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable);
}
