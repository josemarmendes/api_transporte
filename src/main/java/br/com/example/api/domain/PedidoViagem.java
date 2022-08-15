package br.com.example.api.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class PedidoViagem {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Passageiro passageiro;
    private String origem;
    private String destino;
    @Enumerated(EnumType.STRING)
    private PedidoViagemStatus status;
    private LocalDate dataCriacao;
}
