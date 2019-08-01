package com.rafa.apialgamoney.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafa.apialgamoney.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>
{}
