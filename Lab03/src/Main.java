import java.util.ArrayList;
import java.util.Scanner;

import pacote.Cliente;
import pacote.ClientePF;
import pacote.ClientePJ;
import pacote.Seguradora;
import pacote.Veiculo;

public class Main {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) { 
        Seguradora seguradora = cadastrarSeguradora();

        showMenu();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                menuSeguradora(seguradora);
                showMenu();
            }
            else if (opcao == 2){
                menuCliente(seguradora);
                showMenu();
            }
            else if (opcao == 3){
                menuVeiculo(seguradora);
                showMenu();
            }
            else if (opcao == 4){
                menuSinistro(seguradora);
                showMenu();
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }

        input.close();
    }

    private static Seguradora cadastrarSeguradora() {
        System.out.println("Para comecar, cadastre uma seguradora.");
        // Lendo os dados da entrada padrão
        System.out.print("Insira o nome: ");
        String nome = input.nextLine();
        System.out.print("Insira o telefone: ");
        String telefone = input.nextLine();
        System.out.print("Insira o email: ");
        String email = input.nextLine();
        System.out.print("Insira o endereco: ");
        String endereco = input.nextLine();

        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);

        return seguradora;
    }

    private static void showMenu() {
        System.out.println("\n############# Menu ##############");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Seguradora          |");
        System.out.println("| Opcao 2 - Cliente             |");
        System.out.println("| Opcao 3 - Veiculo             |");
        System.out.println("| Opcao 4 - Sinistro            |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuSeguradora() {
        System.out.println("\n######## Menu Seguradora ########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Listar Clientes     |");
        System.out.println("| Opcao 2 - Listar Sinistros    |");
        System.out.println("| Opcao 3 - Cadastrar Cliente   |");
        System.out.println("| Opcao 4 - Remover Cliente     |");
        System.out.println("| Opcao 5 - Visualizar Sinistro |");
        System.out.println("| Opcao 6 - Gerar Sinistro      |");
        System.out.println("| Opcao 0 - Voltar              |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuCliente() {
        System.out.println("\n######### Menu Cliente ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Voltar               |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuVeiculo() {
        System.out.println("\n######### Menu Veiculo ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Voltar              |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuSinistro() {
        System.out.println("\n######### Menu Sinistro #########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Voltar              |");
        System.out.println("|-------------------------------|\n");
    }

    private static void menuSeguradora(Seguradora seguradora) {
        showMenuSeguradora();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();
            input.nextLine();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                System.out.println("\nPessoas Juridicas:");
                seguradora.listarClientes("PJ");
                System.out.println("\nPessoas Fisicas:");
                seguradora.listarClientes("PF");

                showMenuSeguradora();
            }
            else if (opcao == 2){

            }
            else if (opcao == 3){
                System.out.println("\n######## Tipo de Cliente ########");
                System.out.println("|-------------------------------|");
                System.out.println("| Opcao 1 - Pessoa Juridica     |");
                System.out.println("| Opcao 2 - Pessoa Fisica       |");
                System.out.println("|-------------------------------|\n");
                System.out.print("Digite uma opcao: ");
                int tipo = input.nextInt();
                input.nextLine();

                Cliente cliente;

                if (tipo == 1) {
                    System.out.print("Insira o nome: ");
                    String nome = input.nextLine();
                    System.out.print("Insira o endereco: ");
                    String endereco = input.nextLine();
                    System.out.print("Insira o cnpj: ");
                    String cnpj = input.nextLine();
                    System.out.print("Insira a data de fundacao (dd/MM/yyyy): ");
                    String data = input.nextLine();

                    cliente = new ClientePJ(nome, endereco, "PJ", cnpj, data);

                    if (seguradora.cadastrarCliente(cliente)) {
                        System.out.println("\nCliente cadastrado.");
                    } else {
                        System.out.println("\nNao foi possivel cadastrar o cliente.");
                    }

                } else if (tipo == 2) {
                    System.out.print("Insira o nome: ");
                    String nome = input.nextLine();
                    System.out.print("Insira o endereco: ");
                    String endereco = input.nextLine();
                    System.out.print("Insira o cpf: ");
                    String cpf = input.nextLine();
                    System.out.print("Insira o genero: ");
                    String genero = input.nextLine();
                    System.out.print("Insira a data de licenca (dd/MM/yyyy): ");
                    String dataLicenca = input.nextLine();
                    System.out.print("Insira o nivel de educacao: ");
                    String educacao = input.nextLine();
                    System.out.print("Insira a data de nascimento (dd/MM/yyyy): ");
                    String dataNascimento = input.nextLine();
                    System.out.print("Insira a classe economica: ");
                    String classe = input.nextLine();

                    cliente = new ClientePF(nome, endereco, "PF", cpf, genero, dataLicenca,
                                            educacao, dataNascimento, classe);

                    if (seguradora.cadastrarCliente(cliente)) {
                        System.out.println("\nCliente cadastrado.");
                    } else {
                        System.out.println("\nNao foi possivel cadastrar o cliente.");
                    }
                }

                showMenuSeguradora();
            }
            else if (opcao == 4){
                System.out.print("Insira o nome do cliente que deseja remover: ");
                String nome = input.nextLine();
                if (seguradora.removerCliente(nome)) {
                    System.out.printf("\nCliente de nome '%s' removido.\n", nome);
                } else {
                    System.out.printf("\nNao foi possivel remover o cliente de nome '%s'.\n", nome);
                }

                showMenuSeguradora();
            }
            else if (opcao == 5){

            }
            else if (opcao == 6){
                System.out.print("Insira a data que ocorreu o sinistro (dd/MM/yyyy): ");
                String data = input.nextLine();
                System.out.print("Insira o nome do cliente: ");
                String nome = input.nextLine();
                System.out.print("Insira o endereco: ");
                String endereco = input.nextLine();
                System.out.print("Insira a placa do veiculo: ");
                String placa = input.nextLine();

                if (seguradora.gerarSinistro(data, nome, endereco, placa)) {
                    System.out.printf("\nSinistro gerado.\nNome do cliente: %s.\nPlaca do Veiculo: %s.\n",
                                    nome, placa);
                } else {
                    System.out.printf("\nNao foi possivel gerar o sinistro para o cliente %s com veiculo de placa %s.\n",
                                    nome, placa);
                }

                showMenuSeguradora();
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }
    }

    /**
     * Esse menu é apenas para instanciar o método toString() do Cliente na main.
     */
    private static void menuCliente(Seguradora seguradora) {
        showMenuCliente();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                System.out.print("Insira o nome do cliente: ");
                String nome = input.nextLine();
                Cliente cliente = visualizarCliente(nome, seguradora);
                System.out.println(cliente.toString());
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }

    }

    private static Cliente visualizarCliente(String nome, Seguradora seguradora) {
        ArrayList<Cliente> listaClientes = seguradora.getListaClientes();
        for(Cliente cliente : listaClientes) {
            if (nome.equals(cliente.getNome())) {
                return cliente;
            }
        }

        return null;
    }

    /**
     * Esse menu é apenas para instanciar o método toString() do Veiculo na main.
     */
    private static void menuVeiculo(Seguradora seguradora) {
        showMenuVeiculo();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }
    }

    /**
     * Esse menu é apenas para instanciar o método toString() do Sinistro na main.
     */
    private static void menuSinistro(Seguradora seguradora) {
        showMenuSinistro();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }
    }
}