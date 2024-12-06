package com.cardapio.cardapio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cardapio.cardapio.model.Food;
import com.cardapio.cardapio.model.Mensage;
import com.cardapio.cardapio.repository.Repository;

@Service
public class Servico {
    @Autowired
    private Mensage mensagem;
    
    @Autowired
    private Repository acao;

    public ResponseEntity<?> cadastrar(Food obj) {
        if (obj.getNome().equals("")) {
            mensagem.setMensage("O nome precisa ser preenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getPreco() < 0) {
            mensagem.setMensage("Informe um preço válido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> selecionar(){
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> selecionarPeloCodigo(int codigo){
        if(acao.countByCodigo(codigo) == 0){
            mensagem.setMensage("Não foi encontrada nenhuma opção de comida no cardapio!");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.findByCodigo(codigo), HttpStatus.OK);
        }
    }

   public ResponseEntity<?> editar(Food obj){
    if(acao.countByCodigo(obj.getCodigo()) == 0){
        mensagem.setMensage("O código informado não exite.");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }else if(obj.getNome().equals("")){
        mensagem.setMensage("É necessário informar um nome.");
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }else if(obj.getPreco() < 0){
        mensagem.setMensage("Informe um preço válido.");
        return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
    }else{
        return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
    }
   }


   public ResponseEntity<?> remover(int codigo){
    if(acao.countByCodigo(codigo) == 0){
        mensagem.setMensage("O código informado não exite.");
        return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }else{
        Food obj = acao.findByCodigo(codigo);
        acao.delete(obj);
        mensagem.setMensage("Opção removida do cardapio");
        return new ResponseEntity<>(mensagem, HttpStatus.OK);
    }
   }
}


