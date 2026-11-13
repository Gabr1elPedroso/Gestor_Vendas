package com.gap.gestao_negocios.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gap.gestao_negocios.model.MovimentoCaixa;
import com.gap.gestao_negocios.model.TipoMovimento;
import com.gap.gestao_negocios.repository.MovimentoCaixaRepository;


@Service
public class MovimentoCaixaService {

    @Autowired
    private MovimentoCaixaRepository movimentoCaixaRepository;





    public MovimentoCaixa registrarMovimento(MovimentoCaixa movimentoCaixa){
        return movimentoCaixaRepository.save(movimentoCaixa);
    }

    
    public List<MovimentoCaixa> listarMovimentos(){
        return movimentoCaixaRepository.findAll();
    }
    

     public MovimentoCaixa listarMovimentosPorId(Long id){
        return movimentoCaixaRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimento de caixa não encontrado: " + id));
    }

    public BigDecimal calcularSaldoAtual(){
        List<MovimentoCaixa> movimentos = movimentoCaixaRepository.findAll();
        BigDecimal saldo = BigDecimal.ZERO;

        for(MovimentoCaixa movimento : movimentos){
            if(movimento.getTipo() == TipoMovimento.ENTRADA){
                saldo = saldo.add(movimento.getValor());
            } else if(movimento.getTipo() == TipoMovimento.SAIDA){
                saldo = saldo.subtract(movimento.getValor());
            }
        }

        return saldo;
    }
}
