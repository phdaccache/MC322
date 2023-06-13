package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Sinistro {
    // Atributos
    private static int contador_id = 1;
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    // Construtor
    public Sinistro(String data, String endereco, Condutor condutor, Seguro seguro) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.id = contador_id++;
        this.data = LocalDate.parse(data, dtf); // Transformando String em LocalDate
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }
    
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = getData().format(dtf); // Transformando LocalDate em String
        String dataInicioString = getSeguro().getDataInicio().format(dtf); // Transformando LocalDate em String
        String dataFimString = getSeguro().getDataFim().format(dtf); // Transformando LocalDate em String
        joiner.add(String.format("ID: %03d", getId()));
        joiner.add("Data: " + dataString);
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Condutor: " + getCondutor().getNome());
        joiner.add(String.format("Seguro %03d: %s - %s", getSeguro().getId(),
                                dataInicioString, dataFimString));

        return joiner.toString();
    }


    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public LocalDate getData() {
        return this.data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return this.condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return this.seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }
}