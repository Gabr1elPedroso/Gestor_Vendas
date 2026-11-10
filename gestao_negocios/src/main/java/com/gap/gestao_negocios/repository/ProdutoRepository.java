package com.gap.gestao_negocios.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gap.gestao_negocios.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository <Produto, Long> { 
}
