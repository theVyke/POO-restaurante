package api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.ItemPedido;
import api.model.Pedido;
import api.model.StatusPedido;
import api.service.PedidoService;

public class PedidoController extends BaseController<Pedido>{
    
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Override
    public Pedido save(Pedido entity) {
        
        return pedidoService.save(entity);
    }

    public Pedido update(Pedido entity){
        return null;
    }

    @Override
    public Pedido findById(int id) {
        
        return pedidoService.findById(id);
    }

    @Override
    public List<Pedido> findAll() {
        //Trata a exceção e retorna uma lista vazia (Controller não deve imprimir mensagens, isso cabe ao Menu)
        try {
            return pedidoService.findAll();
        } catch (NoSuchElementException e) {
            return List.of();
        }
    }

    @Override
    public void delete(int id) {
        
        pedidoService.delete(id);
    }

    public void adicionarItemAoPedido(Pedido pedido, ItemPedido item) {
        pedidoService.adicionarItemAoPedido(pedido, item);
    }
    
    public void removerItemDoPedido(Pedido pedido, ItemPedido item) {
        pedidoService.removerItemDoPedido(pedido, item);
    }

    public void atualizarStatus(Pedido pedido, StatusPedido status) {
        pedidoService.atualizarStatus(pedido, status);
    }

    public void resumoGeral(Pedido pedido){
        pedidoService.resumoGeral(pedido);
    }
    public Pedido buscarPorMesa(int mesa){
            
        return pedidoService.buscarPorMesa(mesa);
    }

    public List<Pedido> filtrarPorStatus(StatusPedido status){

        return pedidoService.filtrarPorStatus(status);
    } 


}
