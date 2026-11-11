package com.gap.gestao_negocios.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gap.gestao_negocios.model.ItemPedido;
import com.gap.gestao_negocios.model.MovimentoCaixa;
import com.gap.gestao_negocios.model.Pedido;
import com.gap.gestao_negocios.model.Produto;
import com.gap.gestao_negocios.model.TipoMovimento;
import com.gap.gestao_negocios.repository.MovimentoCaixaRepository;
import com.gap.gestao_negocios.repository.PedidoRepository;
import com.gap.gestao_negocios.repository.ProdutoRepository;

import jakarta.transaction.Transactional;


@Service
public class VendasService {
    
    @Autowired 
    private ProdutoRepository produtoRepository;

    @Autowired 
    private PedidoRepository pedidoRepository;

    @Autowired 
    private MovimentoCaixaRepository movimentoCaixaRepository;


    @Transactional 
    public Pedido finalizarVenda(Pedido pedido){
        for(ItemPedido item : pedido.getItens()){
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));
            
            produto.baixarEstoque(item.getQuantidade());
            produtoRepository.save(produto);

            item.setProduto(produto);
            item.setPedido(pedido);

        }


        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        MovimentoCaixa movimentoCaixa = new MovimentoCaixa(TipoMovimento.ENTRADA, pedidoSalvo.getValorTotal(), "Venda realizada - Pedido ID: " + pedidoSalvo.getId());
        movimentoCaixaRepository.save(movimentoCaixa);

        return pedidoSalvo;
        
    }
}
