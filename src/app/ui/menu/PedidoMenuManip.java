package app.ui.menu;

import java.util.NoSuchElementException;

import api.controller.PedidoController;
import api.model.ItemPedido;
import api.model.Pedido;
import api.model.StatusPedido;
import app.ui.InputReader;

public class PedidoMenuManip {

    private final InputReader input;
    private final PedidoController controller;

    public PedidoMenuManip(PedidoController controller) {
        this.input = new InputReader();
        this.controller = controller;
    }

    public void start(){
        while (true) {
            System.out.println("\n=== MANIPULACAO DE PEDIDO ===");
            System.out.println("1. Adicionar itens a um pedido");
            System.out.println("2. Atualizar itens de um pedido");
            System.out.println("3. Remover itens de um pedido");
            System.out.println("4. Alterar Status de um pedido");
            System.out.println("0. Menu anterior");

            int choice = input.readInt("Escolha uma opção");
            switch (choice) {
                case 1 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        int idPrato = input.readInt("Digite o ID do prato");
                        int quantidade = input.readInt("Quantidade de itens (pratos do mesmo tipo)");
                        ItemPedido item = new ItemPedido(idPedido, idPrato, quantidade);
                        try {
                            controller.adicionarItemAoPedido(pedido, item);
                            System.out.println("\nItem adicionado ao pedido com sucesso!");
                        }//Tratamento para o adicionarItemAoPedido
                        catch (IllegalStateException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                    //Tratamento para o findById
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                case 2 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        if (pedido.getItens().isEmpty()) {
                            System.out.println("\nO pedido não possui itens para atualizar.");
                            break;
                        }
                        System.out.println("Itens do pedido:");
                        for (ItemPedido item : pedido.getItens()) {
                            System.out.println(item);
                        }
                        int idItem = input.readInt("Digite o ID do item a ser atualizado");
                        ItemPedido itemAtualizar = null;
                        for (ItemPedido item : pedido.getItens()) {
                            if (item.getId() == idItem) {
                                itemAtualizar = item;
                                break;
                            }
                        }
                        if (itemAtualizar == null) {
                            System.out.println("\nItem não encontrado no pedido.");
                        } else {
                            int novaQuantidade = input.readInt("Digite a nova quantidade para o item");
                            if (novaQuantidade < 1) {
                                System.out.println("\nA quantidade deve ser no mínimo 1.");
                            } else {
                                itemAtualizar.setQuantidade(novaQuantidade);
                                System.out.println("\nQuantidade do item atualizada com sucesso!");
                            }
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                }
                case 3 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        if (pedido.getItens().isEmpty()) {
                            System.out.println("\nO pedido não possui itens para remover.");
                            break;
                        }
                        System.out.println("Itens do pedido:");
                        for (ItemPedido item : pedido.getItens()) {
                            System.out.println(item);
                        }
                        int idItem = input.readInt("Digite o ID do item a ser removido");
                        ItemPedido itemRemover = null;
                        for (ItemPedido item : pedido.getItens()) {
                            if (item.getId() == idItem) {
                                itemRemover = item;
                                break;
                            }
                        }
                        if (itemRemover == null) {
                            System.out.println("\nItem não encontrado no pedido.");
                        } else {
                            try {
                                controller.removerItemDoPedido(pedido, itemRemover);
                                System.out.println("\nItem removido do pedido com sucesso!");
                            }//Tratamento para o removerItemDoPedido
                            catch (IllegalStateException e) {
                                System.out.println("\nErro: " + e.getMessage());
                            }
                        }
                    //Tratamento para o findById
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }
                }
                case 4 -> {
                    int idPedido = input.readInt("Digite o ID do pedido");
                    try {
                        Pedido pedido = controller.findById(idPedido);
                        System.out.println("Status atual: " + pedido.getStatus());
                        String novoStatusStr = input.readLine("Digite o novo status (ABERTO, PRONTO, FINALIZADO, CANCELADO)").toUpperCase();
                        try {
                            StatusPedido novoStatus = StatusPedido.valueOf(novoStatusStr);
                            controller.atualizarStatus(pedido, novoStatus);
                            System.out.println("\nStatus do pedido atualizado com sucesso!");
                            } catch (IllegalArgumentException e) {
                                System.out.println("\nStatus inválido! Use: ABERTO, PRONTO, FINALIZADO ou CANCELADO.");
                            } catch (IllegalStateException e) {
                                System.out.println("\nErro: " + e.getMessage());
                            } catch (Exception e) {
                                System.out.println("\nErro inesperado ao atualizar status: " + e.getMessage());
                            }
                    //Tratamento para o findById
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
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
