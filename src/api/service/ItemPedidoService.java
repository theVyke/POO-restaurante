package api.service;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.ItemPedido;
import api.model.Pedido;
import api.model.Prato;
import api.repository.Repository;

public class ItemPedidoService extends BaseService<ItemPedido> {
    
    private Repository<ItemPedido> repository;
    private Repository<Pedido> pedidoRepository;
    private Repository<Prato> pratoRepository;

    public ItemPedidoService(Repository<ItemPedido> repository, Repository<Pedido> pedidoRepository, Repository<Prato> pratoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.pratoRepository = pratoRepository;
    }

    @Override
    public ItemPedido save(ItemPedido entity) {
        // Validação do Pedido
        if (pedidoRepository.findById(entity.getIdPedido()) == null) {
            throw new IllegalArgumentException("Pedido com o ID " + entity.getIdPedido() + " não existe.");
        }
        // Validação do Prato
        if (pratoRepository.findById(entity.getIdPrato()) == null) {
            throw new IllegalArgumentException("Prato com o ID " + entity.getIdPrato() + " não existe.");
        }
        //Regra: Quantidade de cada item deve ser positiva (mínimo 1);
        if (entity.getQuantidade() < 1) {
            throw new IllegalArgumentException("Quantidade de cada item deve ser no mínimo 1.");
        }
        
        return repository.save(entity);
    }

    public ItemPedido update(int id, int novoIdPedido, int novoIdPrato, int novaQuantidade){
        ItemPedido itemPedido = findById(id);
        itemPedido.setIdPedido(novoIdPedido);
        itemPedido.setIdPrato(novoIdPrato);
        itemPedido.setQuantidade(novaQuantidade);
        return save(itemPedido); // Reaproveita o método save para atualizar
    }

    @Override
    public ItemPedido findById(int id) {
        //Variavel auxiliar para armazenar a consulta
        ItemPedido itensConsultados = repository.findById(id);
        //Condição: Retorna exceção se não existir itens com o ID consultado
        if (itensConsultados == null) {
            throw new NoSuchElementException("Item de Pedido com o ID " + id + " não foi encontrado.");
        }

        return itensConsultados;
    }

    @Override
    public List<ItemPedido> findAll() {
        List<ItemPedido> itensArmazenados = repository.findAll();

        if (itensArmazenados.isEmpty()) {
            throw new NoSuchElementException("Não há itens de pedido cadastrados.");
        }

        return itensArmazenados;
    }

    @Override
    public void delete(int id) {
        ItemPedido entity;

        entity = repository.findById(id);
        if (entity == null) {
            throw new NoSuchElementException("Pedido com o ID " + id + " não foi encontrado.");
       }

        repository.delete(id);
    }


}
