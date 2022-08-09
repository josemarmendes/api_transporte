package br.com.example.api.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@Data
@Builder
@Entity
public class Motorista {

    public Motorista() {

    }

    public Motorista(Long id, String nome, LocalDate dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private LocalDate dataDeNascimento;

}
