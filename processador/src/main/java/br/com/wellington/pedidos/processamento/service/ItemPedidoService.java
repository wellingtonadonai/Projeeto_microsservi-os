package br.com.wellington.pedidos.processamento.service;

import br.com.wellington.pedidos.processamento.entity.ItemPedido;
import br.com.wellington.pedidos.processamento.entity.Pedido;
import br.com.wellington.pedidos.processamento.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    public List<ItemPedido> save(List<ItemPedido> itens) {
        return repository.saveAll(itens);


    }

    public void save(ItemPedido itemPedido){
        repository.save(itemPedido);

    }

    public void updateItemPedido(List<ItemPedido> itens, Pedido pedido) {

        itens.forEach(item ->{
            item.setPedido(pedido); // iformando ao item o seu pedido
            this.save(itens);
        });

    }
}
