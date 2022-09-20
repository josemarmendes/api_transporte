package br.com.example.api.domain;

import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.domain.PedidoViagemRepository;
import br.com.example.api.domain.PedidoViagemStatus;
import br.com.example.api.outcoming.GMapsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViagemService {

    @Autowired
    private PedidoViagemRepository pedidoViagemRepository;

    @Autowired
    private GMapsService gMapsService;

    private static final Integer MAX_TRAVEL_TIME = 600;

    public PedidoViagem salvarPedidoViagem(PedidoViagem pedidoViagem){
        pedidoViagem.setStatus(PedidoViagemStatus.CREATED);
        pedidoViagem.setDataCriacao(LocalDate.now());
        return pedidoViagemRepository.save(pedidoViagem);
    }

    public List<PedidoViagem> listarViagensMaisProximas(String enderecoAtual){
        List<PedidoViagem> pedidoViagems =  pedidoViagemRepository.findByStatus(PedidoViagemStatus.CREATED);

        return pedidoViagems
                .stream()
                .filter(tr -> gMapsService.buscarDistanciaEntreEnderecos(enderecoAtual, tr.getDestino()) < MAX_TRAVEL_TIME)
                .collect(Collectors.toList());
    }
}
