import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;
import pacote.Admin;
import pacote.ClientePF;
import pacote.ClientePJ;
import pacote.Seguradora;
import pacote.Veiculo;

public class Main {
    public static void main(String[] args) {
        /* TESTE AUTOMATICO */

        // Instanciacao dos objetos
        Seguradora seguradora1 = new Seguradora("Pedro Seguros", "(11) 1234-5678", "pedroseguros@gmail.com", "Rua das Seguradoras");
        Seguradora seguradora2 = new Seguradora("Seguros Magicos", "(11) 9876-5432", "porto@gmail.com", "Rua Magica");
        Admin.cadastrarSeguradora(seguradora1);
        Admin.cadastrarSeguradora(seguradora2);

        System.out.println("");

        ClientePJ clientePJ1 = new ClientePJ("Google", "Rua 1", "06.990.590/0001-23", "04/09/1998", 50);
        ClientePF clientePF = new ClientePF("Pedro", "Rua 2", "101.255.787-17", "Masculino", "10/12/2022", "Ensino Superior", "25/01/2003", "Média");
        ClientePJ clientePJ2 = new ClientePJ("Facebook", "Rua 3", "13.347.016/0001-17", "25/01/2003", 8);
        
        Veiculo veiculo1 = new Veiculo("BMW-1234", "Chevrolet", "Onix", 2022);
        Veiculo veiculo2 = new Veiculo("ABC-4321", "Hyundai", "HB20", 2018);
        Veiculo veiculo3 = new Veiculo("TOP-1984", "Fiat", "Cronos", 2020);
        
        // Veiculos adicionados em cada cliente
        clientePF.cadastrarVeiculo(veiculo1);
        clientePJ1.cadastrarVeiculo(veiculo2);
        clientePJ1.cadastrarVeiculo(veiculo3);

        System.out.println("");

        // Cadastro dos clientes na seguradora
        seguradora1.cadastrarCliente(clientePF);
        seguradora1.cadastrarCliente(clientePJ1);
        seguradora1.cadastrarCliente(clientePJ2);

        System.out.println("");

        // Sinistros gerados
        seguradora1.gerarSinistro("10/04/2023", "Google", "Rua 1", "ABC-4321");
        seguradora1.gerarSinistro("21/04/2023", "Google", "Rua 1", "TOP-1984");
        seguradora1.gerarSinistro("14/05/2023", "Pedro", "Rua 2", "BMW-1234");

        System.out.println("");

        // Listagens
        seguradora1.listarClientes();
        System.out.println("");
        seguradora1.listarSinistros();
        System.out.println("");
        System.out.println("Receita da seguradora:");
        seguradora1.calcularReceita();
        System.out.println("");

        // Atualizacao do valor do seguro
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate novaData = LocalDate.parse("25/01/1980", dtf); // Tranformando String em LocalDate
        clientePF.setDataNascimento(novaData);
        double valorSeguro = seguradora1.calcularPrecoSeguroCliente(clientePF);
        clientePF.setValorSeguro(valorSeguro);

        System.out.println("Cliente com novos dados:");
        System.out.println("---------------------------------------------");
        System.out.println(clientePF);
        System.out.println("---------------------------------------------");

        System.out.println("");

        // Nova receita da seguradora
        System.out.println("Nova receita da seguradora:");
        seguradora1.calcularReceita();


        /* MENU INTERATIVO */

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
}