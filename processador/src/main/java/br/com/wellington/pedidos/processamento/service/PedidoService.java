package br.com.wellington.pedidos.processamento.service;

import br.com.wellington.pedidos.processamento.entity.ItemPedido;
import br.com.wellington.pedidos.processamento.entity.Pedido;
import br.com.wellington.pedidos.processamento.repository.PedidoRepository;
import br.com.wellington.pedidos.processamento.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository pedidoRepository;
    private final ProdutoService service;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService service, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.service = service;
        this.itemPedidoService = itemPedidoService;
    }

    // salvamos produtos
    public void save(Pedido pedido){
        service.save(pedido.getItens());

        List<ItemPedido> itens = itemPedidoService.save(pedido.getItens());

        pedidoRepository.save(pedido);

        itemPedidoService.updateItemPedido(itens, pedido);

        logger.info("pedido salvo. {} ", pedido.toString());

    }

}
