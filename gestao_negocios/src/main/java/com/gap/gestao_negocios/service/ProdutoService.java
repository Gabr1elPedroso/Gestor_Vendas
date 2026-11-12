package com.gap.gestao_negocios.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gap.gestao_negocios.model.Produto;
import com.gap.gestao_negocios.repository.ProdutoRepository;

@Service 
public class ProdutoService {

    @Autowired 
    private ProdutoRepository produtoRepository;



    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto atualizar(Produto produto){
        Produto produtoExistente = produtoRepository.findById(produto.getId()).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + produto.getId()));
        produtoExistente.setNome(produto.getNome());
        produtoExistente.setPreco(produto.getPreco());
        produtoExistente.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        return produtoRepository.save(produtoExistente);
    }

    public Produto deletar(Long id){
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
        produtoRepository.delete(produtoExistente);
        return produtoExistente;
    }

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado: " + id));
    }
    
}
