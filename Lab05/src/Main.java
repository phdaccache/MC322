import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import menu.*;
import pacote.*;

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

        // Criar e cadastrar seguradoras
        Seguradora seguradora1 = new Seguradora("61.198.164/0001-60", "Pedro Seguros", "(11) 91234-5678", "Rua dos Pedros", "pedroseguros@gmail.com");
        Seguradora seguradora2 = new Seguradora("51.990.695/0001-37", "Seguros Magicos", "(11) 98765-4321", "Rua da Magia", "segurosmagicos@gmail.com");
        Admin.cadastrarSeguradora(seguradora1);
        Admin.cadastrarSeguradora(seguradora2);
        System.out.println("");

        // Criar e cadastrar clientes
        ClientePJ cliente1 = new ClientePJ("Google", "0800 724 8149", "Mountain View, California, EUA", "google@gmail.com", "06.947.283/0001-60", "04/09/1998", 50);
        ClientePJ cliente2 = new ClientePJ("Facebook", "+1 650-543-4800", "R Leopoldo Couto De Magalhaes Junior, 700", "facebook@gmail.com", "13.347.016/0001-17", "01/02/2004", 15);
        ClientePF cliente3 = new ClientePF("Pedro", "(11) 99999-9999", "Rua Pitagoras, Barao Geraldo", "pedro@gmail.com", "101.255.787-17", "Masculino", "Ensino Superior", "25/01/2003");        
        ClientePF cliente4 = new ClientePF("Marcelo", "(11) 91234-1234", "Rua do Marcelo, 1454", "marcelo@gmail.com", "522.444.883-22", "Masculino", "Ensino Superior", "25/03/2001");
        ClientePJ cliente5 = new ClientePJ("Microsoft", "0800 888 4081", "Redmond, Washington, EUA", "microsoft@gmail.com", "04.712.500/0001-07", "04/04/1975", 100);
        ClientePF cliente6 = new ClientePF("Joao", "(11) 91234-5678", "Rua do Joao, 123", "joao@gmail.com", "381.854.732-77", "Masculino", "Ensino Superior", "01/01/2000");
        seguradora1.cadastrarCliente(cliente1);
        seguradora1.cadastrarCliente(cliente2);
        seguradora1.cadastrarCliente(cliente3);
        seguradora1.cadastrarCliente(cliente4);
        seguradora2.cadastrarCliente(cliente5);
        seguradora2.cadastrarCliente(cliente6);

        // Criar frotas e veiculos, cadastrar veiculos nas frotas e cadastrar frotas nos clientes
        Frota frota1 = new Frota(1);
        Frota frota2 = new Frota(2);
        Frota frota3 = new Frota(3);
        Frota frota4 = new Frota(4);
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Chevrolet", "Camaro", 2023);
        Veiculo veiculo2 = new Veiculo("ABC-4321", "Chevrolet", "Onix", 2018);        
        cliente1.cadastrarFrota(frota1);
        Veiculo veiculo3 = new Veiculo("CBA-1234", "Chevrolet", "Equinox", 2019);
        Veiculo veiculo4 = new Veiculo("MSN-1234", "Hyundai", "Tucson", 2014);
        Veiculo veiculo5 = new Veiculo("MSN-4321", "Hyundai", "HB20", 2017);
        Veiculo veiculo6 = new Veiculo("PAD-1234", "Toyota", "Corolla", 2014);
        Veiculo veiculo7 = new Veiculo("PAD-4321", "Toyota", "Yaris", 2022);
        Veiculo veiculo8 = new Veiculo("TOP-1234", "Toyota", "Etios", 2018);        
        frota1.cadastrarVeiculo(veiculo1);
        frota2.cadastrarVeiculo(veiculo4);
        frota3.cadastrarVeiculo(veiculo6);
        frota4.cadastrarVeiculo(veiculo8);
        cliente1.cadastrarFrota(frota1);
        cliente1.cadastrarFrota(frota2);
        cliente2.cadastrarFrota(frota3);
        cliente5.cadastrarFrota(frota4);
        ArrayList<Veiculo> veiculosF1 = new ArrayList<Veiculo>();
        veiculosF1.add(veiculo2);
        veiculosF1.add(veiculo3);
        cliente1.atualizarFrota(1, veiculosF1, null);
        ArrayList<Veiculo> veiculosF2 = new ArrayList<Veiculo>();
        veiculosF2.add(veiculo5);
        cliente1.atualizarFrota(12, veiculosF2, null);
        ArrayList<Veiculo> veiculosF3 = new ArrayList<Veiculo>();
        veiculosF3.add(veiculo7);
        cliente2.atualizarFrota(1, veiculosF3, null);

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