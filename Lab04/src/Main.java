import java.util.Scanner;

import menu.MenuOperacoes;
import menu.SubmenuOperacoes;

public class Main {
    public static void main(String[] args) {
        MenuOperacoes option;
        do {
            showMenu();
            option = readOptionMenu();
            runMenuOption(option);
        } while (option != MenuOperacoes.SAIR);
    }

    /* Imprime o Menu no terminal
     * A variável 'numOption' pega o índice da constante + 1.
     * Caso 'numOption' seja a última constante da lista (ou seja, 'SAIR'), recebe 0.
     */
    private static void showMenu() {
        MenuOperacoes menu[] = MenuOperacoes.values();

        System.out.println("\n#################### Menu ###################");
        System.out.println("|-------------------------------------------|");
        for (MenuOperacoes option : menu) {
            int numOption = Math.min(option.ordinal() + 1, (menu.length - option.ordinal() - 1) * menu.length);
            System.out.printf("| Opcao %d - %-31s |\n", numOption, option.getName());
        }
        System.out.println("|-------------------------------------------|");
    }

    private static MenuOperacoes readOptionMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;
        MenuOperacoes menu[] = MenuOperacoes.values();

        while (true) {
            System.out.print("Digite uma opcao: ");
            option = scanner.nextInt();
            if (option < 0 || option > menu.length - 1) {
                System.out.println("Opcao Invalida.");
            } else if (option == 0) {
                return MenuOperacoes.values()[menu.length - 1];
            } else {
                return MenuOperacoes.values()[option - 1];
            }
        }
    }

    private static void runMenuOption(MenuOperacoes option) {
        switch (option) {
            case CADASTROS:
            case LISTAR:
            case EXCLUIR:
                runSubmenu(option);
                break;
            case GERAR_SINISTRO:
                System.out.println("Executar metodo gerar Sinistro");
                break;
            case TRANSFERIR_SEGURO:
                System.out.println("Executar metodo tranferir seguro");
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                System.out.println("Executar metodo calcular receita");
                break;
            case SAIR:
                System.out.println("Programa encerrado.");
        }
    }

    private static void runSubmenu(MenuOperacoes option) {
        SubmenuOperacoes subOption;
        do {
            showSubmenu(option);
            subOption = readOptionSubmenu(option);
            runSubmenuOption(subOption);
        } while (subOption != SubmenuOperacoes.VOLTAR);
    }

    private static void showSubmenu(MenuOperacoes option) {
        SubmenuOperacoes submenu[] = option.getSubOptions();
        String title = getFormattedTitle(option);

        System.out.println(title);
        System.out.println("|-------------------------------------------|");
        for (int i = 0; i < submenu.length; i++) {
            int numSuboption = Math.min(i + 1, (submenu.length - i - 1) * submenu.length);
            System.out.printf("| Opcao %d - %-31s |\n", numSuboption, submenu[i].getName());
        }
        System.out.println("|-------------------------------------------|");
    }

    private static SubmenuOperacoes readOptionSubmenu(MenuOperacoes option) {
        Scanner scanner = new Scanner(System.in);
        int subOption;
        SubmenuOperacoes submenu[] = option.getSubOptions();

        while (true) {
            System.out.print("Digite uma opcao: ");
            subOption = scanner.nextInt();
            if (subOption < 0 || subOption > submenu.length - 1) {
                System.out.println("Opcao Invalida.");
            } else if (subOption == 0) {
                return submenu[submenu.length - 1];
            } else {
                return submenu[subOption - 1];
            }
        }
    }

    private static void runSubmenuOption(SubmenuOperacoes subOption) {
        switch(subOption) {
            case CADASTRAR_CLIENTE:
                System.out.println("Chamar metodo cadastrar cliente");
                break;
            case CADASTRAR_VEICULO:
                System.out.println("Chamar metodo cadastrar veiculo");
                break;
            case CADASTRAR_SEGURADORA:
                System.out.println("Chamar metodo cadastrar seguradora");
                break;
            case LISTAR_CLIENTES:
                System.out.println("Chamar metodo listar clientes");
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                System.out.println("Chamar metodo listar sinistros seguradora");
                break;
            case LISTAR_SINISTROS_CLIENTE:
                System.out.println("Chamar metodo listar sinistros cliente");
                break;
            case LISTAR_VEICULOS_SEGURADORA:
                System.out.println("Chamar metodo listar veiculos seguradora");
                break;
            case LISTAR_VEICULOS_CLIENTE:
                System.out.println("Chamar metodo listar veiculos cliente");
                break;
            case EXCLUIR_CLIENTE:
                System.out.println("Chamar metodo excluir cliente");
                break;
            case EXCLUIR_VEICULO:
                System.out.println("Chamar metodo excluir veiculo");
                break;
            case EXCLUIR_SINISTRO:
                System.out.println("Chamar metodo excluir sinistro");
                break;
            case VOLTAR:
            	break;
        }
    }

    private static String getFormattedTitle(MenuOperacoes title) {
        int width = 43;
        int padding = width - title.getName().length();

        if (padding <= 0) {
            return title.getName();
        } else {
            int right = padding / 2;
            int left = padding - right;
            String leftPadding = "#".repeat(left);
            String rightPadding = "#".repeat(right);
            return String.format("\n" + leftPadding + " " + title.getName() + " " + rightPadding);
        }
    }
}