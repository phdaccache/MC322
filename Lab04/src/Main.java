import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;
import pacote.ClientePJ;
import pacote.Seguradora;

public class Main {
    public static void main(String[] args) {
        // Teste Automatico
        Seguradora seguradora = new Seguradora("Pedro Seguros", "(11) 1234-5678", "pedroseguros@gmail.com", "Rua das Seguradoras");
        Cliente clientePJ = new ClientePJ();
        Cliente clientePF = new ClientePF();
        

        // Menu Interativo
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