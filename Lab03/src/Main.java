import java.util.Scanner;

import pacote.Seguradora;

public class Main {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        cadastrarSeguradora();

        showMenu();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                menuSeguradora();
                showMenu();
            }
            else if (opcao == 2){
                menuCliente();
                showMenu();
            }
            else if (opcao == 3){
                menuVeiculo();
                showMenu();
            }
            else if (opcao == 4){
                menuSinistro();
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
        System.out.println("############# Menu ##############");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Seguradora          |");
        System.out.println("| Opcao 2 - Cliente             |");
        System.out.println("| Opcao 3 - Veiculo             |");
        System.out.println("| Opcao 4 - Sinistro            |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuSeguradora() {
        System.out.println("######## Menu Seguradora ########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Listar Clientes     |");
        System.out.println("| Opcao 2 - Listar Sinistros    |");
        System.out.println("| Opcao 3 - Cadastrar Cliente   |");
        System.out.println("| Opcao 4 - Remover Cliente     |");
        System.out.println("| Opcao 5 - Visualizar Sinistro |");
        System.out.println("| Opcao 6 - Gerar Sinistro      |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuCliente() {
        System.out.println("######### Menu Cliente ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuVeiculo() {
        System.out.println("######### Menu Veiculo ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuSinistro() {
        System.out.println("######### Menu Sinistro #########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void menuSeguradora() {
        showMenuSeguradora();

        while (true) {
            System.out.print("Digite uma opcao: ");
            int opcao = input.nextInt();

            if (opcao == 0) {
                break;
            }
            else if (opcao == 1){
                
            }
            else if (opcao == 2){

            }
            else if (opcao == 3){

            }
            else if (opcao == 4){

            }
            else if (opcao == 5){

            }
            else if (opcao == 6){

            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }
    }

    /**
     * Esse menu é apenas para instanciar o método toString() do Cliente na main.
     */
    private static void menuCliente() {
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
                // Cliente cliente = visualizarCliente(nome)
                // System.out.println(cliente.getNome());
            }
            else {
                System.out.println("\nOpcao Invalida.\n");
            }
        }

    }

    // private static Cliente visualizarCliente(String nome) {
    //     ArrayList<Cliente> listaClientes = seguradora.getListaClientes();
    //     for(Cliente cliente : listaClientes) {
    //         if (nome.equals(cliente.getNome())) {
    //             return cliente;
    //         }
    //     }
    // }

    /**
     * Esse menu é apenas para instanciar o método toString() do Veiculo na main.
     */
    private static void menuVeiculo() {
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
    private static void menuSinistro() {
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