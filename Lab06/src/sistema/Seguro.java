package sistema;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringJoiner;

public abstract class Seguro {
    // Atributos
    private static int contador_id;
    private final int id;
    private Cliente cliente;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private double valorMensal;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Condutor> listaCondutores;

    // Construtor
    public Seguro(String dataInicio, String dataFim, Seguradora seguradora, Cliente cliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Nao definir contador_id como 1 no comeco porque podem ter seguros carregados dos arquivos
        contador_id = seguradora.getListaSeguros().size() + 1;
        this.id = contador_id++;
        this.dataInicio = LocalDate.parse(dataInicio, dtf); // Transformando String em LocalDate
        this.dataFim = LocalDate.parse(dataFim, dtf); // Transformando String em LocalDate
        this.seguradora = seguradora;
        this.cliente = cliente;
        this.valorMensal = 0;
        this.listaSinistros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
    }

    public Seguro(int id, String dataInicio, String dataFim, Seguradora seguradora, Cliente cliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        this.id = id;
        this.dataInicio = LocalDate.parse(dataInicio, dtf); // Transformando String em LocalDate
        this.dataFim = LocalDate.parse(dataFim, dtf); // Transformando String em LocalDate
        this.seguradora = seguradora;
        this.cliente = cliente;
        this.valorMensal = 0;
        this.listaSinistros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataInicioString = getDataInicio().format(dtf); // Transformando LocalDate em String
        String dataFimString = getDataFim().format(dtf); // Transformando LocalDate em String
        joiner.add(String.format("ID: %03d", getId()));
        joiner.add("Data Inicio: " + dataInicioString);
        joiner.add("Data Fim: " + dataFimString);
        joiner.add(String.format("Seguradora: %s (CNPJ: %s)", getSeguradora().getNome(), getSeguradora().getCNPJ()));
        joiner.add(String.format("Valor Mensal: R$%.2f", getValorMensal()));
        joiner.add("Quantidade de Sinistros: " + getListaSinistros().size());
        joiner.add("Quantidade de Condutores: " + getListaCondutores().size());

        return joiner.toString();
    }

    // Autorizar novo condutor automatico
    public void autorizarCondutor(Condutor condutor) {
        listaCondutores.add(condutor);
    }

    // Desautorizar condutor automatico
    public void desautorizarCondutor(Condutor condutor) {
        listaCondutores.remove(condutor);
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String endereco, String cpfCondutor) {
        Condutor condutor = getCondutor(cpfCondutor);

        // Checando se o condutor existe
        if (condutor == null) {
            System.out.println("Condutor não encontrado.");
            return;
        }

        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);

        listaSinistros.add(sinistro);
        condutor.adicionarSinistro(sinistro);

        // Atualizar valor dos seguros
        for (Seguro seguro: getCliente().getListaSeguros()) {
            seguro.setValorMensal(seguro.calcularValorMensal());
        }
        // Atualizar valor mensal total
        getCliente().setValorMensalTotal(getCliente().calcularValorMensalTotal());

        System.out.printf("Sinistro gerado! ID do Sinistro: %03d.\n", sinistro.getId());
    }

    // Excluir sinistro automatico
    public void excluirSinistro(Sinistro sinistro) {
        // Checando se foi passado um sinistro
        if (sinistro == null) {
            System.out.println("Nao foi possivel excluir o sinistro.");
            return;
        }

        // Checando se o sinistro existe
        if (!listaSinistros.contains(sinistro)) {
            System.out.printf("Sinistro invalido. Nao foi possivel excluir o sinistro de ID %03d.\n", sinistro.getId());
            return;
        }

        // Remover sinistro do condutor
        sinistro.getCondutor().removerSinistro(sinistro);
        // Remover sinistro da lista de sinistros
        listaSinistros.remove(sinistro);

        // Atualizar valor dos seguros
        for (Seguro seguro: getCliente().getListaSeguros()) {
            seguro.setValorMensal(seguro.calcularValorMensal());
        }
        // Atualizar valor mensal total
        getCliente().setValorMensalTotal(getCliente().calcularValorMensalTotal());

        System.out.printf("Sinistro %03d excluido!\n", sinistro.getId());
    }

    // Calcular valor mensal
    public abstract double calcularValorMensal();

    public Condutor getCondutor(String cpf) {
        for (Condutor condutor : listaCondutores) {
            if (condutor.getCPF().equals(cpf)) {
                return condutor;
            }
        }
        return null;
    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public double getValorMensal() {
        return this.valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
}