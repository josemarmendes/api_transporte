package br.com.example.api.interfaces.modelmapper;

import br.com.example.api.domain.Passageiro;
import br.com.example.api.domain.PassageiroRepository;
import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.domain.PedidoViagemRepository;
import br.com.example.api.interfaces.input.PedidoViagemInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PedidoViagemMapper {
    @Autowired
    private PassageiroRepository passageiroRepository;

    public PedidoViagem map(PedidoViagemInput input){
        Passageiro passageiro = passageiroRepository
                .findById(input.getPassageiroId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        PedidoViagem pedidoViagem = new PedidoViagem();
        pedidoViagem.setPassageiro(passageiro);
        pedidoViagem.setOrigem(input.getOrigem());
        pedidoViagem.setDestino(input.getDestino());

        return pedidoViagem;
    }
}
