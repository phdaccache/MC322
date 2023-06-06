package pacote;

import java.util.StringJoiner;

public class SeguroPF extends Seguro {
    // Atributos
    private Veiculo veiculo;
    private ClientePF cliente;

    // Construtor
    public SeguroPF(Veiculo veiculo, ClientePF cliente,
                    int id, String dataInicio, String dataFim, Seguradora seguradora) {

        super(id, dataInicio, dataFim, seguradora);

        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(super.toString());
        joiner.add("Veiculo: ");
        joiner.add("    " + getVeiculo());
        joiner.add("Cliente: ");
        joiner.add(String.format("    %s (CPF: %s)",
                                 getCliente().getNome(), getCliente().getCPF()));

        return joiner.toString();
    }

    // Calcular valor mensal
    public void calcularValorMensal() {
        return;
    }


    // Getters e Setters
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public ClientePF getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }
}