package br.com.zup.fatura.listener;

import br.com.zup.fatura.model.Transacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TransacaoListener {

    private UUID id;

    private BigDecimal valor;

    private EstabelecimentoListener estabelecimentoListener;

    private CartaoListener cartaoListener;

    private LocalDateTime efetivadaEm;

    public Transacao toTransacao(){
        return new Transacao(this.id, this.valor, this.estabelecimentoListener.toModel(), this.efetivadaEm);
    }
}
