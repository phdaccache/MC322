package menu;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    // Construtor
    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    // Imprime Menu no terminal
    public void showMenu() {
        MenuOperacoes menu[] = MenuOperacoes.values();
        String title = getFormattedTitle("Menu");

        System.out.println(title);
        System.out.println("|-------------------------------------------|");
        for (MenuOperacoes option : menu) {
            int numOption;
            if (option.ordinal() == menu.length - 1) { // Se a opcao for a ultima (i.e. "SAIR"), recebe 0
                numOption = 0;
            } else {
                numOption = option.ordinal() + 1;
            }
            System.out.printf("| Opcao %d - %-31s |\n", numOption, option.getName());
        }
        System.out.println("|-------------------------------------------|");
    }

    // Le a opcao escolhida no menu com o scanner e a retorna
    public MenuOperacoes readOptionMenu() {
        int option;
        MenuOperacoes menu[] = MenuOperacoes.values();

        // Enquanto a opcao nao for valida, fica pedindo a opcao novamente
        while (true) {
            System.out.print("Digite uma opcao: ");
            option = scanner.nextInt();
            if (option < 0 || option > menu.length - 1) {
                System.out.println("Opcao Invalida.");
            } else if (option == 0) { 
                return MenuOperacoes.values()[menu.length - 1]; // opcao 0 = "SAIR", i.e. ultima opcao do menu
            } else {
                return MenuOperacoes.values()[option - 1];
            }
        }
    }

    // Executa a opcao do menu passada
    public void runMenuOption(MenuOperacoes option) {
        switch (option) {
            case ADMIN:
            case SEGURADORA:
            case CLIENTE:
                runSubmenu(option);
                break;
            case SAIR:
                System.out.println("Programa encerrado.");
        }
    }

    // Executa submenu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
    private void runSubmenu(MenuOperacoes option) {
        SubmenuOperacoes subOption;
        do {
            showSubmenu(option);
            subOption = readOptionSubmenu(option);
            runSubmenuOption(subOption);
        } while (subOption != SubmenuOperacoes.VOLTAR);
    }

    // Imprime o Submenu de "option" no terminal.
    private void showSubmenu(MenuOperacoes option) {
        SubmenuOperacoes submenu[] = option.getSubOptions();
        String title = getFormattedTitle(option.getName());

        System.out.println(title);
        System.out.println("|-------------------------------------------|");
        for (int i = 0; i < submenu.length; i++) {
            int numSuboption;
            if (i == submenu.length - 1) { // Se a opcao for a ultima (i.e. "VOLTAR"), recebe 0
                numSuboption = 0;
            } else {
                numSuboption = i + 1;
            }
            System.out.printf("| Opcao %d - %-31s |\n", numSuboption, submenu[i].getName());
        }
        System.out.println("|-------------------------------------------|");
    }
    
    // Le a opcao escolhida no submenu com o scanner e a retorna
    private SubmenuOperacoes readOptionSubmenu(MenuOperacoes option) {
        int subOption;
        SubmenuOperacoes submenu[] = option.getSubOptions();

        // Enquanto a opcao nao for valida, fica pedindo a opcao novamente
        while (true) {
            System.out.print("Digite uma opcao: ");
            subOption = scanner.nextInt();
            if (subOption < 0 || subOption > submenu.length - 1) {
                System.out.println("Opcao Invalida.");
            } else if (subOption == 0) {
                return submenu[submenu.length - 1]; // opcao 0 = "VOLTAR", i.e. ultima opcao do submenu
            } else {
                return submenu[subOption - 1];
            }
        }
    }

    // Executa a opcao do submenu passada
    private void runSubmenuOption(SubmenuOperacoes subOption) {
        switch(subOption) {
            // Admin
            case CADASTRAR_SEGURADORA:
                System.out.println("Chamar metodo CADASTRAR_SEGURADORA");
                break;
            case EXCLUIR_SEGURADORA:
                System.out.println("Chamar metodo EXCLUIR_SEGURADORA");
                break;
            case LISTAR_CLIENTES_SEGURADORA:
                System.out.println("Chamar metodo LISTAR_CLIENTES_SEGURADORA");
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                System.out.println("Chamar metodo LISTAR_SINISTROS_SEGURADORA");
                break;
            case LISTAR_VEICULOS_SEGURADORA:
                System.out.println("Chamar metodo LISTAR_VEICULOS_SEGURADORA");
                break;

            // Seguradora
            case VISUALIZAR_DADOS_SEGURADORA:
                System.out.println("Chamar metodo VISUALIZAR_DADOS_SEGURADORA");
                break;
            case LISTAR_CLIENTES:
                System.out.println("Chamar metodo LISTAR_CLIENTES");
                break;
            case CADASTRAR_CLIENTE:
                System.out.println("Chamar metodo CADASTRAR_CLIENTE");
                break;
            case EXCLUIR_CLIENTE:
                System.out.println("Chamar metodo EXCLUIR_CLIENTE");
                break;
            case LISTAR_SINISTROS_CLIENTE:
                System.out.println("Chamar metodo LISTAR_SINISTROS_CLIENTE");
                break;
            case GERAR_SINISTRO:
                System.out.println("Chamar metodo GERAR_SINISTRO");
                break;
            case EXCLUIR_SINISTRO:
                System.out.println("Chamar metodo EXCLUIR_SINISTRO");
                break;
            case LISTAR_VEICULOS_CLIENTE:
                System.out.println("Chamar metodo LISTAR_VEICULOS_CLIENTE");
                break;
            case TRANSFERIR_SEGURO:
                System.out.println("Chamar metodo TRANSFERIR_SEGURO");
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                System.out.println("Chamar metodo CALCULAR_RECEITA_SEGURADORA");
                break;

            // Cliente
            case VISUALIZAR_DADOS_CLIENTE:
                System.out.println("Chamar metodo VISUALIZAR_DADOS_CLIENTE");
                break;
            case LISTAR_VEICULOS:
                System.out.println("Chamar metodo LISTAR_VEICULOS");
                break;
            case CADASTRAR_VEICULO:
                System.out.println("Chamar metodo CADASTRAR_VEICULO");
                break;
            case EXCLUIR_VEICULO:
                System.out.println("Chamar metodo EXCLUIR_VEICULO");
                break;
            case LISTAR_SINISTROS:
                System.out.println("Chamar metodo LISTAR_SINISTROS");
                break;
            case VOLTAR:
            	break;
        }
    }

    // Retorna o titulo do menu (ou submenu) no formato '#################### Menu ###################'
    private String getFormattedTitle(String title) {
        int width = 43; // Tamanho total do titulo
        int padding = width - title.length();

        if (padding <= 0) { // padding <= 0 indica que o titulo e maior ou igual ao tamanho total
            return title; // Se esse for o caso, nao tem espaco para colocar o #. Logo, retorna o titulo
        } else {
            int right = padding / 2;
            int left = padding - right; // O esquerdo e sempre igual ao direito ou igual ao direito + 1
            String leftPadding = "#".repeat(left);
            String rightPadding = "#".repeat(right);
            return String.format("\n%s %s %s", leftPadding, title, rightPadding);
        }
    }
}