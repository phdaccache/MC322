package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ClientePJ extends Cliente {
    // Atributos
    private final String CNPJ;
    private LocalDate dataFundacao;
    int qtdFuncionarios;

    // Construtor
    public ClientePJ(String nome, String endereco, String CNPJ, String dataFundacao, int qtdFuncionarios) {
        super(nome, endereco);
        this.CNPJ = CNPJ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataFundacao = LocalDate.parse(dataFundacao, dtf); // Tranformando String em LocalDate

        this.qtdFuncionarios = qtdFuncionarios;
    }

    public String toString() {
        super.toString();

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("CNPJ: " + getCNPJ());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFundacaoString = getDataFundacao().format(dtf); // Tranformando LocalDate em String
        joiner.add("Data Fundacao: " + dataFundacaoString);

        joiner.add("Quantidade de funcionarios: " + qtdFuncionarios);

        return joiner.toString();
    }

    // Calcula score
    public double calculaScore() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int qtdCarros = getListaVeiculos().size();
        return valorBase * (1 + (qtdFuncionarios/100)) * qtdCarros;
    }


    // Getters e Setters
    public String getCNPJ() {
        return this.CNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return this.qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }
}