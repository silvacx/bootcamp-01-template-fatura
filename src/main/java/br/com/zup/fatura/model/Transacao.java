package br.com.zup.fatura.model;

import br.com.zup.fatura.dto.response.TransacaoResponse;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(generator = "uuid4")
    private UUID id;

    @NotNull
    private UUID transacaoID;

    @NotNull
    @Positive
    private BigDecimal valor;

    @Embedded
    private Estabelecimento estabelecimento;

    @NotNull
    private LocalDateTime efetivadaEm;

    @Deprecated
    public Transacao() {
    }

    public Transacao(UUID transacaoID, BigDecimal valor, Estabelecimento estabelecimento, LocalDateTime efetivadaEm) {
        this.transacaoID = transacaoID;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public Integer retornarMesDaTransacao() {
        return efetivadaEm.getMonthValue();
    }

    public Integer retornarAnoDaTransacao() {
        return efetivadaEm.getYear();
    }

    public BigDecimal retornarValorDaTransacao() {
        return valor;
    }

    public TransacaoResponse toResponse(){
        return new TransacaoResponse(this.valor, this.estabelecimento.toResponse(), this.efetivadaEm);
    }

    public static Set<TransacaoResponse> toResponseSet(Set<Transacao> transacoes){
        Assert.notNull(transacoes, "Não é possível fazer a conversão de uma lista nula");
        return transacoes.stream().map(Transacao::toResponse).collect(Collectors.toSet());
    }
}
