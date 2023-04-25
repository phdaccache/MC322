package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ClientePJ extends Cliente {
    private final String CNPJ;
    private LocalDate dataFundacao;

    public ClientePJ(String nome, String endereco, String tipo, String CNPJ, String dataFundacao) {
        super(nome, endereco, tipo);
        this.CNPJ = CNPJ;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.dataFundacao = LocalDate.parse(dataFundacao, dtf);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("\nNome: " + getNome());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("CNPJ: " + getCNPJ());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFundacaoString = getDataFundacao().format(dtf);
        joiner.add("Data Fundacao: " + dataFundacaoString);

        joiner.add("Veiculos: ");
        if (getListaVeiculos().isEmpty()) {
            joiner.add("Sem veiculos cadastrados.");
        } else {
            for (Veiculo veiculo : getListaVeiculos()) {
                joiner.add(veiculo.toString());
            }
        }

        return joiner.toString();
    }

    public boolean validarCNPJ() {
        return validarID(CNPJ);
    }

    public String getCNPJ() {
        return this.CNPJ;
    }

    public LocalDate getDataFundacao() {
        return this.dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
}
