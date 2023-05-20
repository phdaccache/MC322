package pacote;

import java.util.Scanner;
import java.util.ArrayList;

public class Admin {
    // Atributo
    public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();

    // Cadastrar nova seguradora automatico
    public static void cadastrarSeguradora(Seguradora seguradora) {
        if (!Validacao.validarNome(seguradora.getNome()) || listaSeguradoras.contains(seguradora)) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar a seguradora");
            return;
        }

        listaSeguradoras.add(seguradora);
        System.out.println("Seguradora cadastrada!");
    }

    // Cadastrar nova seguradora com scanner
    public static void cadastrarSeguradora(Scanner scanner) {
        return;
    }

    // Excluir seguradora com scanner
    public static void excluirSeguradora(Scanner scanner) {
        return;
    }

    // Listar clientes por seguradora
    public static void listarClientes() {
        return;
    }

    // Listar sinistros por seguradora
    public static void listarSinistros() {
        return;
    }

    // Listar veiculos por seguradora
    public static void listarVeiculos() {
        return;
    }
}