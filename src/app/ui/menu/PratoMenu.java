package app.ui.menu;

import api.controller.PratoController;
import api.model.Prato;
import app.ui.InputReader;

import java.util.List;
import java.util.NoSuchElementException;

public class PratoMenu {

    private final InputReader input;

    private final PratoController controller;

    public PratoMenu(PratoController controller) {
        this.input = new InputReader();
        this.controller = controller;
    }

    public void start() {
        while (true) {
            System.out.println("\n=== GERENCIAR PRATOS ===");
            System.out.println("1. Listar pratos cadastrados");
            System.out.println("2. Criar um novo prato");
            System.out.println("3. Procurar um prato");
            System.out.println("4. Atualizar um prato");
            System.out.println("5. Deletar um prato");
            System.out.println("0. Voltar ao menu principal");

            int choice = input.readInt("Escolha uma opção");
            switch (choice) {
                case 1 -> {
                    List<Prato> entities = controller.findAll();
                    if (entities.isEmpty()) {
                        System.out.println("\nNão há pratos cadastrados.");
                    }
                    System.out.println();
                    entities.forEach(System.out::println);
                }
                case 2 -> {
                    String nomePrato = input.readLine("Nome do prato");
                    String descricaoPrato = input.readLine("Descrição do prato");
                    double precoPrato = input.readDouble("Preço do prato");
                    Prato prato = new Prato(nomePrato, descricaoPrato, precoPrato);
                    //Tratamento
                    try {
                        controller.save(prato);
                        System.out.println("\nPrato cadastrado com sucesso!");
                    } catch (IllegalArgumentException e) {
                         System.out.println("\nErro ao cadastrar prato: " + e.getMessage());
                    }
                }
                case 3 -> {
                    int idProcurado = input.readInt("Digite o ID do prato");
                    try {
                        Prato prato = controller.findById(idProcurado);
                        System.out.println("\n" + prato);
                    } catch (NoSuchElementException e) {
                        System.out.println("\nErro: " + e.getMessage());
                    }

                }
                case 4 -> {
                        int idAtualizar = input.readInt("Digite o ID do prato a ser atualizado");
                        String novoNome = input.readLine("Novo nome do prato");
                        String novaDescricao = input.readLine("Nova descrição do prato");
                        double novoPreco = input.readDouble("Novo preço do prato");
                        try {
                            controller.update(idAtualizar, novoNome, novaDescricao, novoPreco);
                            System.out.println("\nPrato atualizado com sucesso!");
                        } catch (NoSuchElementException | IllegalArgumentException e) {
                            System.out.println("\nErro: " + e.getMessage());
                        }
                }
                case 5 -> {
                    int idDeletar = input.readInt("Digite o ID do prato");
                    try {
                        controller.delete(idDeletar);
                        System.out.println("\nPrato deletado com sucesso!");
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
