package api.service;

import java.util.List;
import java.util.NoSuchElementException;

import api.model.ItemPedido;
import api.model.Pedido;
import api.model.Prato;
import api.model.StatusPedido;
import api.repository.PedidoRepository;
import api.repository.impl.PratoRepository;

public class PedidoService extends BaseService<Pedido>{

    private PedidoRepository repository;
    private PratoRepository pratoRepository;

    public PedidoService(PedidoRepository repository, PratoRepository pratoRepository) {
        this.repository = repository;
        this.pratoRepository = pratoRepository;
    }

    @Override
    public Pedido save(Pedido entity) {
        // Regra: Cada mesa deve ter apenas um pedido em aberto, com diversos itens
        if (entity.getStatus() == StatusPedido.ABERTO) {
            //Variavel auxiliar para armazenar a consulta
            Pedido pedidoAberto = repository.buscarPorMesa(entity.getMesa());
            if (pedidoAberto != null && pedidoAberto.getStatus() == StatusPedido.ABERTO && pedidoAberto.getId() != entity.getId()) {
                throw new IllegalStateException("Já existe um pedido em aberto para a mesa " + entity.getMesa());
            }
    }
    
        return repository.save(entity);
    }

    public Pedido update(Pedido entity){
        Pedido pedido = findById(entity.getId());

        pedido.setMesa(pedido.getMesa());

        return save(entity);
    }

    @Override
    public Pedido findById(int id) {
        //Variavel auxiliar para armazenar a consulta
        Pedido pedidoConsultado = repository.findById(id);
        //Condição: Retorna exceção se não existir itens com o ID consultado
        if (pedidoConsultado == null) {
            throw new NoSuchElementException("Pedido com o ID " + id + " não foi encontrado.");
        }
        return pedidoConsultado;
    }

    @Override
    public List<Pedido> findAll() {
        List<Pedido> pedidosArmazenados = repository.findAll();
        if (pedidosArmazenados.isEmpty()) {
            throw new NoSuchElementException("Não há pedidos cadastrados.");
        }
        return pedidosArmazenados;
    }

    @Override
    public void delete(int id) {
        Pedido entity;

        entity = repository.findById(id);
        if (entity == null) {
            throw new NoSuchElementException("Pedido com o ID " + id + " não foi encontrado.");
       }
        //Regra: Pedidos só podem ser excluídos se estiverem com status CANCELADO
       if (entity.getStatus() != StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Pedidos só podem ser excluídos se estiverem com status CANCELADO");
       }
        
        repository.delete(id);
    }

    public void adicionarItemAoPedido(Pedido pedido, ItemPedido item) {
        //Regra: Somente pedidos com status ABERTO podem receber itens;
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            throw new IllegalStateException("Só é possível adicionar itens em pedidos com status ABERTO.");
        }

        pedido.getItens().add(item);
    }

    public void removerItemDoPedido(Pedido pedido, ItemPedido item) {
        //Regra: Somente pedidos com status ABERTO podem ter itens removidos
        if (pedido.getStatus() != StatusPedido.ABERTO) {
            throw new IllegalStateException("Só é possível remover itens de pedidos com status ABERTO.");
        }

        pedido.getItens().remove(item);
    }

    public void atualizarStatus(Pedido pedido, StatusPedido novoStatus) {
    StatusPedido statusAtual = pedido.getStatus();

    // Só permite atualizar status se o pedido estiver ABERTO ou PRONTO
    if (statusAtual != StatusPedido.ABERTO && statusAtual != StatusPedido.PRONTO) {
        throw new IllegalStateException("Só é possível atualizar o status de pedidos ABERTOS ou PRONTOS.");
    }

    // Se for finalizar, precisa ter pelo menos um item
    if (novoStatus == StatusPedido.FINALIZADO) {
        if (pedido.getItens().isEmpty()) {
            throw new IllegalStateException("Um pedido só pode ser finalizado se tiver pelo menos um item.");
        }
        pedido.setStatus(StatusPedido.FINALIZADO);
        return;
    }

    // Se for cancelar, só pode se não tiver itens
    if (novoStatus == StatusPedido.CANCELADO) {
        if (!pedido.getItens().isEmpty()) {
            throw new IllegalStateException("Só é possível cancelar pedidos sem itens.");
        }
        pedido.setStatus(StatusPedido.CANCELADO);
        return;
    }

    // Permite transições normais para ABERTO ou PRONTO
    if (novoStatus == StatusPedido.ABERTO || novoStatus == StatusPedido.PRONTO) {
        pedido.setStatus(novoStatus);
        return;
    }

    throw new IllegalArgumentException("Transição de status inválida.");
    }

    public double calcularValorTotal(List<ItemPedido> itens) {
        
        double total = 0.0;
        for (ItemPedido item : itens) {
            // Busca o prato pelo idPrato diretamente no repositório
            Prato prato = pratoRepository.findById(item.getIdPrato());
            if (prato != null) {
                total += prato.getPreco() * item.getQuantidade();
            }
        }
        return total;
    }

    public void resumoGeral(Pedido pedido) {

        if (pedido.getItens().isEmpty()) {
            throw new NoSuchElementException("Não há itens no pedido");        
        }
        
        double valorTotal = calcularValorTotal(pedido.getItens());

        pedido.exibirResumo(valorTotal);
    }
    
    public Pedido buscarPorMesa(int mesa){
        Pedido pedidoMesa = repository.buscarPorMesa(mesa); 
        
          if (pedidoMesa == null) {
            throw new NoSuchElementException("Não há pedido em aberto para mesa " + mesa);
          }

        return pedidoMesa;
    }

    public List<Pedido> filtrarPorStatus(StatusPedido status){
        List<Pedido> pedidosFiltrados = repository.filtrarPorStatus(status);
        
        return pedidosFiltrados;
    } 

    
}
