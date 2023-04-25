import java.util.ArrayList;
import java.util.Scanner;

import pacote.Cliente;
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
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuCliente() {
        System.out.println("\n######### Menu Cliente ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuVeiculo() {
        System.out.println("\n######### Menu Veiculo ##########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void showMenuSinistro() {
        System.out.println("\n######### Menu Sinistro #########");
        System.out.println("|-------------------------------|");
        System.out.println("| Opcao 1 - Checar dados        |");
        System.out.println("| Opcao 0 - Sair                |");
        System.out.println("|-------------------------------|\n");
    }

    private static void menuSeguradora(Seguradora seguradora) {
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