package app.ui.menu;


import api.controller.PedidoController;
import api.model.ItemPedido;
import api.model.Pedido;
import api.model.StatusPedido;
import app.ui.InputReader;

import java.util.List;
import java.util.NoSuchElementException;


public class PedidoMenu {

    protected final InputReader input;

    private final PedidoController controller;
    private final PedidoMenuBuscas menuBuscas;
    private final PedidoMenuManip menuManip;

    public PedidoMenu(PedidoController controller, PedidoMenuBuscas menuBuscas, PedidoMenuManip menuManip) {
        this.input = new InputReader();
        this.controller = controller;
        this.menuBuscas = menuBuscas;
        this.menuManip = menuManip;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== GERENCIAR PEDIDOS ===");
            System.out.println("1. Listar pedidos cadastrados");
            System.out.println("2. Criar um novo pedido");
            System.out.println("3. Procurar um pedido");
            System.out.println("4. Atualizar mesa um pedido");
            System.out.println("5. Manipular itens de um pedido");
            System.out.println("6. Resumo geral de um pedido");
            System.out.println("7. Excluir um pedido");
            System.out.println("0. Voltar ao menu principal");

            int choice = input.readInt("Escolha uma opção");
            switch (choice) {
                case 1 -> {
                    List<Pedido> entities = controller.findAll();
                    if (entities.isEmpty()) {
                        System.out.println("\nNão há pedidos cadastrados.");
                    }
                    System.out.println();
                    entities.forEach(System.out::println);
                }
                case 2 -> {
                    int mesa = input.readInt("Numero da Mesa");
                    Pedido pedido = new Pedido(mesa);
                    //Tratamento
                    try {
                        controller.save(pedido);
                        System.out.println("\nPedido para mesa " + mesa + " aberto!");
                    } catch (IllegalArgumentException e) {
                         System.out.println("\nErro ao cadastrar pedido: " + e.getMessage());
                    }
                }
                case 3 -> {
                    menuBuscas.start();
                }
                case 4 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        if (pedido.getStatus() != StatusPedido.ABERTO) {
                            System.out.println("\nSó é possível alterar a mesa de pedidos ABERTOS.");
                            break;
                        }
                        int novaMesa = input.readInt("Digite o novo número da mesa");
                        pedido.setMesa(novaMesa);
                        controller.update(pedido);
                        System.out.println("\nMesa do pedido atualizada com sucesso!");
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                case 5 -> {
                    menuManip.start();
                }
                case 6 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        try {
                            controller.resumoGeral(pedido);
                            boolean mostrarItens = input.readBoolean("Deseja ver os itens do pedido? (s/n)");
                            if (mostrarItens) {
                                System.out.println("\nItens do pedido:");
                                for (ItemPedido item : pedido.getItens()) {
                                    System.out.printf("%d. Prato ID: %d | Quantidade %d%n",
                                    item.getId(), item.getIdPrato(), item.getQuantidade());
                                }
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                }
                case 7 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        controller.delete(idPedido);
                        System.out.println("\nPedido deletado com sucesso!");
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    } catch (IllegalArgumentException e){
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
