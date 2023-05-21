package menu;

import java.util.Scanner;

import pacote.Admin;
import pacote.Cliente;
import pacote.Seguradora;

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
        if (numOption < 10) {
            System.out.printf("| Opcao %d - %-31s |\n", numOption, option.getName());
        } else {
            System.out.printf("| Opcao %d - %-30s |\n", numOption, option.getName());
        }
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
            scanner.nextLine();
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
        Seguradora seguradora;
        Cliente cliente;

        switch (option) {
            case ADMIN:
                runSubmenu(option, null, null);
                break;
            case SEGURADORA:
                seguradora = loginSeguradora();
                if (seguradora == null) {return;}
                runSubmenu(option, seguradora, null);
                break;
            case CLIENTE:
                cliente = loginCliente();
                if (cliente == null) {return;}
                runSubmenu(option, null, cliente);
                break;
            case SAIR:
                System.out.println("Programa encerrado.");
        }
    }

    // Executa submenu (imprime, recebe a opcao e executa a opcao) de acordo com a opcao passada
    private void runSubmenu(MenuOperacoes option, Seguradora seguradora, Cliente cliente) {
        SubmenuOperacoes subOption;
        do {
            showSubmenu(option);
            subOption = readOptionSubmenu(option);
            if (seguradora != null) {
                runSubmenuOption(subOption, seguradora, null);
            } else if (cliente != null) {
                runSubmenuOption(subOption, null, cliente);
            } else {
                runSubmenuOption(subOption, null, null);
            }
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
            if (numSuboption < 10) {
                System.out.printf("| Opcao %d - %-31s |\n", numSuboption, submenu[i].getName());
            } else {
                System.out.printf("| Opcao %d - %-30s |\n", numSuboption, submenu[i].getName());
            }
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
            scanner.nextLine();
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
    private void runSubmenuOption(SubmenuOperacoes subOption, Seguradora seguradora, Cliente cliente) {
        switch(subOption) {
            // Admin
            case LISTAR_SEGURADORAS:
                Admin.listarSeguradoras();
                break;
            case CADASTRAR_SEGURADORA:
                Admin.cadastrarSeguradora(scanner);
                break;
            case EXCLUIR_SEGURADORA:
                Admin.excluirSeguradora(scanner);
                break;
            case LISTAR_CLIENTES_SEGURADORA:
                Admin.listarClientes(scanner);
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                Admin.listarSinistros(scanner);
                break;
            case LISTAR_VEICULOS_SEGURADORA:
                Admin.listarVeiculos(scanner);
                break;

            // Seguradora
            case VISUALIZAR_DADOS_SEGURADORA:
                seguradora.visualizarDados();
                break;
            case LISTAR_CLIENTES:
                seguradora.listarClientes();
                break;
            case CADASTRAR_CLIENTE:
                seguradora.cadastrarCliente(scanner);
                break;
            case EXCLUIR_CLIENTE:
                seguradora.excluirCliente(scanner);
                break;
            case LISTAR_SINISTROS_CLIENTE:
                seguradora.listarSinistros(scanner);
                break;
            case GERAR_SINISTRO:
                seguradora.gerarSinistro(scanner);
                break;
            case EXCLUIR_SINISTRO:
                seguradora.excluirSinistro(scanner);
                break;
            case LISTAR_VEICULOS_CLIENTE:
                seguradora.listarVeiculos();
                break;
            case TRANSFERIR_SEGURO:
                seguradora.transferirSeguro(scanner);
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                seguradora.calcularReceita();
                break;

            // Cliente
            case VISUALIZAR_DADOS_CLIENTE:
                cliente.visualizarDados();
                break;
            case LISTAR_VEICULOS:
                cliente.listarVeiculos();
                break;
            case CADASTRAR_VEICULO:
                cliente.cadastrarVeiculo(scanner);
                break;
            case EXCLUIR_VEICULO:
                cliente.excluirVeiculo(scanner);
                break;
            case LISTAR_SINISTROS:
                cliente.listarSinistros();
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

    // Realiza login da seguradora
    private Seguradora loginSeguradora() {
        while (true) {
            // Caso em que nao ha seguradoras cadastradas
            if (Admin.listaSeguradoras == null) {
                System.out.println("Sem seguradoras cadastradas.");
                return null;
            }

            System.out.print("Digite o nome da seguradora: ");
            String nome = scanner.nextLine();

            // Iterando sobre as seguradoras
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                if (seguradora.getNome().equals(nome)) {
                    return seguradora;
                }
            }

            System.out.println("Nome invalido.");
            return null;
        }
    }

    // Realiza login do cliente
    private Cliente loginCliente() {
        boolean clientesIsEmpty = true;

        while (true) {
            // Caso em que nao ha seguradoras cadastradas
            if (Admin.listaSeguradoras == null) {
                System.out.println("Sem seguradoras cadastradas.");
                return null;
            }

            System.out.print("Digite o nome do cliente: ");
            String nome = scanner.nextLine();

            // Iterando sobre as seguradoras
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                // Caso em que nao ha clientes cadastrados
                if (!seguradora.getListaClientes().isEmpty()) {
                    clientesIsEmpty = false;
                }
                // Iterando sobre os clientes
                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente.getNome().equals(nome)) {
                        return cliente;
                    }
                }
            }

            if (clientesIsEmpty) {
                System.out.println("Sem clientes cadastrados.");
                return null;
            }

            System.out.println("Nome invalido.");
            return null;
        }
    }
}