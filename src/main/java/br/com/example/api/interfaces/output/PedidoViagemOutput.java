package br.com.example.api.interfaces.output;

import br.com.example.api.domain.Passageiro;
import br.com.example.api.domain.PedidoViagemStatus;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.time.LocalDate;

@Data
public class PedidoViagemOutput{
    private Long id;
    private String origem;
    private String destino;
    private PedidoViagemStatus status;
    private LocalDate dataCriacao;
}
