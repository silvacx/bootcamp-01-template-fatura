package br.com.zup.fatura.listener;

import br.com.zup.fatura.model.Estabelecimento;

public class EstabelecimentoListener {

    private String nome;

    private String cidade;

    private String endereco;

    public Estabelecimento toModel(){
        return new Estabelecimento(nome, cidade, endereco);
    }
}
