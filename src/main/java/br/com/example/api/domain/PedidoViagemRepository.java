package br.com.example.api.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoViagemRepository extends JpaRepository<PedidoViagem, Long> {
    List<PedidoViagem> findByStatus(PedidoViagemStatus status);
}
