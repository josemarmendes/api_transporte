package br.com.example.api.interfaces;

import br.com.example.api.domain.PedidoViagem;
import br.com.example.api.interfaces.input.PedidoViagemInput;
import br.com.example.api.interfaces.modelmapper.PedidoViagemMapper;
import br.com.example.api.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/pedidosViagens",produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoViagemAPI {

    @Autowired
    private ViagemService viagemService;

    private PedidoViagemMapper mapper;

    @PostMapping
    public PedidoViagem criarPedidoDeViagens(@RequestBody PedidoViagemInput pedidoViagemInput){
        return viagemService.salvarPedidoViagem(mapper.map(pedidoViagemInput));
    }


}
