package pacote;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Seguradora {
    // Atributos
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Email: " + getEmail());
        joiner.add("Endereco: " + getEndereco());

        return joiner.toString();
    }

    // Imprime os dados da seguradora pelo toString()
    public void visualizarDados() {
        return;
    }

    // Listar todos os clientes
    public void listarClientes() {
        return;
    }

    // Cadastrar novo cliente
    public void cadastrarCliente(Cliente cliente) {
        return;
    }

    // Excluir cliente
    public void excluirCliente(String nome) {
        return;
    }

    // Listar todos os sinistros da seguradora por cliente
    public void listarSinistros() {
        return;
    }

    // Gerar novo sinistro
    public void gerarSinistro() {
        return;
    }

    // Excluir sinistro
    public void excluirSinistro() {
        return;
    }

    // Listar veiculos por cliente
    public void listarVeiculos() {
        visualizarSinistro(null);
        return;
    }

    // Trasnferir seguro
    public void transferirSeguro() {
        return;
    }

    // Calcular receita
    public void calcularReceita() {
        return;
    }

    // Visualizar unico sinistro
    private void visualizarSinistro(Sinistro sinistro) {
        return;
    }


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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}