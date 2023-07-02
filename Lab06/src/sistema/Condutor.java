package sistema;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Condutor {
    // Atributos
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private String dataNascimento;
    private ArrayList<Sinistro> listaSinistros;


    // Construtor
    public Condutor(String CPF, String nome, String telefone,
                    String endereco, String email, String dataNascimento) {

        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.listaSinistros = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("CPF: " + getCPF());
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Email: " + getEmail());
        joiner.add("Data Nascimento: " + getDataNascimento());

        return joiner.toString();
    }

    // Adicionar novo sinistro
    public void adicionarSinistro(Sinistro sinistro) {
        listaSinistros.add(sinistro);
    }

    // Remover sinistro
    public void removerSinistro(Sinistro sinistro) {
        listaSinistros.remove(sinistro);
    }

    // Getters e Setters
    public String getCPF() {
        return this.CPF;
    }

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

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return this.listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
}