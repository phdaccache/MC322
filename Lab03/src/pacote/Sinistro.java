package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Sinistro {
    private static int contador_id = 0;
    private final int ID;
    LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = gerarId();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(data, dtf);
        
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("---------------------------------");
        joiner.add(String.format("ID: %03d", getID()));

		String dataString = getData().format(dtf);
        joiner.add("Data: " + dataString);

        joiner.add("Endereco: " + getEndereco());
        joiner.add("Seguradora: \n" + getSeguradora().toString());
        joiner.add("Veiculo: \n" + getVeiculo().toString());
        joiner.add("\nCliente: " + getCliente().toString());
        joiner.add("---------------------------------");

        return joiner.toString();
    }

    // Função que gera ID único (começa em 0 e vai acumulando a cada novo objeto criado).
    public int gerarId(){
        contador_id += 1;
        return contador_id;
    }

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
