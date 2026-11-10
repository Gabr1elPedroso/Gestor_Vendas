package com.gap.gestao_negocios.repository;
import com.gap.gestao_negocios.model.MovimentoCaixa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoCaixaRepository extends JpaRepository <MovimentoCaixa, Long> {
    
}
