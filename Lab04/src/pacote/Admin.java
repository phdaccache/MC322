package pacote;

import java.util.Scanner;
import java.util.ArrayList;

public class Admin {
    // Atributo
    public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();

    // Cadastrar nova seguradora automatico
    public static void cadastrarSeguradora(Seguradora seguradora) {
        if (!Validacao.validarNome(seguradora.getNome()) || listaSeguradoras.contains(seguradora)) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar a seguradora.");
            return;
        }

        listaSeguradoras.add(seguradora);
        System.out.println("Seguradora cadastrada!");
    }

    // Cadastrar nova seguradora com scanner
    public static void cadastrarSeguradora(Scanner scanner) {
        System.out.print("Insira o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Insira o email: ");
        String email = scanner.nextLine();
        System.out.print("Insira o endereco: ");
        String endereco = scanner.nextLine();

        Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        
        cadastrarSeguradora(seguradora);
    }

    // Excluir seguradora automatico
    public static void excluirSeguradora(Seguradora seguradora) {
        String nome = seguradora.getNome();
        if (listaSeguradoras.contains(seguradora)) {
            listaSeguradoras.remove(seguradora);
            System.out.printf("Seguradora '%s' removida!\n", nome);
            return;
        }

        System.out.printf("Nome invalido. Nao foi possivel remover a seguradora '%s'.\n", nome);
    }

    // Excluir seguradora com scanner
    public static void excluirSeguradora(Scanner scanner) {
        System.out.print("Insira o nome da seguradora que deseja excluir: ");
        String nome = scanner.nextLine();

        for (Seguradora seguradora : listaSeguradoras) {
            if (seguradora.getNome().equals(nome)) {
                listaSeguradoras.remove(seguradora);
                System.out.printf("Seguradora '%s' removida!\n", nome);
                return;
            }
        }

        System.out.printf("Nome invalido. Nao foi possivel remover a seguradora '%s'.\n", nome);
    }

    // Listar clientes por seguradora
    public static void listarClientes(Scanner scanner) {
        // Caso em que nao ha seguradoras
        if (listaSeguradoras.isEmpty()) {
            System.out.println("Nao ha seguradoras cadastradas.");
            return;
        }
        
        // Iterando sobre as seguradoras
        for (int i = 0; i < listaSeguradoras.size(); i++) {
            Seguradora seguradora = listaSeguradoras.get(i);
            System.out.printf("Seguradora %d - '%s':\n", i+1, seguradora.getNome());
            System.out.println("---------------------------------------------");
            // Caso em que nao ha clientes
            if (seguradora.getListaClientes().isEmpty()) {
                System.out.println("Nao ha clientes cadastrados.");
            }
            // Iterando sobre os clientes
            for (int j = 0; j < seguradora.getListaClientes().size(); j++) {
                System.out.printf("Cliente %d: %s.\n", j+1, seguradora.getListaClientes().get(j).getNome());
            }
            System.out.println("---------------------------------------------");
            System.out.println("");
        }

        // Visualizar cliente com mais detalhes
        System.out.print("Insira o numero da seguradora que deseja visualizar: ");
        int i = scanner.nextInt();
        System.out.print("Insira o numero do cliente que deseja visualizar: ");
        int j = scanner.nextInt();
        scanner.nextLine();

        // Caso em que a seguradora nao existe e/ou o cliente nao existe
        if (i < 1 || i > listaSeguradoras.size() ||
                        j < 1 || j > listaSeguradoras.get(i-1).getListaClientes().size()) {
            System.out.println("Cliente invalido.");
            return;
        }

        // Impressao do cliente selecionado
        System.out.println("---------------------------------------------");
        System.out.printf("Cliente %d da seguradora %d:\n", j, i);
        System.out.println(listaSeguradoras.get(i-1).getListaClientes().get(j-1));
        System.out.println("---------------------------------------------");
    }

    // Listar sinistros por seguradora
    public static void listarSinistros(Scanner scanner) {
        // Caso em que nao ha seguradoras
        if (listaSeguradoras.isEmpty()) {
            System.out.println("Nao ha seguradoras cadastradas.");
            return;
        }
        
        // Iterando sobre as seguradoras
        for (int i = 0; i < listaSeguradoras.size(); i++) {
            Seguradora seguradora = listaSeguradoras.get(i);
            System.out.printf("Seguradora %d - '%s':\n", i+1, seguradora.getNome());
            System.out.println("---------------------------------------------");
            // Caso em que nao ha sinistros
            if (seguradora.getListaSinistros().isEmpty()) {
                System.out.println("Nao ha sinistros cadastrados.");
                System.out.println("---------------------------------------------");
            }
            // Iterando sobre os sinistros
            for (Sinistro sinistro: seguradora.getListaSinistros()) {
                System.out.println(sinistro);
                System.out.println("---------------------------------------------");
            }
            System.out.println("");
        }

        // Visualizar sinistro com mais detalhes
        System.out.print("Insira o numero da seguradora que deseja visualizar: ");
        int i = scanner.nextInt();
        System.out.print("Insira o numero do sinistro que deseja visualizar: ");
        int j = scanner.nextInt();
        scanner.nextLine();

        // Caso em que a seguradora nao existe e/ou o sinistro nao existe
        if (i < 1 || i > listaSeguradoras.size() ||
                        j < 1 || j > listaSeguradoras.get(i-1).getListaClientes().size()) {
            System.out.println("Sinistro invalido.");
            return;
        }

        // Impressao do sinistro selecionado
        Seguradora seguradora = listaSeguradoras.get(i-1);
        Sinistro sinistro = seguradora.getListaSinistros().get(j-1);
        seguradora.visualizarSinistro(sinistro);
    }

    // Listar veiculos por seguradora
    public static void listarVeiculos(Scanner scanner) {
        // Caso em que nao ha seguradoras
        if (listaSeguradoras.isEmpty()) {
            System.out.println("Nao ha seguradoras cadastradas.");
            return;
        }
        
        // Iterando sobre as seguradoras
        for (int i = 0; i < listaSeguradoras.size(); i++) {
            Seguradora seguradora = listaSeguradoras.get(i);
            System.out.printf("Seguradora %d - '%s':\n", i+1, seguradora.getNome());
            System.out.println("---------------------------------------------");
            // Caso em que nao ha clientes. Logo, tambem nao ha veiculos
            if (seguradora.getListaClientes().isEmpty()) {
                System.out.println("Nao ha veiculos cadastrados.");
                System.out.println("---------------------------------------------");
                return;
            }
            
            boolean semVeiculos = true;
            int inicio = 1; // Para enumerar os veiculos de forma corrida (i.e. nao resetar para 0 a cada cliente)
            // Iterando sobre os clientes
            for (Cliente cliente: seguradora.getListaClientes()) {
                // Iterando sobre os veiculos
                for (int j = 0; j < cliente.getListaVeiculos().size(); j++) {
                    System.out.printf("Veiculo %d:\n", inicio);
                    System.out.println(cliente.getListaVeiculos().get(j));
                    System.out.println("---------------------------------------------");
                    inicio++;
                    semVeiculos = false;
                }
            }

            // Caso em que ha clientes, mas nenhum cliente tem veiculo
            if (semVeiculos) {
                System.out.println("Nao ha veiculos cadastrados.");
                System.out.println("---------------------------------------------");
            }
            System.out.println("");
        }
    }
}