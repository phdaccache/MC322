package pacote;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Cliente {
    // Atributos
    private String nome;    
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Veiculos: ");
        for (int i = 0; i < listaVeiculos.size(); i++) {
            Veiculo veiculo = listaVeiculos.get(i);
            joiner.add(String.format("    Carro %d - %s - %s.",
                                    i+1, veiculo.getModelo(), veiculo.getPlaca()));
        }

        return joiner.toString();
    }

    // Imprime os dados do cliente pelo toString()
    public void visualizarDados() {
        return;
    }

    // Listar todos os veiculos
    public void listarVeiculos() {
        return;
    }

    // Cadastrar novo veiculo
    public void cadastrarVeiculo() {
        return;
    }

    // Excluir veiculo
    public void excluirVeiculo() {
        return;
    }

    // Listar todos os sinistros do cliente
    public void listarSinistros() {
        return;
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

    public double getValorSeguro() {
        return this.valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }
}
