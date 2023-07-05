

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import arquivos.Carregar;
import arquivos.Gravar;
import menu.*;
import sistema.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LABORATORIO 6 - SISTEMA DE SEGURADORA");
        System.out.println("");

        System.out.println("FUNCIONAMENTO DO PROGRAMA:");
        System.out.println("");

        System.out.println("* E possivel aceitar ou negar essas opcoes no inicio do programa:");
        System.out.println("");

        System.out.println("    1. Carregar arquivos salvos:");
        System.out.println("        * Inicia o programa com alguns objetos ja instanciados.");
        System.out.println("        * Esses objetos sao carregados de 'src/arquivos/arquivosCSV'.");
        System.out.println("");

        System.out.println("    2. Rodar o teste automatico:");
        System.out.println("        * Instancia objetos direto da main e testa suas funcionalidades.");
        System.out.println("        * Todos os objetos sao excluidos apos o teste.");
        System.out.println("        * Dica: caso nao seja possivel visualizar a saida inteira no console, siga esses passos:");
        System.out.println("            1. Acesse File -> Preferences -> Settings.");
        System.out.println("            2. Na barra de pesquisa, digite 'scrollback'.");
        System.out.println("            3. Na opcao 'Terminal > Integrated: Scrollback', altere o valor de 1000 para 3000 ou mais.");
        System.out.println("");

        System.out.println("* E possivel aceitar ou negar essa opcao ao fim do programa:");
        System.out.println("");

        System.out.println("    1. Salvar as alteracoes:");
        System.out.println("        * Salva todos os objetos em 'src/arquivos/arquivosCSV'.");
        System.out.println("");

        // Opcao de carregar os arquivos csv no programa
        System.out.print("Deseja carregar os arquivos salvos? (s/n): ");
        String op = scanner.nextLine();
        System.out.println("");

        if (op.equals("s") || op.equals("S")) {
            Carregar.carregarDados();
        }

        // Execucao normal do programa
        runMain(scanner);

        // Opcao de gravar os arquivos csv no programa
        System.out.print("Deseja salvar as alteracoes? (s/n): ");
        String op2 = scanner.nextLine();
        System.out.println("");

        if (op2.equals("s") || op.equals("S")) {
            try {
                Gravar.gravarDados();
            } catch (IOException e) {
                System.out.println("Erro ao gravar os arquivos: " + e.getMessage());
            }
        }

        // Encerrando o programa
        System.out.println("Programa encerrado.");
        scanner.close();
    }

    public static void runMain(Scanner scanner) {
        // Opcao de rodar o teste automatico
        System.out.print("\nDeseja rodar o teste automatico? (s/n): ");
        String op = scanner.nextLine();
        System.out.println("");

        if (op.equals("s") || op.equals("S")) {
            runTesteAutomatico();
        }

        /******************************* MENU INTERATIVO *******************************/
      
        Menu menu = new Menu(scanner);
        MenuOperacoes option;

        // Executa menu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
        do {
            menu.showMenu();
            option = menu.readOptionMenu();
            menu.runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);
    }

    // Executa o teste automatico
    public static void runTesteAutomatico() {
        //////////////////// CADASTROS E LISTAGENS ////////////////////
        imprimirTituloFormatado("CADASTROS E LISTAGENS");
        System.out.println("");

        // Criar e cadastrar seguradoras
        imprimirTituloFormatado("Criar e Cadastrar Seguradoras");
        Seguradora seguradora1 = new Seguradora("61.198.164/0001-60", "Pedro Seguros", "(11) 91234-5678", "Rua dos Pedros", "pedroseguros@gmail.com");
        Seguradora seguradora2 = new Seguradora("51.990.695/0001-37", "Seguros Magicos", "(11) 98765-4321", "Rua da Magia", "segurosmagicos@gmail.com");
        Admin.cadastrarSeguradora(seguradora1);
        Admin.cadastrarSeguradora(seguradora2);
        System.out.println("");

        // Criar e cadastrar clientes
        imprimirTituloFormatado("Criar e Cadastrar Clientes");
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
        Frota frota3 = new Frota(1);
        Frota frota4 = new Frota(1);
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
        imprimirTituloFormatado("Cadastrar Veiculos nas Frotas");      
        frota1.cadastrarVeiculo(veiculo1);
        frota2.cadastrarVeiculo(veiculo4);
        frota3.cadastrarVeiculo(veiculo6);
        frota4.cadastrarVeiculo(veiculo8);
        System.out.println("");
        // Cadastrar frotas nos clientes
        imprimirTituloFormatado("Cadastrar Frotas nos Clientes PJ");
        cliente1.cadastrarFrota(frota1);
        cliente1.cadastrarFrota(frota2);
        cliente2.cadastrarFrota(frota3);
        cliente5.cadastrarFrota(frota4);
        System.out.println("");
        // Atualizar frotas
        imprimirTituloFormatado("Atualizar Frotas");
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

        // Listagens Admin
        imprimirTituloFormatado("Listagens do Admin");
        Admin.listarSeguradoras();
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens das Seguradoras");
        imprimirTituloFormatado("Visualizar Dados Seguradora 1 - Pedro Seguros");
        seguradora1.visualizarDados();
        imprimirTituloFormatado("Listar Clientes Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarClientes();
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.calcularReceita();
        imprimirTituloFormatado("Listar Seguros Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarSeguros();
        imprimirTituloFormatado("Visualizar Dados Seguradora 2 - Seguros Magicos", "\n");
        seguradora2.visualizarDados();
        imprimirTituloFormatado("Listar Clientes Seguradora 2 - Seguros Magicos", "\n");
        seguradora2.listarClientes();
        imprimirTituloFormatado("Calcular Receita Seguradora 2 - Seguros Magicos", "\n");
        seguradora2.calcularReceita();
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens dos Clientes PJ");
        imprimirTituloFormatado("Visualizar Dados Cliente PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Frotas PJ1 - Google", "\n");
        cliente1.listarFrotas();
        imprimirTituloFormatado("Visualizar Frota 001 PJ1 - Google", "\n");
        cliente1.visualizarFrota(1);
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Listar Condutores PJ1 - Google", "\n");
        cliente1.listarCondutores();
        imprimirTituloFormatado("Listar Frotas PJ2 - Facebook", "\n");
        cliente2.listarFrotas();
        imprimirTituloFormatado("Visualizar Frota 001 PJ2 - Facebook", "\n");
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
        // Cadastrar veiculos nos clientes
        imprimirTituloFormatado("Cadastrar Veiculos nos Clientes PF");
        cliente3.cadastrarVeiculo(veiculo9);
        cliente3.cadastrarVeiculo(veiculo10);
        cliente3.cadastrarVeiculo(veiculo11);
        cliente4.cadastrarVeiculo(veiculo12);
        cliente4.cadastrarVeiculo(veiculo13);
        cliente6.cadastrarVeiculo(veiculo14);
        cliente6.cadastrarVeiculo(veiculo15);
        System.out.println("");

        // Listagens cliente PF
        imprimirTituloFormatado("Listagens dos Clientes PF");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Visualizar Dados PF2 - Marcelo", "\n");
        cliente4.visualizarDados();
        imprimirTituloFormatado("Listar Veiculos PF2 - Marcelo", "\n");
        cliente4.listarVeiculos();
        imprimirTituloFormatado("Visualizar Dados PF3 - Joao", "\n");
        cliente6.visualizarDados();
        imprimirTituloFormatado("Listar Veiculos PF3 - Joao", "\n");
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
        imprimirTituloFormatado("Gerar Seguros");
        seguradora1.gerarSeguroPJ(cliente1, frota1, "12/06/2023", "12/06/2026", condutor1);
        seguradora1.gerarSeguroPJ(cliente1, frota2, "12/06/2023", "12/06/2028", condutor1);
        seguradora1.gerarSeguroPJ(cliente2, frota3, "13/06/2023", "13/06/2024", condutor2);
        seguradora1.gerarSeguroPF(cliente3, veiculo9, "13/06/2023", "13/06/2025", condutor3);
        seguradora1.gerarSeguroPF(cliente4, veiculo12, "13/06/2023", "13/06/2028", condutor4);
        seguradora2.gerarSeguroPF(cliente6, veiculo14, "13/06/2023", "13/06/2029", condutor5);
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens das Seguradoras");
        imprimirTituloFormatado("Listar Seguros Seguradora 1 - Pedro Seguros");
        seguradora1.listarSeguros();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google");
        seguradora1.listarSegurosPorCliente("06.947.283/0001-60");
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro");
        seguradora1.listarSegurosPorCliente("101.255.787-17");
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        imprimirTituloFormatado("Listar Seguros Seguradora 2 - Seguros Magicos", "\n");
        seguradora2.listarSeguros();
        imprimirTituloFormatado("Listar Seguros PJ3 - Microsoft");
        seguradora2.listarSegurosPorCliente("04.712.500/0001-07");
        imprimirTituloFormatado("Listar Seguros PF3 - Joao");
        seguradora2.listarSegurosPorCliente("381.854.732-77");
        imprimirTituloFormatado("Calcular Receita Seguradora 2 - Seguros Magicos");
        seguradora2.calcularReceita();
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Listar Condutores PJ1 - Google", "\n");
        cliente1.listarCondutores();
        imprimirTituloFormatado("Listar Frotas PJ1 - Google", "\n");
        cliente1.listarFrotas();
        System.out.println("");

        // Atualizar Frota
        imprimirTituloFormatado("Atualizar Frota Cliente PJ1 - Google");
        ArrayList<Veiculo> veiculosF2Add = new ArrayList<Veiculo>();
        Veiculo veiculo16 = new Veiculo("NSM-1234", "Hyundai", "ix35", 2020);
        veiculosF2Add.add(veiculo16);
        cliente1.atualizarFrota(2, veiculosF2Add, new ArrayList<Veiculo>());
        System.out.println("");
        // Cadastrar Condutor Cliente PJ
        imprimirTituloFormatado("Cadastrar Condutor Cliente PJ1 - Google");
        cliente1.cadastrarCondutor(condutor6, 1);
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Listar Condutores PJ1 - Google", "\n");
        cliente1.listarCondutores();
        imprimirTituloFormatado("Listar Frotas PJ1 - Google", "\n");
        cliente1.listarFrotas();
        System.out.println("");
        
        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        System.out.println("");

        // Adicionar Veiculo Cliente PF
        imprimirTituloFormatado("Adicionar Veiculo Cliente PF1 - Pedro");
        Veiculo veiculo17 = new Veiculo("CAD-4321", "Ferrari", "SF90", 2022);
        cliente3.cadastrarVeiculo(veiculo17);
        System.out.println("");
        // Cadastrar Condutor Cliente PF
        imprimirTituloFormatado("Cadastrar Condutor Cliente PF1 - Pedro");
        cliente3.cadastrarCondutor(condutor7, 4);
        System.out.println("");

        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        System.out.println("");


        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Listar Sinistros Seguradora 1 - Pedro Seguros");
        seguradora1.listarSinistros();
        imprimirTituloFormatado("Listar Sinistros PJ1 - Google");
        seguradora1.listarSinistrosPorCliente("06.947.283/0001-60");
        imprimirTituloFormatado("Listar Sinistros PF1 - Pedro");
        seguradora1.listarSinistrosPorCliente("101.255.787-17");
        imprimirTituloFormatado("Listar Sinistros PJ2 - Facebook");
        seguradora1.listarSinistrosPorCliente("13.347.016/0001-17");
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Visualizar Seguro 002 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(2);
        System.out.println("");

        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        System.out.println("");

        // Gerar Sinistros
        imprimirTituloFormatado("Gerar Sinistros");
        seguradora1.gerarSinistro("16/06/2023", "Rua Perigosa, 170", "484.258.684-24", 1);
        seguradora1.gerarSinistro("20/06/2023", "Rua Estranha, 34", "752.792.913-82", 2);
        seguradora1.gerarSinistro("13/08/2023", "Rua Periculosa, 51", "522.342.883-80", 3);
        seguradora1.gerarSinistro("27/11/2023", "Rua Suspeita, 7530", "522.342.883-80", 3);
        seguradora1.gerarSinistro("31/09/2023", "Rua Duvidosa, 99", "453.455.012-03", 4);
        seguradora2.gerarSinistro("08/10/2023", "Rua Interessante, 101", "953.344.933-01", 6);
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Listar Sinistros Seguradora 1 - Pedro Seguros");
        seguradora1.listarSinistros();
        imprimirTituloFormatado("Listar Sinistros PJ1 - Google");
        seguradora1.listarSinistrosPorCliente("06.947.283/0001-60");
        imprimirTituloFormatado("Listar Sinistros PF1 - Pedro");
        seguradora1.listarSinistrosPorCliente("101.255.787-17");
        imprimirTituloFormatado("Listar Sinistros PJ2 - Facebook");
        seguradora1.listarSinistrosPorCliente("13.347.016/0001-17");
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Visualizar Seguro 002 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(2);
        System.out.println("");

        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        System.out.println("");


        //////////////////// REMOCOES ////////////////////
        imprimirTituloFormatado("REMOCOES");
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Listar Sinistros Seguradora 1 - Pedro Seguros");
        seguradora1.listarSinistros();
        imprimirTituloFormatado("Listar Sinistros PJ1 - Google");
        seguradora1.listarSinistrosPorCliente("06.947.283/0001-60");
        imprimirTituloFormatado("Listar Sinistros PJ2 - Facebook");
        seguradora1.listarSinistrosPorCliente("13.347.016/0001-17");
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        System.out.println("");

        // Excluir sinistros
        imprimirTituloFormatado("Excluir Sinistros");
        seguradora1.excluirSinistro(1);
        seguradora1.excluirSinistro(2);
        seguradora1.excluirSinistro(3);
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Listar Sinistros Seguradora 1 - Pedro Seguros");
        seguradora1.listarSinistros();
        imprimirTituloFormatado("Listar Sinistros PJ1 - Google");
        seguradora1.listarSinistrosPorCliente("06.947.283/0001-60");
        imprimirTituloFormatado("Listar Sinistros PJ2 - Facebook");
        seguradora1.listarSinistrosPorCliente("13.347.016/0001-17");
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        System.out.println("");


        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Listar Condutores PJ1 - Google", "\n");
        cliente1.listarCondutores();
        imprimirTituloFormatado("Listar Frotas PJ1 - Google", "\n");
        cliente1.listarFrotas();
        System.out.println("");

        // Excluir Condutor Cliente PJ
        imprimirTituloFormatado("Excluir Condutores Cliente PJ1 - Google");
        cliente1.excluirCondutor("484.258.684-24", 1);
        cliente1.excluirCondutor("752.792.913-82", 1); // Nao vai excluir porque o seguro nao pode ficar sem condutor
        System.out.println("");

        // Excluir Veiculos e Frota Inteira Cliente PJ
        imprimirTituloFormatado("Excluir Veiculos e Frota Inteira Cliente PJ1 - Google");
        ArrayList<Veiculo> veiculosRemove = new ArrayList<Veiculo>();
        veiculosRemove.add(veiculo4);
        veiculosRemove.add(veiculo5);
        veiculosRemove.add(veiculo16);
        cliente1.atualizarFrota(2, new ArrayList<Veiculo>(), veiculosRemove);
        System.out.println("");

        // Listagens Cliente PJ
        imprimirTituloFormatado("Listagens Cliente PJ1 - Google");
        imprimirTituloFormatado("Visualizar Dados PJ1 - Google");
        cliente1.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PJ1 - Google", "\n");
        cliente1.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 001 PJ1 - Google", "\n");
        cliente1.visualizarSeguro(1);
        imprimirTituloFormatado("Listar Condutores PJ1 - Google", "\n");
        cliente1.listarCondutores();
        imprimirTituloFormatado("Listar Frotas PJ1 - Google", "\n");
        cliente1.listarFrotas();
        System.out.println("");


        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        System.out.println("");

        // Excluir Condutor Cliente PF
        imprimirTituloFormatado("Excluir Condutor Cliente PF1 - Pedro");
        cliente3.excluirCondutor("453.455.012-03", 4);
        System.out.println("");

        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        System.out.println("");

        // Excluir Veiculos Cliente PF
        imprimirTituloFormatado("Excluir Veiculos Cliente PF1 - Pedro");
        cliente3.excluirVeiculo(veiculo9); // Veiculo com seguro
        cliente3.excluirVeiculo(veiculo11);
        cliente3.excluirVeiculo(veiculo17);
        System.out.println("");

        // Listagens Cliente PF
        imprimirTituloFormatado("Listagens Cliente PF1 - Pedro");
        imprimirTituloFormatado("Visualizar Dados PF1 - Pedro");
        cliente3.visualizarDados();
        imprimirTituloFormatado("Listar Seguros PF1 - Pedro", "\n");
        cliente3.listarSeguros();
        imprimirTituloFormatado("Visualizar Seguro 004 PF1 - Pedro", "\n");
        cliente3.visualizarSeguro(4);
        imprimirTituloFormatado("Listar Condutores PF1 - Pedro", "\n");
        cliente3.listarCondutores();
        imprimirTituloFormatado("Listar Veiculos PF1 - Pedro", "\n");
        cliente3.listarVeiculos();
        System.out.println("");


        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Visualizar Dados Seguradora 1 - Pedro Seguros");
        seguradora1.visualizarDados();
        imprimirTituloFormatado("Listar Clientes Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarClientes();
        imprimirTituloFormatado("Listar Seguros Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarSeguros();
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros");
        seguradora1.calcularReceita();
        System.out.println("");

        // Excluir Cliente PJ
        imprimirTituloFormatado("Excluir Cliente PJ1 - Google");
        seguradora1.excluirCliente("06.947.283/0001-60");
        System.out.println("");

        // Excluir Cliente PF
        imprimirTituloFormatado("Excluir Cliente PF1 - Pedro");
        seguradora1.excluirCliente("101.255.787-17");
        System.out.println("");

        // Cancelar Seguro PJ
        imprimirTituloFormatado("Cancelar Seguro 003 PJ2 - Facebook");
        seguradora1.cancelarSeguro("13.347.016/0001-17", 3);
        System.out.println("");

        // Cancelar Seguro PF
        imprimirTituloFormatado("Cancelar Seguro 005 PF2 - Marcelo");
        seguradora1.cancelarSeguro("522.444.883-22", 5);
        System.out.println("");

        // Listagens Seguradora
        imprimirTituloFormatado("Listagens Seguradora 1 - Pedro Seguros");
        imprimirTituloFormatado("Visualizar Dados Seguradora 1 - Pedro Seguros");
        seguradora1.visualizarDados();
        imprimirTituloFormatado("Listar Clientes Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarClientes();
        imprimirTituloFormatado("Listar Seguros Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.listarSeguros();
        imprimirTituloFormatado("Calcular Receita Seguradora 1 - Pedro Seguros", "\n");
        seguradora1.calcularReceita();
        System.out.println("");

        // Listagens Admin
        imprimirTituloFormatado("Listagens do Admin");
        Admin.listarSeguradoras();
        System.out.println("");

        // Excluir Seguradoras
        imprimirTituloFormatado("Excluir Seguradoras");
        Admin.excluirSeguradora(seguradora1);
        Admin.excluirSeguradora(seguradora2);
        System.out.println("");

        // Listagens Admin
        imprimirTituloFormatado("Listagens do Admin");
        Admin.listarSeguradoras();
        System.out.println("");
    }

    // Retorna o titulo no formato '################################ Titulo ###############################'
    public static void imprimirTituloFormatado(String titulo, String ... linhaExtra) {
        int width = 69; // Tamanho total do titulo
        int padding = width - titulo.length();
        if (linhaExtra.length == 0) {
            linhaExtra = new String[] {""};
        }

        if (padding <= 0) { // padding <= 0 indica que o titulo e maior ou igual ao tamanho total
            System.out.printf("%s%s\n\n", linhaExtra[0], titulo);; // Se esse for o caso, nao tem espaco para colocar o #. Logo, retorna o titulo
        } else {
            int right = padding / 2;
            int left = padding - right; // O esquerdo e sempre igual ao direito ou igual ao direito + 1
            String leftPadding = "#".repeat(left);
            String rightPadding = "#".repeat(right);
            System.out.printf("%s%s %s %s\n\n", linhaExtra[0], leftPadding, titulo, rightPadding);
        }
    }
}