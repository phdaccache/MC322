public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private static int contador_id = 0;

    // Construtor
    public Sinistro(String data, String endereco) {
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
    }

    public String toString() {
        return String.format("ID: %03d.\nData: %s.\nEndereço: %s.\n", getId(), getData(), getEndereco());
    }

    public int gerarId(){
        contador_id += 1;
        return contador_id;
    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}
