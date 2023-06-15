package pacote;

import java.time.format.DateTimeFormatter;
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
        if (getSeguro() == null)
            joiner.add("Seguro: Nao possui");
        else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataInicioString = getSeguro().getDataInicio().format(dtf); // Transformando LocalDate em String
            String dataFimString = getSeguro().getDataFim().format(dtf); // Transformando LocalDate em String
            joiner.add(String.format("Seguro %03d: %s - %s", getSeguro().getId(),
                                dataInicioString, dataFimString));
        }
        joiner.add("Quantidade de veiculos: " + getListaVeiculos().size());

        return joiner.toString();
    }

    // Listar todos os veiculos
    public void listarVeiculos() {
        System.out.println("Veiculos:");

        // Caso em que nao ha veiculos cadastrados
        if (listaVeiculos.isEmpty()) {
            System.out.println("    * Nao ha veiculos cadastrados.");
            return;
        }

        // Iterando sobre os veiculos
        for (Veiculo veiculo : listaVeiculos) {
            System.out.println("---------------------------------------------");
            System.out.println(veiculo);
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo veiculo automatico
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Checando se foi passado um veiculo
        if (veiculo == null) {
            System.out.println("Nao foi possivel cadastrar o veiculo.");
            return;
        }

        // Caso em que a placa passada e invalida
        if (!Validacao.validarPlaca(veiculo.getPlaca())) {
            System.out.println("Placa invalida. Nao foi possivel cadastrar o veiculo.");
            return;
        }

        // Caso em que o veiculo ja esta cadastrado
        for (Veiculo veiculoCadastrado : listaVeiculos) {
            if (veiculoCadastrado.getPlaca().equals(veiculo.getPlaca())) {
                System.out.println("Veiculo ja cadastrado.");
                return;
            }
        }

        listaVeiculos.add(veiculo); // Veiculo adicionado
        System.out.println("Veiculo cadastrado!");
        // Valor do seguro atualizado em atualizarFrota() em clientePJ
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
        // Checando se foi passado um veiculo
        if (veiculo == null) {
            System.out.println("Nao foi possivel remover o veiculo.");
            return;
        }

        String placa = veiculo.getPlaca();

        // Checando se o veiculo esta cadastrado
        if (!listaVeiculos.contains(veiculo)) {
            System.out.printf("Veiculo invalido. Nao foi possivel remover o veiculo de placa %s.\n", placa);
            return;
        }

        // Excluir veiculo na frota
        listaVeiculos.remove(veiculo);
        System.out.printf("Veiculo de placa %s removido!\n", placa);
    }

    // Excluir veiculo com scanner
    public void excluirVeiculo(Scanner scanner) {
        Veiculo veiculo = null;

        System.out.print("Insira a placa do veiculo que deseja excluir: ");
        String placa = scanner.nextLine();

        for (Veiculo veic: listaVeiculos) {
            if (veic.getPlaca().equals(placa)) {
                veiculo = veic;
            }
        }

        excluirVeiculo(veiculo);
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