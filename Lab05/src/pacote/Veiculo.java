package pacote;

import java.util.StringJoiner;

public class Veiculo {
    // Atributos
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;
    private Seguro seguro;

    // Construtor
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.seguro = null;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Placa: " + getPlaca());
        joiner.add("Marca: " + getMarca());
        joiner.add("Modelo: " + getModelo());
        joiner.add("Ano de Fabricacao: " + getAnoFabricacao());
        if (getSeguro() == null)
            joiner.add("Seguro: Nao possui.");
        else {
        joiner.add(String.format("Seguro %03d: %s - %s", getSeguro().getId(),
                                getSeguro().getDataInicio(), getSeguro().getDataFim()));
        }
        return joiner.toString();
    }


    // Getters e Setters
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabricacao() {
        return this.anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
}