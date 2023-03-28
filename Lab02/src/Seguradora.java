public class Seguradora {
    private String nome;
    private long telefone;
    private String email;
    private String endereco;

    // Construtor
    public Seguradora(String nome, long telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
    }

    public String toString(String nome, long telefone, String email, String endereco) {
        return String.format("Nome: %s.\nTelefone: %d.\nEmail: %s.\nEndereço: %s.", nome, telefone, email, endereco);
    }

    //Getters e Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getTelefone() {
        return this.telefone;
    }

    public void setTelefone(long telefone) {
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
}
