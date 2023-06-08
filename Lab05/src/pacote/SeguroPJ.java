package pacote;

import java.util.StringJoiner;

public class SeguroPJ extends Seguro {
    // Atributos
    private Frota frota;

    // Construtor
    public SeguroPJ(Frota frota, ClientePJ cliente, String dataInicio,
                    String dataFim, Seguradora seguradora) {

        super(dataInicio, dataFim, seguradora, cliente);

        this.frota = frota;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(super.toString());
        joiner.add("Frota: ");
        joiner.add("    " + getFrota());
        joiner.add("Cliente: ");
        joiner.add(String.format("    %s (CNPJ: %s)",
                                 getCliente().getNome(), getCliente().getDocumento()[1]));

        return joiner.toString();
    }

    // Calcular valor mensal
    public double calcularValorMensal() {
        return 0;
    }


    // Getters e Setters
    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }
}