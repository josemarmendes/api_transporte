package br.com.example.api.interfaces.modelmapper;

import br.com.example.api.domain.Passageiro;
import br.com.example.api.domain.PassageiroRepository;
import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.domain.PedidoViagemRepository;
import br.com.example.api.interfaces.PassageiroAPI;
import br.com.example.api.interfaces.input.PedidoViagemInput;
import br.com.example.api.interfaces.output.PedidoViagemOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
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

    public PedidoViagemOutput map(PedidoViagem pedidoViagem){
        PedidoViagemOutput output = new PedidoViagemOutput();
        output.setId(pedidoViagem.getId());
        output.setOrigem(pedidoViagem.getOrigem());
        output.setDestino(pedidoViagem.getDestino());
        output.setStatus(pedidoViagem.getStatus());
        output.setDataCriacao(pedidoViagem.getDataCriacao());

        return output;
    }

    public EntityModel<PedidoViagemOutput> construirObjetoDeSaida(PedidoViagem pedidoViagem, PedidoViagemOutput output){
        EntityModel<PedidoViagemOutput> model = EntityModel.of(output);

        Link passageiroLink = WebMvcLinkBuilder
                .linkTo(PassageiroAPI.class)
                .slash(pedidoViagem.getPassageiro().getId())
                .withRel("passageiro")
                .withTitle(pedidoViagem.getPassageiro().getNome());

        model.add(passageiroLink);

        return model;
    }
}
