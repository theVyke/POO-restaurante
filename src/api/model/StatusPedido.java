package api.model;

    // Criação do Enum para representar os status do pedido

    public enum StatusPedido {
        ABERTO, //Feito automaticamente quando o pedido é criado
        PRONTO, //Quando o pedido está pronto para ser servido
        FINALIZADO, //Quando o pedido foi entregue e pago
        CANCELADO; //Quando o pedido foi cancelado antes de servido
}
