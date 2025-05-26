package app.ui;

import java.util.Scanner;

public class InputReader {


    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                String line = readLine(prompt);
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }
    // Criado um input reader para ler tipo double
    public double readDouble(String prompt) {
        while (true) {
            try {
                String line = readLine(prompt);
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal.");
            }
        }
    }

    public boolean readBoolean(String prompt) {
        while (true) {
            String resposta = readLine(prompt + " (sim/nao)").toLowerCase();
            if (resposta.equals("sim") || resposta.equals("s")) {
                return true;
            } else if (resposta.equals("nao") || resposta.equals("n") || resposta.equals("não")) {
                return false;
            } else {
                System.out.println("Entrada inválida. Responda com 'sim' ou 'nao'.");
            }
        }
    }

}
