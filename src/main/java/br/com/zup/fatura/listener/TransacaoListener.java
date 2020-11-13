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

    public Transacao toModel(){
        return new Transacao(id, valor, estabelecimentoListener.toModel(), cartaoListener.toModel(), efetivadaEm);
    }
}
