package com.gap.gestao_negocios.service;

import java.math.BigDecimal;
import java.util.List;

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

    @Autowired
    private MovimentoCaixaService movimentoCaixaService;

    @Transactional
    public Pedido finalizarVenda(Pedido pedido) {
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedido item : pedido.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

            produto.baixarEstoque(item.getQuantidade());
            produtoRepository.save(produto);

            item.setProduto(produto);
            item.setPedido(pedido);
            item.setPrecoUnitario(produto.getPreco());

            BigDecimal valorItem = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotal = valorTotal.add(valorItem);
        }

        pedido.setValorTotal(valorTotal);

        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        MovimentoCaixa movimentoCaixa = new MovimentoCaixa(TipoMovimento.ENTRADA, pedidoSalvo.getValorTotal(), "Venda realizada - Pedido ID: " + pedidoSalvo.getId());
        movimentoCaixaRepository.save(movimentoCaixa);

        String chavePix = "sua-chave-pix@email.com";
        String nomeLoja = "GAP Gestao Comercial";
        String cidadeLoja = "Sorocaba";

        String pixPayload = PixService.gerarPayloadPix(
                chavePix,
                nomeLoja,
                cidadeLoja,
                pedidoSalvo.getValorTotal(),
                "PEDIDO" + pedidoSalvo.getId()
        );

        return pedidoSalvo;

    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
}
