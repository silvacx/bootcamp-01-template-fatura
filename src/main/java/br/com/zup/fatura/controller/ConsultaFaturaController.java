package br.com.zup.fatura.controller;

import br.com.zup.fatura.dto.response.FaturaResponse;
import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.utils.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class ConsultaFaturaController {

    private final EntityManager entityManager;

    public ConsultaFaturaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/{id}/faturas")
    public ResponseEntity buscarDetalhesDaFatura(@PathVariable UUID id){
        final List<Cartao> cartao = entityManager.createNamedQuery("findCartaoByNumero", Cartao.class)
                .setParameter("numeroDoCartao", id)
                .getResultList();

        if(cartao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Arrays.asList("Cartão não encontrado")));
        }

        final List<Fatura> fatura = entityManager.createNamedQuery("findFaturaByCartaoAndMesCorrente", Fatura.class)
                .setParameter("numeroDoCartao", id)
                .setParameter("mesCorrespondente", LocalDate.now().getMonth().getValue())
                .getResultList();

        if(fatura.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Arrays.asList("Não existem transações para a fatura do mês corrente")));
        }

        FaturaResponse response = fatura.get(0).toResponse();
        return ResponseEntity.ok(response);
    }
}