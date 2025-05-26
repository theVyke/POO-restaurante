package api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.ItemPedido;
import api.service.ItemPedidoService;

public class ItemPedidoController extends BaseController<ItemPedido>{

    private ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    @Override
    public ItemPedido save(ItemPedido entity) {

        return itemPedidoService.save(entity);
    }

    public ItemPedido update (int id, int novoIdPedido, int novoIdPrato, int novaQuantidade){
        return itemPedidoService.update(id, novoIdPedido, novoIdPrato, novaQuantidade);
    }

    @Override
    public ItemPedido findById(int id) {

        return itemPedidoService.findById(id);
    }

    @Override
    public List<ItemPedido> findAll() {

        //Trata a exceção e retorna uma lista vazia (Controller não deve imprimir mensagens, isso cabe ao Menu)
        try {
            return itemPedidoService.findAll();
        } catch (NoSuchElementException e) {
            return List.of();
        }
    }

    @Override
    public void delete(int id) {

        itemPedidoService.delete(id);
    }

    
    
}
