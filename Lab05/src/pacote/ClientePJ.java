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
        joiner.add("Quantidade de frotas: " + getListaFrotas().size());
        joiner.add("Quantidade de seguros: " + getListaSeguros().size());

        return joiner.toString();
    }

    // Listar todas as frotas
    public void listarFrotas() {
        // Caso em que nao ha frotas cadastradas
        if (listaFrotas.isEmpty()) {
            System.out.println("Nao ha frotas cadastradas.");
            return;
        }
        System.out.println("Frotas: ");
        for (Frota frota : listaFrotas) {
            System.out.printf("    * Frota %03d: %d veiculo(s)\n",
                                frota.getId(), frota.getListaVeiculos().size());
        }
    }

    // Visualizar unica frota (com mais detalhes do que a listagem normal) automatico
    public void visualizarFrota(int id) {
        // Caso em que a frota nao existe
        if (id > listaFrotas.size() || id < 1) {
            System.out.println("---------------------------------------------");
            System.out.printf("Frota de ID %03d nao encontrada.\n", id);
            System.out.println("---------------------------------------------");
            return;
        }
        Frota frota = listaFrotas.get(id - 1);
        System.out.println("---------------------------------------------");
        System.out.println(frota);
        System.out.println("---------------------------------------------");
    }

    // Visualizar unica frota (com mais detalhes do que a listagem normal) com scanner
    public void visualizarFrota(Scanner scanner) {
        System.out.print("Digite o ID da frota que deseja visualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        visualizarFrota(id);
    }

    // Cadastrar frota automatico
    public void cadastrarFrota(Frota frota) {
        listaFrotas.add(frota);
        System.out.println("Frota cadastrada!");
    }

    // Cadastrar frota com scanner
    public void cadastrarFrota(Scanner scanner) {
        int id = listaFrotas.size() + 1;
        Frota frota = new Frota(id);
        System.out.println("Para cadastrar uma frota, e necessario cadastrar pelo menos um veiculo:");
        frota.cadastrarVeiculo(scanner);
        cadastrarFrota(frota);
    }

    // Atualizar frota automatico
    public void atualizarFrota(int id, ArrayList<Veiculo> veiculos) {
        // Caso em que a frota nao existe
        if (id > listaFrotas.size() || id < 1) {
            System.out.println("---------------------------------------------");
            System.out.printf("Frota de ID %03d nao encontrada.\n", id);
            System.out.println("---------------------------------------------");
            return;
        }
        Frota frota = listaFrotas.get(id - 1);
        // Caso em que os veiculos sao nulos (remover frota inteira)
        if (veiculos.isEmpty()) {
            removerFrota(frota);
            return;
        }
        frota.setListaVeiculos(veiculos);
    }

    // Atualizar frota com scanner
    public void atualizarFrota(Scanner scanner) {
        // Adicionar veiculos?
        // Remover veiculos?
        // Remover frota?
    }

    // Remover frota
    public void removerFrota(Frota frota) {
        // Excluir seguro associado à frota no cliente
        for (Seguro seguro : getListaSeguros()) {
            if (((SeguroPJ)seguro).getFrota().equals(frota)) {
                getListaSeguros().remove(seguro);
            }
        }
        // Excluir seguro associado à frota na seguradora
        for (Seguro seguro : getSeguradora().getListaSeguros()) {
            if (((SeguroPJ)seguro).getFrota().equals(frota)) {
                getSeguradora().getListaSeguros().remove(seguro);
            }
        }
        // Excluir frota no cliente
        listaFrotas.remove(frota);
    }

    // Retorna o documento do cliente
    public String[] getDocumento() {
        return new String [] {"CNPJ", this.CNPJ};
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