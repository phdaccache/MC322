import pacote.Seguradora;
import java.util.Scanner;

// import pacote.*;

public class Main {
    public static void main(String[] args) {
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

        // Pegando os dados de dentro da classe com o método get ao invés de pegar os dados da saída padrão
        String nome = seguradora.getNome();
        long telefone = seguradora.getTelefone();
        String email = seguradora.getEmail();
        String endereco = seguradora.getEndereco();

        System.out.print("Resumo dos dados tirados da classe Seguradora:\n");
        System.out.println(seguradora.toString(nome, telefone, email, endereco));

        input.close();
    }
}