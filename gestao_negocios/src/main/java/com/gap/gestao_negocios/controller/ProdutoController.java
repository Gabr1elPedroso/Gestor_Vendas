package com.gap.gestao_negocios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gap.gestao_negocios.model.Produto;
import com.gap.gestao_negocios.service.ProdutoService;


@RestController 
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired 
    private ProdutoService ProdutoService;


    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto){
        try{
            Produto produtoCadastrado = ProdutoService.salvar(produto);

            return ResponseEntity.status(HttpStatus.CREATED).body(produtoCadastrado);
            
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Produto> deletarProduto(@PathVariable Long id){
        try{
            ProdutoService.deletarProduto(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


    @GetMapping 
    public ResponseEntity <List<Produto>> buscarTodos(){
        try{
            List<Produto> produtos = ProdutoService.listarTodos();
            return ResponseEntity.ok(produtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping ("{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        try{
            Produto produto = ProdutoService.buscarPorId(id);
            return ResponseEntity.ok(produto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping
    public ResponseEntity<Produto> atualizarProduto(@RequestBody Produto produto){
        try{
            Produto produtoAtualizado = ProdutoService.atualizar(produto);
            return ResponseEntity.ok(produtoAtualizado);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
}
