package pacote;

import java.util.Scanner;
import java.util.ArrayList;

public class Admin {
    // Atributo
    public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();

    // Listar todas as seguradoras
    public static void listarSeguradoras() {
        // Caso em que nao ha seguradoras
        if (listaSeguradoras.isEmpty()) {
            System.out.println("Nao ha seguradoras cadastradas.");
        }
        // Iterando sobre as seguradoras
        for (Seguradora seguradora: listaSeguradoras) {
            System.out.println("---------------------------------------------");
            System.out.println(seguradora);
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar nova seguradora automatico
    public static void cadastrarSeguradora(Seguradora seguradora) {
        // Caso em que o CNPJ e invalido
        if (!Validacao.validarDocumento(seguradora.getCNPJ(), "CNPJ")) {
            System.out.println("CNPJ invalido. Nao foi possivel cadastrar a seguradora.");
            return;
        }

        // Caso em que o CNPJ ja existe
        for (Seguradora seg : listaSeguradoras) {
            if (seg.getCNPJ().equals(seguradora.getCNPJ())) {
                System.out.printf("Ja existe a seguradora de CNPJ %s. Nao foi possivel cadastrar a seguradora.\n",
                                seg.getCNPJ());
            return;
            }
        }

        listaSeguradoras.add(seguradora);
        System.out.println("Seguradora cadastrada!");
    }

    // Cadastrar nova seguradora com scanner
    public static void cadastrarSeguradora(Scanner scanner) {
        System.out.print("Insira o CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Insira o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Insira o endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("Insira o email: ");
        String email = scanner.nextLine();

        Seguradora seguradora = new Seguradora(cnpj, nome, telefone, endereco, email);
        
        cadastrarSeguradora(seguradora);
    }

    // Excluir seguradora automatico
    public static void excluirSeguradora(Seguradora seguradora) {
        String nome = seguradora.getNome();
        String cnpj = seguradora.getCNPJ();
        // Checar se a seguradora existe antes de excluir
        if (listaSeguradoras.contains(seguradora)) {
            listaSeguradoras.remove(seguradora);
            System.out.printf("Seguradora '%s' de CNPJ %s removida!\n", nome, cnpj);
            return;
        }

        System.out.printf("CNPJ invalido. Nao foi possivel remover a seguradora '%s' de CNPJ %s.\n", nome, cnpj);
    }

    // Excluir seguradora com scanner
    public static void excluirSeguradora(Scanner scanner) {
        System.out.print("Insira o CNPJ da seguradora que deseja excluir: ");
        String cnpj = scanner.nextLine();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getCNPJ().equals(cnpj)) {
                String nome = seguradora.getNome();
                listaSeguradoras.remove(seguradora);
                System.out.printf("Seguradora '%s' de CNPJ %s removida!\n", nome, cnpj);
                return;
            }
        }

        System.out.printf("CNPJ invalido. Nao foi possivel remover a seguradora de CNPJ %s.\n", cnpj);
    }
}