package br.com.example.api.interfaces.input;

import br.com.example.api.domain.Passageiro;
import lombok.Data;

@Data
public class PedidoViagemInput {

    Long passageiroId;
    String origem;
    String destino;
}
