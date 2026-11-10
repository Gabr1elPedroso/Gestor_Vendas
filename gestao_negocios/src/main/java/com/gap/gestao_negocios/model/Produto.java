package com.gap.gestao_negocios.model;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

public class Produto{
    private Long id;
    private String nome;
    private BigDecimal preco;
    private int quantidadeEstoque;
}