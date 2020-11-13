package br.com.zup.fatura.listener;

import br.com.zup.fatura.model.Cartao;

import java.util.UUID;

public class CartaoListener {

    private UUID id;
    private String email;

    public Cartao toModel(){
        return new Cartao(id, email);
    }
}
