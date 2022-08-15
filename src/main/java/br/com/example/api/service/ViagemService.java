package br.com.example.api.service;

import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.domain.PedidoViagemRepository;
import br.com.example.api.domain.PedidoViagemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ViagemService {

    @Autowired
    private PedidoViagemRepository pedidoViagemRepository;

    public PedidoViagem salvarPedidoViagem(PedidoViagem pedidoViagem){
        pedidoViagem.setStatus(PedidoViagemStatus.CREATED);
        pedidoViagem.setDataCriacao(LocalDate.now());
        return pedidoViagemRepository.save(pedidoViagem);
    }
}
