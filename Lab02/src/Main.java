import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /////////////// TESTANDO SEGURADORA ///////////////

        // Lendo os dados da entrada padrão
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o nome: ");
        String nome = input.nextLine();
        System.out.print("Insira o telefone: ");
        String telefone = input.nextLine();
        System.out.print("Insira o email: ");
        String email = input.nextLine();
        System.out.print("Insira o endereco: ");
        String endereco = input.nextLine();

        // Criando seguradora com os dados inseridos
        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);

        // Imprimindo os dados na saída padrão
        System.out.print("Resumo dos dados tirados da classe Seguradora:\n");
        System.out.println(seguradora.toString());

        // Lendo os dados da entrada padrão para mudança
        System.out.print("Insira o novo nome: ");
        String novo_nome = input.nextLine();
        System.out.print("Insira o novo telefone: ");
        String novo_telefone = input.nextLine();
        System.out.print("Insira o novo email: ");
        String novo_email = input.nextLine();
        System.out.print("Insira o novo endereco: ");
        String novo_endereco = input.nextLine();

        seguradora.setNome(novo_nome);
        seguradora.setTelefone(novo_telefone);
        seguradora.setEmail(novo_email);
        seguradora.setEndereco(novo_endereco);

        // Imprimindo os dados na saída padrão
        System.out.print("Resumo dos dados tirados da classe Seguradora:\n");
        System.out.println(seguradora.toString());


        /////////////// TESTANDO CLIENTE ///////////////

        



        input.close();
    }
}