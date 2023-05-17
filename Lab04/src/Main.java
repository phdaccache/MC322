import java.util.Scanner;

import menu.Menu;
import menu.MenuOperacoes;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new Menu(scanner);
        MenuOperacoes option;

        do {
            menu.showMenu();
            option = menu.readOptionMenu();
            menu.runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);

        scanner.close();
    }
}