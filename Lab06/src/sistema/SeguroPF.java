package sistema;

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

    public SeguroPF(int id, Veiculo veiculo, ClientePF cliente, String dataInicio,
                    String dataFim, Seguradora seguradora) {

        super(id, dataInicio, dataFim, seguradora, cliente);

        this.veiculo = veiculo;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");

        joiner.add(super.toString());
        joiner.add(String.format("Veiculo: %s - %s", getVeiculo().getModelo(), getVeiculo().getPlaca()));
        joiner.add(String.format("Cliente: %s (CPF: %s)", getCliente().getNome(), getCliente().getDocumento()[1]));

        return joiner.toString();
    }

    // Calcular valor mensal
    public double calcularValorMensal() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        double qtdVeiculos = ((ClientePF)getCliente()).getListaVeiculos().size();
        double qtdSinistrosSeguro = getListaSinistros().size();
        double idade = ((ClientePF)getCliente()).getIdade();
        double qtdSinistrosCliente = 0;
        double fatorIdade;

        for (Seguro seguro : getCliente().getListaSeguros()) {
            qtdSinistrosCliente += seguro.getListaSinistros().size();
        }

        if (idade >= 18 && idade < 30) {
            fatorIdade = CalcSeguro.FATOR_18_30.getValor();
        } else if (idade >= 30 && idade < 60) {
            fatorIdade = CalcSeguro.FATOR_30_60.getValor();
        } else if (idade >= 60) {
            fatorIdade = CalcSeguro.FATOR_60.getValor();
        } else {
            fatorIdade = 0;
        }

        double valor = (valorBase * fatorIdade * (1 + (1/(qtdVeiculos + 2))) * 
                        (2 + (qtdSinistrosCliente/10)) * (5 + (qtdSinistrosSeguro/10)));
        
        return valor;
    }


    // Getters e Setters
    public Veiculo getVeiculo() {
        return this.veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}