package com.gap.gestao_negocios.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;


public class ItemPedido {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    
    @JoinColumn (name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private int quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitario;


    public ItemPedido(){

    }

    public ItemPedido(Produto produto, int quantidade, Pedido pedido){
        this.produto = produto;
        this.quantidade = quantidade;
        this.pedido = pedido;
        this.precoUnitario = produto.getPreco();
    }

    public BigDecimal calcularPrecoUnitario(Produto preco, int quantidade){
        return this.precoUnitario = preco.getPreco().multiply(new BigDecimal(quantidade));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
}
