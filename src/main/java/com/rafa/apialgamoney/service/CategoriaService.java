package com.rafa.apialgamoney.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rafa.apialgamoney.model.Categoria;
import com.rafa.apialgamoney.repository.CategoriaRepository;

@Service
public class CategoriaService
{
	@Autowired
	private CategoriaRepository categorias;
	
	public Categoria atualizar(Long codigo, Categoria categoria)
	{
		Categoria categoriaSalva = categorias.findOne(codigo);
		
		if (categoriaSalva == null)
		{
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");
		
		return categorias.save(categoriaSalva);
	}
}
