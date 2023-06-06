package pacote;

import java.util.StringJoiner;

public class SeguroPJ extends Seguro {
    // Atributos
    private Frota frota;
    private ClientePJ cliente;

    // Construtor
    public SeguroPJ(Frota frota, ClientePJ cliente,
                    int id, String dataInicio, String dataFim, Seguradora seguradora) {

        super(id, dataInicio, dataFim, seguradora);

        this.frota = frota;
        this.cliente = cliente;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(super.toString());
        joiner.add("Frota: ");
        joiner.add("    " + getFrota());
        joiner.add("Cliente: ");
        joiner.add(String.format("    %s (CNPJ: %s)",
                                 getCliente().getNome(), getCliente().getCNPJ()));

        return joiner.toString();
    }

    // Calcular valor mensal
    public void calcularValorMensal() {
        return;
    }


    // Getters e Setters
    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return this.cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }
}