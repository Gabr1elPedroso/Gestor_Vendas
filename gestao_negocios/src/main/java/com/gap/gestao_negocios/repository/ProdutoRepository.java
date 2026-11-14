package com.gap.gestao_negocios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gap.gestao_negocios.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> { 
    List<Produto> findByAtivoTrue();
}
