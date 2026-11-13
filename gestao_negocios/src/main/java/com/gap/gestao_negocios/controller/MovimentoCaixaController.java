package com.gap.gestao_negocios.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gap.gestao_negocios.model.MovimentoCaixa;
import com.gap.gestao_negocios.service.MovimentoCaixaService;

@RestController 
@RequestMapping ("/api/movimentos-caixa")
public class MovimentoCaixaController {

    @Autowired 
    private MovimentoCaixaService movimentoCaixaService;


    @PostMapping 
    public ResponseEntity<MovimentoCaixa> registrarMovimento(@RequestBody MovimentoCaixa movimentoCaixa){
        try{
            MovimentoCaixa movimentoRegistrado = movimentoCaixaService.registrarMovimento(movimentoCaixa);
            return ResponseEntity.status(HttpStatus.CREATED).body(movimentoRegistrado);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping 
    public ResponseEntity <List<MovimentoCaixa>> listarMovimentos(){
        try{

            List<MovimentoCaixa> movimentos = movimentoCaixaService.listarMovimentos();
            return ResponseEntity.ok(movimentos);
        
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping ("/{id}")
    public ResponseEntity<MovimentoCaixa> listarMovimentoPorId(@PathVariable Long id){
        try{
            MovimentoCaixa movimento = movimentoCaixaService.listarMovimentosPorId(id);
            return ResponseEntity.ok(movimento);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/saldo")
    public ResponseEntity<BigDecimal> calcularSaldoAtual(){
        try{
            BigDecimal saldoAtual = movimentoCaixaService.calcularSaldoAtual();
            return ResponseEntity.ok(saldoAtual);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    
}
