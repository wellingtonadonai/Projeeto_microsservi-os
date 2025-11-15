package br.com.wellington.pedidos.processamento.repository;

import br.com.wellington.pedidos.processamento.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
}
