package br.com.zup.fatura.model;

import br.com.zup.fatura.dto.response.FaturaResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NamedQuery(name = "findFaturaByCartaoAndMesCorrente",
        query = "select f from Fatura f where f.cartao.numeroDoCartao = :numeroDoCartao and mesCorrespondente = :mesCorrespondente")
public class Fatura {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotNull
    @Positive
    private Integer mes;

    @NotNull
    @Positive
    private Integer ano;

    @OneToMany
    private Set<Transacao> transacoes;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Fatura() {
    }

    public Fatura(@NotNull @Positive Integer mes, @NotNull @Positive Integer ano, @NotNull Cartao cartao) {
        this.mes = mes;
        this.ano = ano;
        this.transacoes = new HashSet<>();
        this.cartao = cartao;
    }

    public Fatura(@NotNull @Positive Integer mes, @NotNull @Positive Integer ano, Set<Transacao> transacoes) {
        this.mes = mes;
        this.ano = ano;
        this.transacoes = transacoes;
    }

    public void adicionarTransacaoNaFatura(Transacao transacao){
        this.transacoes.add(transacao);
    }

    public BigDecimal calcularTotalDaFatura(){
        return transacoes.stream()
                .map(transacao -> transacao.retornarValorDaTransacao())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public FaturaResponse toResponse(){
        return new FaturaResponse(this.mes, this.ano, Transacao.toResponseSet(transacoes), calcularTotalDaFatura());
    }
}
