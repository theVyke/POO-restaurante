package app.ui.menu;

import api.controller.ItemPedidoController;

import api.model.ItemPedido;
import app.ui.InputReader;

import java.util.List;
import java.util.NoSuchElementException;


public class ItemPedidoMenu {

    private final InputReader input;

    private final ItemPedidoController controller;

    public ItemPedidoMenu(ItemPedidoController controller) {
        this.input = new InputReader();
        this.controller = controller;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== GERENCIAR ITENS DE PEDIDO ===");
            System.out.println("1. Listar itens cadastrados");
            System.out.println("2. Procurar um item");
            System.out.println("0. Voltar ao menu principal");

            int choice = input.readInt("Escolha uma opção");
            switch (choice) {
                case 1 -> {
                    List<ItemPedido> entities = controller.findAll();
                    if (entities.isEmpty()) {
                        System.out.println("\nNão há itens cadastrados.");
                    }
                    System.out.println();
                    entities.forEach(System.out::println);
                }
                case 2 -> {
                    int idProcurado = input.readInt("Digite o ID da lista de itens");
                    try {
                        ItemPedido itemPedido = controller.findById(idProcurado);
                        System.out.println("\n" + itemPedido);
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }

                case 0 -> {
                    System.out.println("Voltando ao menu principal...");
                    return;
                }
                default -> {
                    System.out.println("Opção inválida.");
                }
            }
        }
    }

}
