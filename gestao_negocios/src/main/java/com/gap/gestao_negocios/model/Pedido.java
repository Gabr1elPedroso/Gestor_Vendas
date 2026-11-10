package com.gap.gestao_negocios.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private Long id;
    private LocalDateTime dataHora;
    private ArrayList<ItemPedido> itens;
    private BigDecimal valorTotal;
    private String status;
    
}
