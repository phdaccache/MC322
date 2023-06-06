package pacote;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public abstract class Cliente {
    // Atributos
    private String nome;    
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private ArrayList<Sinistro> listaSinistros;
    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<>();
        this.listaSinistros = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Endereco: " + getEndereco());
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
        joiner.add(String.format("Valor do seguro: R$ %.2f", getValorSeguro()));

        return joiner.toString();
    }

    // Imprime os dados do cliente pelo toString()
    public void visualizarDados() {
        System.out.println("Dados do cliente:");
        System.out.println(toString());
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
        Seguradora seguradora = null;

        // Caso em que a placa passada e invalida
        if (!Validacao.validarPlaca(veiculo.getPlaca())) {
            System.out.println("Placa invalida. Nao foi possivel cadastrar o veiculo.");
            return;
        }

        listaVeiculos.add(veiculo); // Veiculo adicionado

        // 'Gambiarra' para pegar a seguradora que o cliente esta sem ter que armazenar a seguradora no objeto Cliente:
        for (Seguradora seg : Admin.listaSeguradoras) {
            if (seg.getListaClientes().contains(this)) {
                seguradora = seg;
            }
        }
        // Mudanca do valor do seguro
        if (seguradora != null) {
            double valorSeguro = seguradora.calcularPrecoSeguroCliente(this);
            setValorSeguro(valorSeguro);
        }

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

    // Listar todos os sinistros do cliente
    public void listarSinistros() {
        // Caso em que nao ha sinistros
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros cadastrados.");
            return;
        }
        System.out.println("Sinistros:");
        // Iterando sobre os sinistros
        for (Sinistro sinistro: listaSinistros) {
            System.out.println("---------------------------------------------");
            System.out.println(sinistro);
        }
        System.out.println("---------------------------------------------");
    }

    // Calcula score (sera sobrescrito por PF e PJ)
    public double calcularScore() {
        return CalcSeguro.VALOR_BASE.getValor();
    }


    // Getters e Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public double getValorSeguro() {
        return this.valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}