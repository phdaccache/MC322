package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ClientePF extends Cliente {
    private final String CPF;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

    public ClientePF(String nome, String endereco, String tipo, String CPF,
                    String genero, String dataLicenca, String educacao,
                    String dataNascimento, String classeEconomica) {
                        
        super(nome, endereco, tipo);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        this.CPF = CPF;
        this.genero = genero;
        this.dataLicenca = LocalDate.parse(dataLicenca, dtf);
        this.educacao = educacao;
        this.dataNascimento = LocalDate.parse(dataNascimento, dtf);
        this.classeEconomica = classeEconomica;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("\n#################################");
        joiner.add("Nome: " + getNome());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("CPF: " + getCPF());
        joiner.add("Genero: " + getGenero());

		String dataLicencaString = getDataLicenca().format(dtf);
        joiner.add("Data Licenca: " + dataLicencaString);

        joiner.add("Educacao: " + getEducacao());

        String dataNascimentoString = getDataNascimento().format(dtf);
        joiner.add("Data Nascimento: " + dataNascimentoString);

        joiner.add("Classe Economica: " + getClasseEconomica());

        joiner.add("Veiculos: ");
        if (getListaVeiculos().isEmpty()) {
            joiner.add("Sem veiculos cadastrados.");
        } else {
            for (Veiculo veiculo : getListaVeiculos()) {
                joiner.add("---------------------------------");
                joiner.add(veiculo.toString());
            }

            joiner.add("---------------------------------"); 
        }

        joiner.add("#################################\n");

        return joiner.toString();
    }

    public boolean validarCPF() {
        return validarID(CPF);
    }

    public String getCPF() {
        return this.CPF;
    }


    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataLicenca() {
        return this.dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getClasseEconomica() {
        return this.classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }
}
