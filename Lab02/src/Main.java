import pacote.Seguradora;
import java.util.Scanner;

// import pacote.*;

public class Main {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        String input_nome = myObj.nextLine();

        Seguradora seguradora = new Seguradora(input_nome,1,"c","d");
        String nome = seguradora.getNome();
        int telefone = seguradora.getTelefone();
        String email = seguradora.getEmail();
        String endereco = seguradora.getEndereco();

        System.out.println(nome+telefone+email+endereco);


        myObj.close();
    }
}