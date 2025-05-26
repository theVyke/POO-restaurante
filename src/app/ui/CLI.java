package app.ui;

import app.ui.menu.ItemPedidoMenu;
import app.ui.menu.PedidoMenu;
import app.ui.menu.PratoMenu;

public class CLI {

    private final InputReader inputReader;

    private final PratoMenu pratoMenu;
    private final ItemPedidoMenu itemPedidoMenu;
    private final PedidoMenu pedidoMenu;


    public CLI(InputReader inputReader, PratoMenu pratoMenu, ItemPedidoMenu itemMenu, PedidoMenu pedidoMenu) {
    this.inputReader = inputReader;
    this.pratoMenu = pratoMenu;
    this.itemPedidoMenu = itemMenu;
    this.pedidoMenu = pedidoMenu;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== SISTEMA CLI ===");
            System.out.println("1. Gerenciar Pedidos");
            System.out.println("2. Gerenciar Itens");
            System.out.println("3. Gerenciar Pratos");
            System.out.println("0. Sair");

            int opcao = inputReader.readInt("Escolha uma opção");

            switch (opcao) {
                case 1 -> pedidoMenu.start();
                case 2 -> itemPedidoMenu.start();
                case 3 -> pratoMenu.start();
                case 0 -> {
                    System.out.println("Saindo...");
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
