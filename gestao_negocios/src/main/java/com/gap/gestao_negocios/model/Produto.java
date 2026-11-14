package com.gap.gestao_negocios.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity 
@Table(name = "produtos")
public class Produto{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(name = "quantidade_estoque", nullable = false)
    private int quantidadeEstoque;

    @Column 
    private Boolean ativo = true;


    public Produto() {

    }


    public void baixarEstoque(int quantidadeVendida){
        if(this.quantidadeEstoque < quantidadeVendida){
            throw new RuntimeException("Quantidade em estoque insuficiente para o produto: " + this.nome);
        }
        this.quantidadeEstoque -= quantidadeVendida;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }






}