package pacote;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Frota {
    // Atributos
    private final int id;
    private Seguro seguro;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public Frota(int id) {
        this.id = id;
        this.seguro = null;
        this.listaVeiculos = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("ID: %03d", getId()));
        joiner.add("Veiculos: ");
        if (listaVeiculos.isEmpty()) {
            joiner.add("    Sem veiculos cadastrados.");
        } else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                Veiculo veiculo = listaVeiculos.get(i);
                joiner.add(String.format("    Carro %d: %s - %s",
                                        i+1, veiculo.getModelo(), veiculo.getPlaca()));
            }
        }

        return joiner.toString();
    }

    // Listar todos os veiculos
    public void listarVeiculos() {
        // Caso em que nao ha veiculos cadastrados
        if (listaVeiculos.isEmpty()) {
            System.out.println("Nao ha veiculos cadastrados.");
            return;
        }

        System.out.println("Veiculos:");
        // Iterando sobre os veiculos
        for (Veiculo veiculo : listaVeiculos) {
            System.out.println("---------------------------------------------");
            System.out.println(veiculo);
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo veiculo automatico
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Caso em que a placa passada e invalida
        if (!Validacao.validarPlaca(veiculo.getPlaca())) {
            System.out.println("Placa invalida. Nao foi possivel cadastrar o veiculo.");
            return;
        }

        listaVeiculos.add(veiculo); // Veiculo adicionado
        System.out.println("Veiculo cadastrado!");
    }

    // Cadastrar novo veiculo com scanner
    public void cadastrarVeiculo(Scanner scanner) {
        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o ano de fabricacao: ");
        int ano = scanner.nextInt();

        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);

        cadastrarVeiculo(veiculo);
    }

    // Excluir veiculo automatico
    public void excluirVeiculo(Veiculo veiculo) {
        String placa = veiculo.getPlaca();

        // Checar se o veiculo existe antes de excluir
        if (listaVeiculos.contains(veiculo)) {
            listaVeiculos.remove(veiculo);
            System.out.printf("Veiculo de placa %s removido!\n", placa);
            return;
        }

        System.out.printf("Veiculo invalido. Nao foi possivel remover o veiculo de placa %s.\n", placa);
    }

    // Excluir veiculo com scanner
    public void excluirVeiculo(Scanner scanner) {
        System.out.print("Insira a placa do veiculo que deseja excluir: ");
        String placa = scanner.nextLine();

        for (Veiculo veiculo: listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                listaVeiculos.remove(veiculo);
                System.out.printf("Veiculo de placa %s removido!\n", placa);
                return;
            }
        }

        System.out.printf("Veiculo invalido. Nao foi possivel remover o veiculo de placa %s.\n", placa);
    }
    

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}