import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        /* MENU INTERATIVO */

        // Scanner scanner = new Scanner(System.in);
        removerLinhasComentadas("inputs/Teste_Input.txt", "inputs/input.txt");
        Scanner scanner = new Scanner(new File("inputs/input.txt"));
        Menu menu = new Menu(scanner);
        MenuOperacoes option;

        // Executa menu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
        do {
            menu.showMenu();
            option = menu.readOptionMenu();
            menu.runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);

        scanner.close();
    }

    // Funcao que remove as linhas que comecam com "//" ou sao espacos em branco
    // de um arquivo de entrada e salva o resultado em um arquivo de saida
    public static void removerLinhasComentadas(String arquivoEntrada, String arquivoSaida) {
        try {
            // Abre o arquivo de entrada para leitura
            BufferedReader reader = new BufferedReader(new FileReader(arquivoEntrada));

            // Abre o arquivo de saida para armazenar o conteudo sem comentarios
            BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoSaida));

            // Leitura de cada linha do arquivo de entrada
            String line;
            while ((line = reader.readLine()) != null) {
                // Checa se a linha comeca com "//" ou e um espaco em branco
                if (!line.trim().startsWith("//") && !line.trim().isEmpty()) {
                    // Escreve a linha no arquivo de saida
                    writer.write(line);
                    writer.newLine();
                }
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}