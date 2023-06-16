package pacote;

import java.util.Scanner;
import java.util.ArrayList;

public class Admin {
    // Atributo
    public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();

    // Listar todas as seguradoras
    public static void listarSeguradoras() {
        System.out.println("Seguradoras cadastradas:");

        // Caso em que nao ha seguradoras
        if (listaSeguradoras.isEmpty()) {
            System.out.println("    * Nao ha seguradoras cadastradas.");
            return;
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
        // Checando se foi passado uma seguradora
        if (seguradora == null) {
            System.out.println("Nao foi possivel cadastrar a seguradora.");
            return;
        }
        
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
        // Checando se foi passado uma seguradora
        if (seguradora == null) {
            System.out.println("Nao foi possivel remover a seguradora.");
            return;
        }

        String nome = seguradora.getNome();
        String cnpj = seguradora.getCNPJ();

        // Checar se a seguradora existe antes de excluir
        if (!listaSeguradoras.contains(seguradora)) {
            System.out.printf("Nao existe a seguradora '%s' de CNPJ %s. Nao foi possivel remover a seguradora.\n", nome, cnpj);
            return;
        }

        listaSeguradoras.remove(seguradora);
        System.out.printf("Seguradora '%s' de CNPJ %s removida!\n", nome, cnpj);
    }

    // Excluir seguradora com scanner
    public static void excluirSeguradora(Scanner scanner) {
        Seguradora seguradora = null;
        System.out.print("Insira o CNPJ da seguradora que deseja excluir: ");
        String cnpj = scanner.nextLine();

        for (Seguradora seg : listaSeguradoras) {
            if (seg.getCNPJ().equals(cnpj)) {
                seguradora = seg;
            }
        }

        excluirSeguradora(seguradora);
    }
}