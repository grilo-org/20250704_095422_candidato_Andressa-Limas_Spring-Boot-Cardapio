package com.cardapio.cardapio.model;

import org.springframework.stereotype.Component;

@Component
public class Mensage {

    private String mensagem;

    public String getMensage() {
        return mensagem;
    }

    public void setMensage(String mensagem) {
        this.mensagem = mensagem;
    }
 
}

