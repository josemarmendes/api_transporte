package br.com.example.api.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Motorista {

    @Id
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;

}
