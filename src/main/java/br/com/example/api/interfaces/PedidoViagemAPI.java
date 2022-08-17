package br.com.example.api.interfaces;

import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.interfaces.input.PedidoViagemInput;
import br.com.example.api.interfaces.modelmapper.PedidoViagemMapper;
import br.com.example.api.interfaces.output.PedidoViagemOutput;
import br.com.example.api.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pedidoViagem",produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoViagemAPI {

    @Autowired
    private ViagemService viagemService;

    @Autowired
    private PedidoViagemMapper mapper;

    @PostMapping
    public EntityModel<PedidoViagemOutput> criarPedidoDeViagens(@RequestBody PedidoViagemInput pedidoViagemInput){
        PedidoViagem pedidoViagem = viagemService.salvarPedidoViagem(mapper.map(pedidoViagemInput));
        PedidoViagemOutput output = mapper.map(pedidoViagem);

        return mapper.construirObjetoDeSaida(pedidoViagem, output);
    }


}
