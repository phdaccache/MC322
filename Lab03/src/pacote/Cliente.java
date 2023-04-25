package pacote;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Cliente {
    private String nome;    
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private String tipo;

    public Cliente(String nome, String endereco, String tipo) {
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<>();
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Veiculos: ");
        for (Veiculo veiculo : listaVeiculos) {
            joiner.add(veiculo.toString());
        }

        return joiner.toString();
    }

    // Função que retorna 'true' se os dígitos de um id forem todos iguais e 'false' caso contrário.
    private boolean digitosIguais(String id){
        char firstChar = id.charAt(0);
        for (int i = 1; i < id.length(); i++) {
            if (id.charAt(i) != firstChar) {
                return false;
            }
        }

        return true;
    }

    // Função que retorna 'true' se os dígitos verificadores de um id estiverem corretos e 'false' caso contrário.
    private boolean digitosVerificadores(String id){
        int num, r1, r2, d1, d2;
        int sum1 = 0, sum2 = 0;

        // Primeiro dígito verificador
        // Soma com pesos
        for (int i = 0, j = 10; i < id.length() - 2; i++, j--) {
            num = (int)(id.charAt(i) - 48);
            sum1 += num * j;
        }

        // Cálculo do dígito verificador
        r1 = sum1 % 11;
        if (r1 == 0 || r1 == 1) {
            d1 = 0;
        } else {
            d1 = 11 - r1;
        }

        // Comparação do dígito calculado com o dígito passado
        if (d1 != (int)(id.charAt(9) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        // Segundo dígito verificador
        // Soma com pesos
        for (int i = 1, j = 10; i < id.length() - 1; i++, j--) {
            num = (int)(id.charAt(i) - 48); // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            sum2 += num * j;
        }

        // Cálculo do dígito verificador
        r2 = sum2 % 11;
        if (r2 == 0 || r2 == 1) {
            d2 = 0;
        } else {
            d2 = 11 - r2;
        }

        // Comparação do dígito calculado com o dígito passado
        if (d2 != (int)(id.charAt(10) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        return true;
    }

    protected boolean validarID(String id_original){ 
        String id_num = id_original.replaceAll("[^0-9]", ""); // Retira todos os caracteres não numéricos
        int tam = id_num.length();

        if (tam != 11) {
            return false;
        }
        if (digitosIguais(id_num)) {
            return false;
        }
        if (!digitosVerificadores(id_num)) {
            return false;
        }

        return true;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
