package com.gap.gestao_negocios.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "movimentos_caixa")
public class MovimentoCaixa {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoMovimento tipo;
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;
    @Column(length = 255)
    private String descricao;


    public MovimentoCaixa() {
       this.dataHora = LocalDateTime.now();
    }

    public MovimentoCaixa(TipoMovimento tipo, BigDecimal valor, String descricao){
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
        this.descricao = descricao;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pedido getStatus() {
        return status;
    }

    public void setStatus(Pedido status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
