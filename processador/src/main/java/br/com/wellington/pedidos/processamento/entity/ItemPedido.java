package br.com.wellington.pedidos.processamento.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne                       // ✔ correto
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    @ManyToOne                       // ✔ correto
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
}