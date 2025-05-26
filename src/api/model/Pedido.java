package api.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Pedido extends BaseEntity{
    
    private static int contador = 1;

    private int mesa;
    private StatusPedido status; // Status do pedido, que pode ser PENDENTE, EM_PREPARACAO, PRONTO ou FINALIZADO
    private List<ItemPedido> itens; // Lista de itens do pedido, pois um pedido pode ter vários itens
    private LocalDateTime hora; // Data e Hora em que o pedido foi feito, Biblioteca LocalDateTime

    // CONSTRUTORES
    public Pedido(int mesa) {
        this.setId(contador++);
        this.mesa = mesa;
        this.status = StatusPedido.ABERTO; // O status padrão é ABERTO quando o pedido é criado
        this.itens = new ArrayList<>(); // Inicializa a lista de itens como uma nova lista vazia
        this.hora = LocalDateTime.now(); // A data e hora é definida como a hora atual no momento da criação do pedido
    }

    public Pedido() {
        this.setId(contador++);
        this.itens = new ArrayList<>();
    }
    
    // GETTERS E SETTERS

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDateTime getHora() {
        return hora;
    }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    // MÉTODOS

    // Método para facilitar a visualização dos dados do pedido (mais robusto que o toString)
    public void exibirResumo(double valorTotal) {

        // Formatação da data e hora para o formato "dd/MM/yyyy HH:mm:ss"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy | HH:mm");
        String horaFormatada = hora.format(formatter);

        // Exibição dos dados do pedido
        System.out.println("\nResumo do Pedido:");
        System.out.print("\nID do Pedido: " + getId());
        System.out.print("\nMesa: " + mesa);
        System.out.print("\nStatus: " + status);
        System.out.print("\nData/Hora: " + horaFormatada);
        System.out.printf("\nValor total: R$ %.2f%n", valorTotal);
    }

    @Override //Uso de IA para geração de StringBuilder personalizado
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mesa: ").append(getMesa())
        .append("\nID Pedido: ").append(getId())
        .append(" | Status: ").append(getStatus())
        .append(" | ITENS:\n");

        for (ItemPedido item : getItens()) {
            sb.append("- ").append(item.toString()).append("\n");
        }

        return sb.toString();
    }
}
