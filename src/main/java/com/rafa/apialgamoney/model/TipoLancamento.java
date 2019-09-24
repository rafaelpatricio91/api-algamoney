package com.rafa.apialgamoney.model;

public enum TipoLancamento
{
	RECEITA("Receita"), DESPESA("Despesa");
	
	private String descricao;
	
	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	private TipoLancamento(String descricao)
	{
		this.descricao = descricao;
	}
}
