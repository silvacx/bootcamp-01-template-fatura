package br.com.zup.fatura.dto.response;

public class EstabelecimentoResponse {

    private String nome;

    private String cidade;

    private String endereco;

    public EstabelecimentoResponse(String nome, String cidade, String endereco) {
        this.nome = nome;
        this.cidade = cidade;
        this.endereco = endereco;
    }
}
