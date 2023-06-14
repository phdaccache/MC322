package menu;

import java.util.Scanner;

import pacote.Admin;
import pacote.Cliente;
import pacote.ClientePF;
import pacote.ClientePJ;
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
                System.out.println("Login da Seguradora:");
                seguradora = loginSeguradora();
                if (seguradora == null) {return;}
                System.out.println("Login realizado com sucesso!");
                System.out.printf("Bem vindo(a) %s!\n", seguradora.getNome());
                runSubmenu(option, seguradora, null);
                break;
            case CLIENTE_PJ:
                System.out.println("Login do Cliente:");
                cliente = loginClientePJ();
                if (cliente == null) {return;}
                System.out.println("Login realizado com sucesso!");
                System.out.printf("Bem vindo(a) %s!\n", cliente.getNome());
                runSubmenu(option, null, cliente);
                break;
            case CLIENTE_PF:
                System.out.println("Login do Cliente:");
                cliente = loginClientePF();
                if (cliente == null) {return;}
                System.out.println("Login realizado com sucesso!");
                System.out.printf("Bem vindo(a) %s!\n", cliente.getNome());
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
            case LISTAR_SEGUROS_SEGURADORA:
                seguradora.listarSeguros();
                break;
            case LISTAR_SEGUROS_SEGURADORA_CLIENTE:
                seguradora.listarSegurosPorCliente(scanner);
                break;
            case GERAR_SEGURO:
                seguradora.gerarSeguro(scanner);
                break;
            case CANCELAR_SEGURO:
                seguradora.cancelarSeguro(scanner);
                break;
            case LISTAR_SINISTROS_SEGURADORA:
                seguradora.listarSinistros();
                break;
            case LISTAR_SINISTROS_SEGURADORA_CLIENTE:
                seguradora.listarSinistrosPorCliente(scanner);
                break;
            case GERAR_SINISTRO:
                seguradora.gerarSinistro(scanner);
                break;
            case EXCLUIR_SINISTRO:
                seguradora.excluirSinistro(scanner);
                break;
            case CALCULAR_RECEITA_SEGURADORA:
                seguradora.calcularReceita();
                break;

            // Cliente PF
            case VISUALIZAR_DADOS_CLIENTE_PF:
                cliente.visualizarDados();
                break;
            case LISTAR_SEGUROS_CLIENTE_PF:
                cliente.listarSeguros();
                break;
            case VISUALIZAR_SEGURO_CLIENTE_PF:
                cliente.visualizarSeguro(scanner);
                break;
            case LISTAR_CONDUTORES_PF:
                cliente.listarCondutores();
                break;
            case CADASTRAR_CONDUTOR_PF:
                cliente.cadastrarCondutor(scanner);
                break;
            case EXCLUIR_CONDUTOR_PF:
                cliente.excluirCondutor(scanner);
                break;
            case LISTAR_VEICULOS:
                ((ClientePF)cliente).listarVeiculos();
                break;
            case CADASTRAR_VEICULO:
                ((ClientePF)cliente).cadastrarVeiculo(scanner);
                break;
            case EXCLUIR_VEICULO:
                ((ClientePF)cliente).excluirVeiculo(scanner);
                break;

            // Cliente PJ
            case VISUALIZAR_DADOS_CLIENTE_PJ:
                cliente.visualizarDados();
                break;
            case LISTAR_SEGUROS_CLIENTE_PJ:
                cliente.listarSeguros();
                break;
            case VISUALIZAR_SEGURO_CLIENTE_PJ:
                cliente.visualizarSeguro(scanner);
                break;
            case LISTAR_CONDUTORES_PJ:
                cliente.listarCondutores();
                break;
            case CADASTRAR_CONDUTOR_PJ:
                cliente.cadastrarCondutor(scanner);
                break;
            case EXCLUIR_CONDUTOR_PJ:
                cliente.excluirCondutor(scanner);
                break;
            case LISTAR_FROTAS:
                ((ClientePJ)cliente).listarFrotas();
                break;
            case VISUALIZAR_FROTA:
                ((ClientePJ)cliente).visualizarFrota(scanner);
                break;
            case CADASTRAR_FROTA:
                ((ClientePJ)cliente).cadastrarFrota(scanner);
                break;
            case ATUALIZAR_FROTA:
                ((ClientePJ)cliente).atualizarFrota(scanner);
                break;

            // Comum
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
        // Caso em que nao ha seguradoras cadastradas
        if (Admin.listaSeguradoras == null) {
            System.out.println("Sem seguradoras cadastradas.");
            return null;
        }

        System.out.print("Digite o CNPJ da seguradora: ");
        String cnpj = scanner.nextLine();

        // Iterando sobre as seguradoras
        for (Seguradora seguradora : Admin.listaSeguradoras) {
            if (seguradora.getCNPJ().equals(cnpj)) {
                return seguradora;
            }
        }

        System.out.println("CNPJ invalido.");
        return null;
    }

    // Login do cliente comum
    private Cliente login() {
        boolean clientesIsEmpty = true;

        // Caso em que nao ha seguradoras cadastradas
        if (Admin.listaSeguradoras == null) {
            System.out.println("Sem seguradoras cadastradas.");
            return null;
        }

        System.out.print("Digite o documento do cliente: ");
        String documento = scanner.nextLine();

        // Iterando sobre as seguradoras
        for (Seguradora seguradora : Admin.listaSeguradoras) {
            // Caso em que ha clientes cadastrados
            if (!seguradora.getListaClientes().isEmpty()) {
                clientesIsEmpty = false;
            }
            // Iterando sobre os clientes
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente.getDocumento()[1].equals(documento)) {
                    return cliente;
                }
            }
        }

        if (clientesIsEmpty) {
            System.out.println("Sem clientes cadastrados.");
            return null;
        }

        System.out.println("Documento invalido.");
        return null;
    }

    // Realiza login do cliente PJ
    private Cliente loginClientePJ() {
        Cliente cliente = login();
        if (cliente == null) {
            return null;
        } else if (cliente instanceof ClientePJ) {
            return cliente;
        } else {
            System.out.println("Documento invalido.");
            return null;
        }
    }

    // Realiza login do cliente PF
    private Cliente loginClientePF() {
        Cliente cliente = login();
        if (cliente == null) {
            return null;
        } else if (cliente instanceof ClientePF) {
            return cliente;
        } else {
            System.out.println("Documento invalido.");
            return null;
        }
    }
}