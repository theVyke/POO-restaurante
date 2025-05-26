package api.model;

public class ItemPedido extends BaseEntity {

    private static int contador = 1;
    
    private int idPedido; // ID do pedido, pois um ou mais itens pertencem a um pedido
    private int idPrato; // ID do prato, pois os pratos compõem os itens do pedido
    private int quantidade; // Quantidade de pratos do mesmo tipo no pedido


    // CONSTRUTORES
    public ItemPedido(int idPedido, int idPrato, int quantidade) {
        this.setId(contador++);
        this.idPedido = idPedido;
        this.idPrato = idPrato;
        this.quantidade = quantidade;
    }

    public ItemPedido() {
        this.setId(contador++);
    }

    // GETTERS E SETTERS
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPrato() {
        return idPrato;
    }

    public void setIdPrato(int idPrato) {
        this.idPrato = idPrato;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // MÉTODOS
    public void exibirItemPedido() {
    System.out.printf("ITENS DO PEDIDO:%nItem ID: %s%nPedido ID: %s%nPrato ID: %s%nQuantidade: %d%nValor dos Itens: %.2f%n", 
                      getId(), idPedido, idPrato, quantidade);
    }

    @Override //Uso de IA para geração de StringBuilder personalizado
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(getId())
        .append("\nID do Prato: ").append(idPrato)
        .append(" | Quantidade: ").append(quantidade)
        .append("\n");
        return sb.toString();
    }


    
}
