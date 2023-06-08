package pacote;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public abstract class Cliente {
    // Atributos
    private String nome;
    private String telefone;   
    private String endereco;
    private String email;
    private Seguradora seguradora;
    private double valorMensalTotal;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Condutor> listaCondutores;

    // Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.seguradora = null;
        this.valorMensalTotal = 0;
        this.listaSeguros = new ArrayList<>();
        this.listaCondutores = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Email: " + getEmail());
        joiner.add(String.format("Seguradora: %s (CNPJ: %s)",
                                getSeguradora().getNome(), getSeguradora().getCNPJ()));
        joiner.add(String.format("Valor mensal total: R$%.2f", getValorMensalTotal()));
        joiner.add("Seguros: ");
        if (listaSeguros.isEmpty()) {
            joiner.add("    Nao ha seguros.");
        } else {
            for (Seguro seguro: listaSeguros) {
                joiner.add(String.format("Seguro %03d: %s - %s", seguro.getId(),
                                        seguro.getDataInicio(), seguro.getDataFim()));
            }
        }

        return joiner.toString();
    }

    // Imprime os dados do cliente pelo toString()
    public void visualizarDados() {
        System.out.println("Dados do cliente:");
        System.out.println(toString());
    }

    // Lista todos os seguros do cliente
    public void listarSeguros() {
        return;
    }

    // Visualizar unico seguro (com mais detalhes do que a listagem normal) automatico
    public void visualizarSeguro(int id) {
        return;
    }

    // Visualizar unico seguro (com mais detalhes do que a listagem normal) com scanner
    public void visualizarSeguro(Scanner scanner) {
        return;
    }

    // Adicionar seguro
    public void adicionarSeguro(Seguro seguro) {
        listaSeguros.add(seguro);
    }

    // Excluir seguro
    public void excluirSeguro(Seguro seguro) {
        listaSeguros.remove(seguro);
    }

    // Listar todos os condutores
    public void listarCondutores() {
        return;
    }

    // Cadastrar novo condutor automatico
    public void cadastrarCondutor(Condutor condutor) {
        return;
    }

    // Cadastrar novo condutor com scanner
    public void cadastrarCondutor(Scanner scanner) {
        return;
    }

    // Excluir condutor automatico
    public void excluirCondutor(String cpf) {
        return;
    }

    // Excluir condutor com scanner
    public void excluirCondutor(Scanner scanner) {
        return;
    }

    // Retorna o documento do cliente
    public abstract String[] getDocumento();


    // Getters e Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public double getValorMensalTotal() {
        return this.valorMensalTotal;
    }

    public void setValorMensalTotal(double valorMensalTotal) {
        this.valorMensalTotal = valorMensalTotal;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public ArrayList<Condutor> getListaCondutores() {
        return this.listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
}