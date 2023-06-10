package pacote;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class ClientePJ extends Cliente {
    // Atributos
    private final String CNPJ;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;
    private ArrayList<Frota> listaFrotas;

    // Construtor
    public ClientePJ(String nome, String telefone,
                    String endereco, String email, String CNPJ,
                    String dataFundacao, int qtdFuncionarios) {

        super(nome, telefone, endereco, email);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        this.CNPJ = CNPJ;
        this.dataFundacao = LocalDate.parse(dataFundacao, dtf); // Transformando String em LocalDate
        this.qtdFuncionarios = qtdFuncionarios;
        this.listaFrotas = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataFundacaoString = getDataFundacao().format(dtf); // Transformando LocalDate em String
        joiner.add(super.toString());
        joiner.add("CNPJ: " + getCNPJ());
        joiner.add("Data Fundacao: " + dataFundacaoString);
        joiner.add("Quantidade de funcionarios: " + qtdFuncionarios);
        joiner.add("Frotas: ");
        if (listaFrotas.isEmpty()) {
            joiner.add("    * Sem frotas cadastradas.");
        } else {
            for (Frota frota : listaFrotas) {
                joiner.add(String.format("    * Frota %03d: %d veiculos",
                                        frota.getId(), frota.getListaVeiculos().size()));
            }
        }
        joiner.add("Seguros: ");
        if (getListaSeguros().isEmpty()) {
            joiner.add("    * Nao ha seguros.");
        } else {
            for (Seguro seguro: getListaSeguros()) {
                joiner.add(String.format("    * Seguro %03d: %s - %s", seguro.getId(),
                                        seguro.getDataInicio(), seguro.getDataFim()));
            }
        }

        return joiner.toString();
    }

    // Listar todas as frotas
    public void listarFrotas() {
        return;
    }

    // Visualizar unica frota (com mais detalhes do que a listagem normal) automatico
    public void visualizarFrota(int id) {
        return;
    }

    // Visualizar unica frota (com mais detalhes do que a listagem normal) com scanner
    public void visualizarFrota(Scanner scanner) {
        return;
    }

    // Cadastrar frota automatico
    public void cadastrarFrota(Frota frota) {
        return;
    }

    // Cadastrar frota com scanner
    public void cadastrarFrota(Scanner scanner) {
        return;
    }

    // Atualizar frota automatico
    public void atualizarFrota(int id, ArrayList<Veiculo> veiculos) {
        return;
    }

    // Atualizar frota com scanner
    public void atualizarFrota(Scanner scanner) {
        return;
    }

    // Retorna o documento do cliente
    public String[] getDocumento() {
        return new String [] {"CNPJ", this.CNPJ};
    }

    // Retorna a frota do cliente pelo id
    public Frota getFrota(int id) {
        for (Frota frota : listaFrotas) {
            if (frota.getId() == id) {
                return frota;
            }
        }
        return null;
    }

    // Retorna a quantidade de anos pos fundacao
    public int getAnosPosFundacao() {
        LocalDate then = dataFundacao;
        LocalDate now = LocalDate.now();
        return Period.between(then, now).getYears();
    }

    // Retorna todos os veiculos de uma frota
    public ArrayList<Veiculo> getVeiculosPorFrota(int id) {
        return listaFrotas.get(id - 1).getListaVeiculos();
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

    public ArrayList<Frota> getListaFrotas() {
        return this.listaFrotas;
    }

    public void setListaFrotas(ArrayList<Frota> listaFrotas) {
        this.listaFrotas = listaFrotas;
    }
}