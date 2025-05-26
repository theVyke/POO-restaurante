package api.repository;

import api.model.BaseEntity;

import java.util.List;

public interface Repository<T extends BaseEntity> {
    T save(T entity); // Método para salvar (Prato, ItemPedido, Pedido) no repositório

    T findById(int id);// Método para encontrar um objeto pelo ID

    List<T> findAll(); // Método para encontrar todos os objetos do mesmo tipo no repositório

    void delete(int id); // Método para deletar um objeto pelo ID
    
}
