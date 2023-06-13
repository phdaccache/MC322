import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner1 = new Scanner(System.in);

        System.out.println("Ao rodar o programa pelo scanner automatico, forneca um arquivo de entrada como input.");
        System.out.println("O programa ignora comentarios do tipo '//' e linhas em branco.");
        System.out.println("Caso nao seja passado nenhum arquivo, o programa ira rodar com o arquivo 'input/Teste_Input.txt'.");
        System.out.println("");

        System.out.println("Ao rodar o programa pelo scanner manual, o usuario podera digitar os dados de entrada e a saida sera impressa no console.");
        System.out.println("");

        System.out.print("Deseja rodar o programa pelo scanner automatico ou manual? (1 - Automatico, 2 - Manual): ");
        int op = scanner1.nextInt();
        scanner1.nextLine();

        if (op == 1) {
            System.out.print("Digite o caminho do arquivo de entrada (ou deixe em branco para usar o arquivo 'input/Teste_Input.txt'): ");
            String entrada = scanner1.nextLine();
            rodarComScannerAutomatico(entrada);
        } else if (op == 2) {
            rodarComScannerManual();
        } else {
            System.out.println("Opcao invalida.");
        }
        scanner1.close();
    }

    public static void rodarComScannerAutomatico(String ... entrada) throws FileNotFoundException {
        if (entrada[0].equals("")) {
            entrada = new String[] {"input/Teste_Input.txt"};
        }
        removerLinhasComentadas(entrada[0], "input/input.txt");
        Scanner scanner = new Scanner(new File("input/input.txt"));

        PrintStream stdout = System.out;
        PrintStream fileOut = new PrintStream(new FileOutputStream("output/output.txt"));
        System.setOut(fileOut);
        
        Menu menu = new Menu(scanner);
        MenuOperacoes option;

        // Executa menu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
        do {
            menu.showMenu();
            option = menu.readOptionMenu();
            menu.runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);

        scanner.close();
        fileOut.close();
        System.setOut(stdout);

        System.out.println("O programa foi executado com sucesso! A saida foi salva em 'output/output.txt'.");
    }

    public static void rodarComScannerManual() {
        /******************** TESTE AUTOMATICO ********************/



        /******************** MENU INTERATIVO ********************/

        Scanner scanner = new Scanner(System.in);        
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