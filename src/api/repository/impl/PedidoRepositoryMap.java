package api.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.model.Pedido;
import api.model.StatusPedido;
import api.repository.PedidoRepository;

public class PedidoRepositoryMap implements PedidoRepository{

    private Map<Integer, Pedido> pedidosMap = new HashMap<>(); //Segunda implementação

    @Override
    public Pedido save(Pedido entity) {
        //Sem if, pois o método put do Map já faz o papel de criar ou atualizar
        pedidosMap.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Pedido findById(int id) {
        //Sem if, pois o método get do Map já já retorna null se a chave não existir
        return pedidosMap.get(id);
    }

    @Override
    public List<Pedido> findAll() {
        //Retorna todos os pedidos do Map em uma Lista
        return new ArrayList<>(pedidosMap.values());
    }

    @Override
    public void delete(int id) {
        //Deleta o pedido encontrado, caso não haja, retorna null automaticamente
        pedidosMap.remove(id);
    }

    //Exatamente igual ao PedidoRepositoryList, mas com o Map
    @Override
    public Pedido buscarPorMesa(int mesa) {

        for (Pedido pedido : pedidosMap.values()) {
            if (pedido.getMesa() == mesa) {
                return pedido;
            }
        }
        return null; 
    }

    //Exatamente igual ao PedidoRepositoryList, mas com o Map
    @Override
    public List<Pedido> filtrarPorStatus(StatusPedido status) {

        List<Pedido> pedidosFiltrados = new ArrayList<>();

        for (Pedido pedido : pedidosMap.values()) {
            if (pedido.getStatus() == status) {     
                pedidosFiltrados.add(pedido);
            }
        }
        return pedidosFiltrados;
    }


    
}

