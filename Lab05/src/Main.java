import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;

public class Main {
    public static void main(String[] args) {
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