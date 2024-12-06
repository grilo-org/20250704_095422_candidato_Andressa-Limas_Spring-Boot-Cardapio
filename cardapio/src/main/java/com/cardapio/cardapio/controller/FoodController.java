package com.cardapio.cardapio.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cardapio.cardapio.model.Cliente;
import com.cardapio.cardapio.model.Food;
import com.cardapio.cardapio.repository.Repository;
import com.cardapio.cardapio.service.Servico;

import jakarta.validation.Valid;

@RestController
public class FoodController {

    @Autowired
    private Repository acao;

    @Autowired
    private Servico servico;

    @PostMapping("/api")
    public ResponseEntity<?> cadastrar(@RequestBody Food obj) {
        return servico.cadastrar(obj);
    }

 
    @GetMapping("/api")
    public ResponseEntity<?> selecionar (){
        return servico.selecionar();
    }

    @GetMapping("/api/{codigo}")
    public ResponseEntity<?> selecionarPeloCodigFood(@PathVariable int codigo){
        return servico.selecionarPeloCodigo(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?>  editar(@RequestBody Food obFood){
        return servico.editar(obFood);
    }

    @DeleteMapping("/api/{codigo}")
    public ResponseEntity<?> remover (@PathVariable int codigo){
       return servico.remover(codigo);
    }

    @GetMapping("/api/nomeContem")
    public List<Food> nomeContem(){
        return acao.findByNomeContaining("c");
    }

    @GetMapping("/api/somaPrecos")
    public int somaPrecos(){
       return acao.somaPrecos(); 
    }

    @GetMapping("/api/precoMaiorIgual")
    public List<Food> precoMaiorIgual(){
       return acao.precoMaiorIgual(8); 
    }


    @GetMapping("/contador")
    public long contador(){
        return acao.count();
    }

    @GetMapping("")
    public String mensagem(){
        return "Bem-vindo(a) ao nosso Restaurante Sabor Nordestino";
    }

    @GetMapping("/cardapio/{nome}")
    public String saudacao(@PathVariable String nome){
        return "Desfrute do nosso cardapio " + nome;
    }

    @PostMapping("/Food")
    public Food comida (@RequestBody Food c){
        return c;
    }

    @PostMapping("/cliente")
    public void cliente(@Valid @RequestBody Cliente obj){

    }

}
