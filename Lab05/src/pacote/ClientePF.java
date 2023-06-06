package pacote;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class ClientePF extends Cliente{
    // Atributos
    private final String CPF;
    private String genero;
    private LocalDate dataLicenca;
    private String educacao;
    private LocalDate dataNascimento;
    private String classeEconomica;

    // Construtor
    public ClientePF(String nome, String endereco, String CPF,
                    String genero, String dataLicenca, String educacao,
                    String dataNascimento, String classeEconomica) {
                        
        super(nome, endereco);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        this.CPF = CPF;
        this.genero = genero;
        this.dataLicenca = LocalDate.parse(dataLicenca, dtf); // Tranformando String em LocalDate
        this.educacao = educacao;
        this.dataNascimento = LocalDate.parse(dataNascimento, dtf); // Tranformando String em LocalDate
        this.classeEconomica = classeEconomica;
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataLicencaString = getDataLicenca().format(dtf); // Tranformando LocalDate em String
        String dataNascimentoString = getDataNascimento().format(dtf); // Tranformando LocalDate em String
        joiner.add(super.toString());
        joiner.add("CPF: " + getCPF());
        joiner.add("Genero: " + getGenero());
        joiner.add("Data Licenca: " + dataLicencaString);
        joiner.add("Educacao: " + getEducacao());
        joiner.add("Data Nascimento: " + dataNascimentoString);
        joiner.add("Classe Economica: " + getClasseEconomica());

        return joiner.toString();
    }

    // Calcula score
    public double calcularScore() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor();
        int qtdCarros = getListaVeiculos().size();
        int idade = getIdade();
        double fatorIdade;

        if (idade >= 18 && idade < 30) {
            fatorIdade = CalcSeguro.FATOR_18_30.getValor();
        } else if (idade >= 30 && idade < 60) {
            fatorIdade = CalcSeguro.FATOR_30_60.getValor();
        } else if (idade >= 60 && idade < 90) {
            fatorIdade = CalcSeguro.FATOR_60_90.getValor();
        } else {
            fatorIdade = 0;
        }

        return valorBase * fatorIdade * qtdCarros;
    }

    // Calcula idade baseado na data de nascimento do cliente
    public int getIdade() {
        LocalDate then = dataNascimento;
        LocalDate now = LocalDate.now();
        return Period.between(then, now).getYears();
    }


    // Getters e Setters
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