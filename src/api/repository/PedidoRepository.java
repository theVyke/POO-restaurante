package api.repository;

import java.util.List;

import api.model.Pedido;
import api.model.StatusPedido;

//Interface própria para implementação dos Pedidos, ela engloba os métodos específicos para os Pedidos
public interface PedidoRepository extends Repository<Pedido> {
    Pedido buscarPorMesa(int mesa);
    List<Pedido> filtrarPorStatus(StatusPedido status);
}
