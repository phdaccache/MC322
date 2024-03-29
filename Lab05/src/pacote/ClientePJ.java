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
        joiner.add("Quantidade de Funcionarios: " + qtdFuncionarios);
        joiner.add("Quantidade de Frotas: " + getListaFrotas().size());
        joiner.add("Quantidade de Seguros: " + getListaSeguros().size());

        return joiner.toString();
    }

    // Listar todas as frotas
    public void listarFrotas() {
        System.out.println("Frotas: ");

        // Caso em que nao ha frotas cadastradas
        if (listaFrotas.isEmpty()) {
            System.out.println("    * Nao ha frotas cadastradas.");
            return;
        }
        // Iterando sobre as frotas
        for (Frota frota : listaFrotas) {
            System.out.println("---------------------------------------------");
            System.out.println(frota);
        }
        System.out.println("---------------------------------------------");
    }

    // Visualizar unica frota (com mais detalhes do que a listagem normal) automatico
    public void visualizarFrota(int id) {
        // Caso em que a frota nao existe
        if (id > listaFrotas.size() || id < 1) {
            System.out.printf("Frota de ID %03d nao encontrada.\n", id);
            return;
        }

        Frota frota = listaFrotas.get(id - 1);
        System.out.println("---------------------------------------------");
        System.out.println(frota);

        // Listando veiculos
        System.out.println("Veiculos: ");
        if (frota.getListaVeiculos().isEmpty()) {
            System.out.println("    * Sem veiculos cadastrados.");
        } else {
            for (int i = 0; i < frota.getListaVeiculos().size(); i++) {
                Veiculo veiculo = frota.getListaVeiculos().get(i);
                System.out.println(String.format("    * Carro %d: %s - %s",
                                        i+1, veiculo.getModelo(), veiculo.getPlaca()));
            }
        }
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
        // Checando se foi passado uma frota
        if (frota == null) {
            System.out.println("Nao foi possivel cadastrar a frota.");
            return;
        }

        // Adicionando frota
        listaFrotas.add(frota);

        // Atualizar valor dos seguros
        for (Seguro seguro: getListaSeguros()) {
            seguro.setValorMensal(seguro.calcularValorMensal());
        }
        // Atualizar valor mensal total
        setValorMensalTotal(calcularValorMensalTotal());
        
        System.out.printf("Frota cadastrada! ID da Frota: %03d.\n", frota.getId());
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
    public void atualizarFrota(int id, ArrayList<Veiculo> vAdd, ArrayList<Veiculo> vDel) {
        // Caso em que a frota nao existe
        if (id > listaFrotas.size() || id < 1) {
            System.out.printf("Frota de ID %03d nao encontrada.\n", id);
            return;
        }
        Frota frota = listaFrotas.get(id - 1);
        // Adicionar veiculos
        for (Veiculo veiculo : vAdd) {
            frota.cadastrarVeiculo(veiculo);
        }
        // Remover veiculos
        for (Veiculo veiculo : vDel) {
            frota.excluirVeiculo(veiculo);
        }
        // Caso em que os veiculos sao nulos (remover frota inteira)
        if (frota.getListaVeiculos().isEmpty()) {
            removerFrota(frota);
        }
        
        // Atualizar valor dos seguros
        for (Seguro seguro: getListaSeguros()) {
            seguro.setValorMensal(seguro.calcularValorMensal());
        }
        // Atualizar valor mensal total
        setValorMensalTotal(calcularValorMensalTotal());

        System.out.printf("Frota de ID %03d atualizada!\n", id);
    }

    // Atualizar frota com scanner
    public void atualizarFrota(Scanner scanner) {
        System.out.print("Digite o ID da frota que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        // Caso em que a frota nao existe
        if (id > listaFrotas.size() || id < 1) {
            System.out.printf("Frota de ID %03d nao encontrada.\n", id);
            return;
        }

        Frota frota = listaFrotas.get(id - 1);

        int op;
        do {
            System.out.println("\n################### Opcoes ##################");
            System.out.println("|-------------------------------------------|");
            System.out.println("| Opcao 1 - Cadastrar Veiculo               |");
            System.out.println("| Opcao 2 - Remover Veiculo                 |");
            System.out.println("| Opcao 3 - Remover Frota                   |");
            System.out.println("| Opcao 0 - Voltar                          |");
            System.out.println("|-------------------------------------------|\n");
            System.out.print("Digite uma opcao: ");
            op = scanner.nextInt();
            scanner.nextLine();
            
            switch (op) {
                case 1:
                    frota.cadastrarVeiculo(scanner);
                    break;
                case 2:
                    frota.excluirVeiculo(scanner);
                    if (frota.getListaVeiculos().isEmpty()) {
                        removerFrota(frota);
                    }
                    break;
                case 3:
                    removerFrota(frota);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        } while (op != 0);

        // Atualizar valor dos seguros
        for (Seguro seguro: getListaSeguros()) {
            seguro.setValorMensal(seguro.calcularValorMensal());
        }
        // Atualizar valor mensal total
        setValorMensalTotal(calcularValorMensalTotal());

        System.out.printf("Frota de ID %03d atualizada!\n", id);
    }

    // Excluir seguro
    public void excluirSeguro(Seguro seguro) {
        // Checando se foi passado um seguro
        if (seguro == null) {
            System.out.println("Nao foi possivel excluir o seguro.");
            return;
        }

        // Checando se o seguro pertence ao cliente
        if (!getListaSeguros().contains(seguro)) {
            System.out.println("Nao foi possivel excluir o seguro.");
            return;
        }

        // Removendo o seguro da lista de seguros do cleinte
        super.excluirSeguro(seguro);
        // Removendo o seguro da frota
        for (Frota frota: listaFrotas) {
            if (((SeguroPJ)seguro).getFrota().equals(frota)) {
                frota.setSeguro(null);
            }
        }
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

    // Remover frota
    private void removerFrota(Frota frota) {
        int id = frota.getId();
        // Excluir seguro associado à frota no cliente
        for (Seguro seguro : getListaSeguros()) {
            if (((SeguroPJ)seguro).getFrota().equals(frota)) {
                getListaSeguros().remove(seguro);
                break;
            }
        }
        // Excluir seguro associado à frota na seguradora
        for (Seguro seguro : getSeguradora().getListaSeguros()) {
            if (seguro instanceof SeguroPJ && ((SeguroPJ)seguro).getFrota().equals(frota)) {
                getSeguradora().getListaSeguros().remove(seguro);
                break;
            }
        }
        // Excluir frota no cliente
        listaFrotas.remove(frota);
        System.out.printf("Frota de ID %03d removida!\n", id);
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