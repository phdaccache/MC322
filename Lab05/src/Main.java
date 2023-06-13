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
        System.out.println("Alem disso, sao instanciados alguns objetos para testar o programa antes de rodar o menu.");
        System.out.println("");

        System.out.print("Deseja rodar o programa pelo scanner automatico ou manual? (1 - Automatico, 2 - Manual): ");
        int op = scanner1.nextInt();
        scanner1.nextLine();

        switch (op) {
            case 1:
                System.out.print("Digite o caminho do arquivo de entrada (ou deixe em branco para usar o arquivo 'input/Teste_Input.txt'): ");
                String entrada = scanner1.nextLine();
                rodarComScannerAutomatico(entrada);
                break;
            case 2:
                rodarComScannerManual();
                break;
            default:
            System.out.println("Opcao invalida.");
                break;
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
        /******************************* TESTE AUTOMATICO *******************************/

        //////////////////// CADASTROS E LISTAGENS ////////////////////

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
        System.out.println("");

        // Criar frotas
        Frota frota1 = new Frota(1);
        Frota frota2 = new Frota(2);
        Frota frota3 = new Frota(3);
        Frota frota4 = new Frota(4);
        // Criar veiculos
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Chevrolet", "Camaro", 2023);
        Veiculo veiculo2 = new Veiculo("ABC-4321", "Chevrolet", "Onix", 2018);        
        Veiculo veiculo3 = new Veiculo("CBA-1234", "Chevrolet", "Equinox", 2019);
        Veiculo veiculo4 = new Veiculo("MSN-1234", "Hyundai", "Tucson", 2014);
        Veiculo veiculo5 = new Veiculo("MSN-4321", "Hyundai", "HB20", 2017);
        Veiculo veiculo6 = new Veiculo("PAD-1234", "Toyota", "Corolla", 2014);
        Veiculo veiculo7 = new Veiculo("PAD-4321", "Toyota", "Yaris", 2022);
        Veiculo veiculo8 = new Veiculo("TOP-1234", "Toyota", "Etios", 2018);  
        // Cadastrar veiculos nas frotas      
        frota1.cadastrarVeiculo(veiculo1);
        frota2.cadastrarVeiculo(veiculo4);
        frota3.cadastrarVeiculo(veiculo6);
        frota4.cadastrarVeiculo(veiculo8);
        System.out.println("");
        // Cadastrar frotas nos clientes
        cliente1.cadastrarFrota(frota1);
        cliente1.cadastrarFrota(frota2);
        cliente2.cadastrarFrota(frota3);
        cliente5.cadastrarFrota(frota4);
        System.out.println("");
        // Atualizar frotas
        ArrayList<Veiculo> veiculosF1 = new ArrayList<Veiculo>();
        veiculosF1.add(veiculo2);
        veiculosF1.add(veiculo3);
        cliente1.atualizarFrota(1, veiculosF1, new ArrayList<Veiculo>());
        System.out.println("");
        ArrayList<Veiculo> veiculosF2 = new ArrayList<Veiculo>();
        veiculosF2.add(veiculo5);
        cliente1.atualizarFrota(2, veiculosF2, new ArrayList<Veiculo>());
        System.out.println("");
        ArrayList<Veiculo> veiculosF3 = new ArrayList<Veiculo>();
        veiculosF3.add(veiculo7);
        cliente2.atualizarFrota(1, veiculosF3, new ArrayList<Veiculo>());
        System.out.println("");

        // Listagens
        Admin.listarSeguradoras();
        System.out.println("");

        seguradora1.visualizarDados();
        System.out.println("");
        seguradora1.listarClientes();
        System.out.println("");
        seguradora1.calcularReceita();
        System.out.println("");
        seguradora1.listarSeguros();
        System.out.println("");
        seguradora2.visualizarDados();
        System.out.println("");
        seguradora2.listarClientes();
        System.out.println("");
        seguradora2.calcularReceita();
        System.out.println("");

        cliente1.visualizarDados();
        System.out.println("");
        cliente1.listarFrotas();
        System.out.println("");
        cliente1.visualizarFrota(1);
        System.out.println("");
        cliente1.listarSeguros();
        System.out.println("");
        cliente1.listarCondutores();
        System.out.println("");
        cliente2.listarFrotas();
        System.out.println("");
        cliente2.visualizarFrota(1);
        System.out.println("");

        // Criar veiculos
        Veiculo veiculo9 = new Veiculo("DAC-1234", "BMW", "Serie 3", 2023);
        Veiculo veiculo10 = new Veiculo("DAC-4321", "BMW", "Serie 5", 2022);   
        Veiculo veiculo11 = new Veiculo("CAD-1234", "Ferrari", "Roma", 2021);
        Veiculo veiculo12 = new Veiculo("COR-1234", "Ferrari", "Portofino", 2020);   
        Veiculo veiculo13 = new Veiculo("COR-4321", "Tesla", "Model S", 2022);
        Veiculo veiculo14 = new Veiculo("TES-1234", "Tesla", "Model 3", 2021);
        Veiculo veiculo15 = new Veiculo("TES-4321", "Tesla", "Model X", 2020);
        // Cadastrar veiculos no cliente
        cliente3.cadastrarVeiculo(veiculo9);
        cliente3.cadastrarVeiculo(veiculo10);
        cliente3.cadastrarVeiculo(veiculo11);
        cliente4.cadastrarVeiculo(veiculo12);
        cliente4.cadastrarVeiculo(veiculo13);
        cliente6.cadastrarVeiculo(veiculo14);
        cliente6.cadastrarVeiculo(veiculo15);
        System.out.println("");

        // Listagens cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente4.visualizarDados();
        System.out.println("");
        cliente4.listarVeiculos();
        System.out.println("");
        cliente6.visualizarDados();
        System.out.println("");
        cliente6.listarVeiculos();
        System.out.println("");

        // Criando Condutores
        Condutor condutor1 = new Condutor("752.792.913-82", "Andrezinho", "(19) 98787-7878", "Rua do Condutor Andrezinho, 356", "andrezinho@gmail.com", "17/09/1998");
        Condutor condutor2 = new Condutor("522.342.883-80", "Cleide", "(11) 91111-1111", "Rua da Condutora Cleide", "cleide@gmail.com", "21/04/2001");
        Condutor condutor3 = new Condutor("453.455.012-03", "Tom Jobim", "(11) 91234-1234", "Rua da Bossa Nova, 420", "tomjobim@gmail.com", "25/01/1927");
        Condutor condutor4 = new Condutor("654.711.351-56", "Jonas Madureira", "(11) 94444-3333", "Rua do Condutor Jonas, 160", "jonas@gmail.com", "30/07/1976");
        Condutor condutor5 = new Condutor("953.344.933-01", "Joaozinho", "(11) 99999-9999", "Rua do Condutor Joaozinho, 123", "joazinho@gmail.com", "01/10/1999");
        Condutor condutor6 = new Condutor("484.258.684-24", "Mariazinha", "(11) 98888-8888", "Rua da Condutora Mariazinha, 456", "mariazinha@gmail.com", "02/11/2002");
        Condutor condutor7 = new Condutor("454.565.219-03", "John Piper", "(11) 91212-2121", "Rua do Condutor Piper, 901", "piper@gmail.com", "11/01/1946");
        
        // Gerar Seguros
        seguradora1.gerarSeguroPJ(cliente1, frota1, "12/06/2023", "12/06/2026", condutor1);
        seguradora1.gerarSeguroPJ(cliente1, frota2, "12/06/2023", "12/06/2028", condutor1);
        seguradora1.gerarSeguroPJ(cliente2, frota3, "13/06/2023", "13/06/2024", condutor2);
        seguradora1.gerarSeguroPF(cliente3, veiculo9, "13/06/2023", "13/06/2025", condutor3);
        seguradora1.gerarSeguroPF(cliente4, veiculo12, "13/06/2023", "13/06/2028", condutor4);
        seguradora2.gerarSeguroPF(cliente6, veiculo14, "13/06/2023", "13/06/2029", condutor5);
        System.out.println("");

        // Listagens Seguradora
        seguradora1.listarSeguros();
        seguradora1.listarSegurosPorCliente("06.947.283/0001-60");
        seguradora1.listarSegurosPorCliente("101.255.787-17");
        seguradora1.calcularReceita();
        System.out.println("");
        seguradora2.listarSeguros();
        seguradora2.listarSegurosPorCliente("04.712.500/0001-07");
        seguradora2.listarSegurosPorCliente("381.854.732-77");
        seguradora2.calcularReceita();
        System.out.println("");

        // Listagens Cliente PJ
        cliente1.visualizarDados();
        System.out.println("");
        cliente1.listarSeguros();
        System.out.println("");
        cliente1.visualizarSeguro(1);
        System.out.println("");
        cliente1.listarCondutores();
        System.out.println("");
        cliente1.listarFrotas();
        System.out.println("");

        // Atualizar Frota
        ArrayList<Veiculo> veiculosF2Add = new ArrayList<Veiculo>();
        Veiculo veiculo16 = new Veiculo("NSM-1234", "Hyundai", "ix35", 2020);
        veiculosF2Add.add(veiculo16);
        cliente1.atualizarFrota(2, veiculosF2Add, new ArrayList<Veiculo>());
        System.out.println("");
        // Cadastrar Condutor Cliente PJ
        cliente1.cadastrarCondutor(condutor6, 1);
        System.out.println("");

        // Listagens Cliente PJ
        cliente1.visualizarDados();
        System.out.println("");
        cliente1.listarSeguros();
        System.out.println("");
        cliente1.visualizarSeguro(1);
        System.out.println("");
        cliente1.listarCondutores();
        System.out.println("");
        cliente1.listarFrotas();
        System.out.println("");
        
        // Listagens Cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.visualizarSeguro(4);
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");

        // Adicionar Veiculos Cliente PF
        Veiculo veiculo17 = new Veiculo("CAD-4321", "Ferrari", "SF90", 2022);
        cliente3.cadastrarVeiculo(veiculo17);
        System.out.println("");
        // Cadastrar Condutor Cliente PF
        cliente3.cadastrarCondutor(condutor7, 4);
        System.out.println("");

        // Listagens Cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.visualizarSeguro(4);
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");


        // Criar 2 sinistro cliente PJ 1, 1 sinistro cliente PJ2, 1 sinistro cliente PJ3 (seguradora2)

        //////////////////// REMOCOES ////////////////////

        // Listagens Cliente PJ
        cliente1.visualizarDados();
        System.out.println("");
        cliente1.listarSeguros();
        System.out.println("");
        cliente1.visualizarSeguro(1);
        System.out.println("");
        cliente1.listarCondutores();
        System.out.println("");
        cliente1.listarFrotas();
        System.out.println("");

        // Excluir Condutor Cliente PJ
        cliente1.excluirCondutor("484.258.684-24", 1);
        cliente1.excluirCondutor("752.792.913-82", 1); // Nao vai excluir porque o seguro nao pode ficar sem condutor
        System.out.println("");

        // Excluir Veiculos e Frota Inteira Cliente PJ
        ArrayList<Veiculo> veiculosRemove = new ArrayList<Veiculo>();
        veiculosRemove.add(veiculo4);
        veiculosRemove.add(veiculo5);
        veiculosRemove.add(veiculo16);
        cliente1.atualizarFrota(2, new ArrayList<Veiculo>(), veiculosRemove);
        System.out.println("");

        // Listagens Cliente PJ
        cliente1.visualizarDados();
        System.out.println("");
        cliente1.listarSeguros();
        System.out.println("");
        cliente1.visualizarSeguro(1);
        System.out.println("");
        cliente1.listarCondutores();
        System.out.println("");
        cliente1.listarFrotas();
        System.out.println("");


        // Listagens Cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.visualizarSeguro(4);
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");

        // Excluir Condutor Cliente PF
        cliente3.excluirCondutor("453.455.012-03", 4);
        System.out.println("");

        // Listagens Cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.visualizarSeguro(4);
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");

        // Excluir Veiculos Cliente PF
        cliente3.excluirVeiculo(veiculo9); // Veiculo com seguro
        cliente3.excluirVeiculo(veiculo11);
        cliente3.excluirVeiculo(veiculo17);

        // Listagens Cliente PF
        cliente3.visualizarDados();
        System.out.println("");
        cliente3.listarSeguros();
        System.out.println("");
        cliente3.visualizarSeguro(4);
        System.out.println("");
        cliente3.listarCondutores();
        System.out.println("");
        cliente3.listarVeiculos();
        System.out.println("");


        // Listagens Seguradora
        seguradora1.visualizarDados();
        System.out.println("");
        seguradora1.listarClientes();
        System.out.println("");
        seguradora1.listarSeguros();
        seguradora1.calcularReceita();
        System.out.println("");

        // Excluir Cliente PJ
        seguradora1.excluirCliente("06.947.283/0001-60");
        System.out.println("");

        // Excluir Cliente PF
        seguradora1.excluirCliente("101.255.787-17");
        System.out.println("");

        // Cancelar Seguro PJ
        seguradora1.cancelarSeguro("13.347.016/0001-17", 3);
        System.out.println("");

        // Cancelar Seguro PF
        seguradora1.cancelarSeguro("522.444.883-22", 5);
        System.out.println("");

        // Listagens Seguradora
        seguradora1.visualizarDados();
        System.out.println("");
        seguradora1.listarClientes();
        System.out.println("");
        seguradora1.listarSeguros();
        System.out.println("");
        seguradora1.calcularReceita();
        System.out.println("");

        // Listar Seguradoras
        Admin.listarSeguradoras();
        System.out.println("");
        // Excluir Seguradoras
        Admin.excluirSeguradora(seguradora1);
        Admin.excluirSeguradora(seguradora2);
        System.out.println("");
        // Listar Seguradoras
        Admin.listarSeguradoras();
        System.out.println("");


        /******************************* MENU INTERATIVO *******************************/

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