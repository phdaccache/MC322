public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;

    // Construtor
    public Veiculo(String placa, String marca, String modelo) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
    }

    public String toString() {
        return String.format("Placa: %s.\nMarca: %s.\nModelo: %s.\n", getPlaca(), getMarca(), getModelo());
    }

    // Getters e Setters
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
}
