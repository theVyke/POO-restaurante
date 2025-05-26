package api.repository.impl;

import java.util.List;

import api.model.ItemPedido;
import api.repository.Repository;

public class ItemPedidoRepository implements Repository<ItemPedido>{

    private List<ItemPedido> itens;

    public ItemPedidoRepository(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public ItemPedido save(ItemPedido entity) {
        // Verifica se o item já existe na lista
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getId() == entity.getId()) {
                itemPedido.setIdPedido(entity.getIdPedido());
                itemPedido.setIdPrato(entity.getIdPrato());
                itemPedido.setQuantidade(entity.getQuantidade());
                return itemPedido;
            }
        }
        // Se não existe, adiciona o novo item à lista
        itens.add(entity);
        return entity;
    }

    @Override
    public ItemPedido findById(int id) {

        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getId() == id) {
                return itemPedido;
            }
        }
        return null;
    }

    @Override
    public List<ItemPedido> findAll() {

        return itens;
    }

    @Override
    public void delete(int id) {

        // Percorre a lista de itens e remove o item com o ID correspondente
        for (int i = 0; i < itens.size(); i++) {
            if (itens.get(i).getId() == id) {
                itens.remove(i);
                break;
            }
        }
    }


}
