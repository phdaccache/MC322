package pacote;

import java.util.StringJoiner;

public class SeguroPF extends Seguro {
    // Atributos
    private Veiculo veiculo;

    // Construtor
    public SeguroPF(Veiculo veiculo, ClientePF cliente, String dataInicio,
                    String dataFim, Seguradora seguradora) {

        super(dataInicio, dataFim, seguradora, cliente);

        this.veiculo = veiculo;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(super.toString());
        joiner.add("Veiculo: ");
        joiner.add("    " + getVeiculo());
        joiner.add("Cliente: ");
        joiner.add(String.format("    %s (CPF: %s)",
                                 getCliente().getNome(), getCliente().getDocumento()[1]));

        return joiner.toString();
    }

    // Calcular valor mensal
    public double calcularValorMensal() {
        return 0;
    }


    // Getters e Setters
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}