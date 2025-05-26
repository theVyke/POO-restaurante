package api.repository.impl;

import java.util.ArrayList;

import java.util.List;

import api.model.Pedido;
import api.model.StatusPedido;
import api.repository.PedidoRepository;


public class PedidoRepositoryList implements PedidoRepository{

    private List<Pedido> pedidos = new ArrayList<>(); //Primeira implementação

    public PedidoRepositoryList(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public PedidoRepositoryList() {
    }

    @Override
    public Pedido save(Pedido entity) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == entity.getId()) {
                pedido.setMesa(entity.getMesa());
                pedido.setItens(entity.getItens());
                //Não foram implementados os atributos de status e hora, pois são gerados na instanciação do objeto
                //e terão métodos próprios, como atualizarStatus
                return pedido;
            }
        }
        pedidos.add(entity);
        return entity;
    }

    @Override
    public Pedido findById(int id) {

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
            
        }

        return null;
    }

    @Override
    public List<Pedido> findAll() {

        return pedidos;
    }

    @Override
    public void delete(int id) {

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == id) {
                pedidos.remove(i);
                break;
            }
        }
    }

    // Método para retornar um pedido com base uma mesa (similar a busar por ID)
    @Override
    public Pedido buscarPorMesa(int mesa) { // A diferença é que é retornar o objeto Pedido, e não a lista. Pois uma mesa só pode ter um pedido em aberto
        for (Pedido pedido : pedidos) {
            if (pedido.getMesa() == mesa) {
                return pedido;
            }
        }
        return null;//Se não existir, retorna null
    }

    @Override
    public List<Pedido> filtrarPorStatus(StatusPedido status) {
        List<Pedido> pedidosFiltrados = new ArrayList<>();
         //Percorre todos os pedidos da lista    
        for (Pedido pedido : pedidos) {
            //Verifica se o status do pedido é igual ao status passado como parâmetro
            if (pedido.getStatus() == status) {
                //Adiciona o pedido à lista de pedidos filtrados
                pedidosFiltrados.add(pedido);
            } 
        }
        return pedidosFiltrados;
        
    }

    
}
