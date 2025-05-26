package app.ui.menu;

import java.util.List;
import java.util.NoSuchElementException;

import api.controller.PedidoController;
import api.model.Pedido;
import app.ui.InputReader;
import api.model.StatusPedido;

public class PedidoMenuBuscas {

    private final InputReader input;
    private final PedidoController controller;

    public PedidoMenuBuscas(PedidoController controller) {
        this.input = new InputReader();
        this.controller = controller;
    }

    public void start(){
        while (true) {
            System.out.println("\n=== BUSCAS DE PEDIDO ===");
            System.out.println("1. Buscar por ID");
            System.out.println("2. Buscar por Mesa");
            System.out.println("3. Buscar por Status");
            System.out.println("0. Menu anterior");


            int choice = input.readInt("Escolha uma opção");
            switch (choice) {
                case 1 -> {
                    int idProcurado = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idProcurado);
                        System.out.println("\n" + pedido);
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    int mesaBuscada = input.readInt("Digite o numero da mesa");
                    try {
                        Pedido pedido = controller.buscarPorMesa(mesaBuscada);
                        System.out.println("\n" + pedido);
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }                    
                }
                case 3 -> {
                    String statusBuscado = input.readLine("Digite o Status procurado (ABERTO, PRONTO, FINALIZADO, CANCELADO)").toUpperCase();
                    try {
                        StatusPedido status = StatusPedido.valueOf(statusBuscado);
                        List<Pedido> pedidos = controller.filtrarPorStatus(status);
                            if (pedidos.isEmpty()) {
                                System.out.println("\nNão há pedidos com o Status " + status + " nesse momento.");
                            } else {
                                pedidos.forEach(System.out::println);
                            }                        
                    } catch (IllegalArgumentException e) {
                        System.out.println("\nStatus inválido! Use: ABERTO, PRONTO, FINALIZADO ou CANCELADO.");
                    }
                }
                case 0 -> {
                    System.out.println("Voltando ao menu anterior...");
                    return;
                }
                default -> {
                    System.out.println("Opção inválida.");
                }
            }
        }

    }
    
}
