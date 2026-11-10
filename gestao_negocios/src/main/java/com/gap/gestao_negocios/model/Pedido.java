package com.gap.gestao_negocios.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 
@Table(name = "pedidos")
public class Pedido {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private ArrayList<ItemPedido> itens;
    
    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(nullable = false, length = 50)
    private String status;



    public Pedido(){
        this.dataHora = LocalDateTime.now();
        this.valorTotal = BigDecimal.ZERO;
        this.status = "PENDENTE";
    }

    public void adicionarItem(Produto produto, int quantidade){
        ItemPedido item = new ItemPedido(produto, quantidade, this);
        this.itens.add(item);
        recalcularValorTotal();
    }

    private void recalcularValorTotal(){
        BigDecimal totalCalculado = BigDecimal.ZERO;
        for(ItemPedido item : this.itens){
            BigDecimal subtotal = item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()));
            totalCalculado = totalCalculado.add(subtotal);
        }
        this.valorTotal = totalCalculado;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
