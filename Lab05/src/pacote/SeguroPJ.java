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
        joiner.add(String.format("    * Frota %03d: %d veiculo(s)\n", frota.getId(), frota.getListaVeiculos().size()));
        joiner.add("Cliente: ");
        joiner.add(String.format("    * %s (CNPJ: %s)", getCliente().getNome(), getCliente().getDocumento()[1]));

        return joiner.toString();
    }

    // Calcular valor mensal
    public double calcularValorMensal() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int qtdFuncionarios = ((ClientePJ)getCliente()).getQtdFuncionarios();
        int qtdVeiculos = ((ClientePF)getCliente()).getListaVeiculos().size();
        int anosPosFundacao = ((ClientePJ)getCliente()).getAnosPosFundacao();
        int qtdSinistrosSeguro = getListaSinistros().size();
        int qtdSinistrosCliente = 0;

        for (Seguro seguro : getCliente().getListaSeguros()) {
            qtdSinistrosCliente += seguro.getListaSinistros().size();
        }

        double valor = (valorBase * (10 + (qtdFuncionarios/10)) *
                        (1 + (1/(qtdVeiculos + 2))) * (1 + (1/(anosPosFundacao + 2))) * 
                        (2 + (qtdSinistrosCliente/10)) * (5 + (qtdSinistrosSeguro/10)));
        
        return valor;
    }


    // Getters e Setters
    public Frota getFrota() {
        return this.frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }
}