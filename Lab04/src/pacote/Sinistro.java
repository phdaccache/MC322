package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Sinistro {
    // Atributos
    private static int contador_id = 1;
    private final int ID;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = contador_id++;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        this.data = LocalDate.parse(data, dtf); // Tranformando String em LocalDate
        
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("ID: %03d", getID()));

		String dataString = getData().format(dtf); // Tranformando LocalDate em String
        joiner.add("Data: " + dataString);

        joiner.add("Endereco: " + getEndereco());
        joiner.add("Seguradora: " + getSeguradora().getNome());
        joiner.add(String.format("Veiculo: %s - %s",
                                getVeiculo().getModelo(), getVeiculo().getPlaca()));
        joiner.add("Cliente: " + getCliente().getNome());

        return joiner.toString();
    }


    // Getters e Setters
    public int getID() {
        return this.ID;
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

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}