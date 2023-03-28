import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /////////////// TESTANDO SEGURADORA ///////////////

        // Lendo os dados da entrada padrão
        Scanner input = new Scanner(System.in);
        System.out.print("Insira o nome: ");
        String input_nome = input.nextLine();
        System.out.print("Insira o telefone: ");
        long input_telefone = input.nextLong();
        input.nextLine();
        System.out.print("Insira o email: ");
        String input_email = input.nextLine();
        System.out.print("Insira o endereco: ");
        String input_endereco = input.nextLine();

        // Criando seguradora com os dados inseridos
        Seguradora seguradora = new Seguradora(input_nome, input_telefone, input_email, input_endereco);

        // Pegando os dados de dentro da classe com o método get ao invés de pegar os dados do input
        String nome = seguradora.getNome();
        long telefone = seguradora.getTelefone();
        String email = seguradora.getEmail();
        String endereco = seguradora.getEndereco();

        // Imprimindo os dados na saída padrão
        System.out.print("Resumo dos dados tirados da classe Seguradora:\n");
        System.out.println(seguradora.toString(nome, telefone, email, endereco));


        // Lendo os dados da entrada padrão para mudança
        System.out.print("Insira o novo nome: ");
        String novo_input_nome = input.nextLine();
        System.out.print("Insira o novo telefone: ");
        long novo_input_telefone = input.nextLong();
        input.nextLine();
        System.out.print("Insira o novo email: ");
        String novo_input_email = input.nextLine();
        System.out.print("Insira o novo endereco: ");
        String novo_input_endereco = input.nextLine();

        seguradora.setNome(novo_input_nome);
        seguradora.setTelefone(novo_input_telefone);
        seguradora.setEmail(novo_input_email);
        seguradora.setEndereco(novo_input_endereco);

        // Pegando os dados de dentro da classe com o método get ao invés de pegar os dados do input
        String novo_nome = seguradora.getNome();
        long novo_telefone = seguradora.getTelefone();
        String novo_email = seguradora.getEmail();
        String novo_endereco = seguradora.getEndereco();

        // Imprimindo os dados na saída padrão
        System.out.print("Resumo dos dados tirados da classe Seguradora:\n");
        System.out.println(seguradora.toString(novo_nome, novo_telefone, novo_email, novo_endereco));




        

        input.close();
    }
}