package pacote;

import java.util.ArrayList;
import java.util.StringJoiner;

public class Frota {
    // Atributos
    private final int id;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public Frota(int id) {
        this.id = id;
        this.listaVeiculos = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add(String.format("ID: %03d", getId()));
        joiner.add("Veiculos: ");
        if (listaVeiculos.isEmpty()) {
            joiner.add("    Sem veiculos cadastrados.");
        } else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                Veiculo veiculo = listaVeiculos.get(i);
                joiner.add(String.format("    Carro %d: %s - %s",
                                        i+1, veiculo.getModelo(), veiculo.getPlaca()));
            }
        }

        return joiner.toString();
    }

    // Getters e Setters
    public int getId() {
        return this.id;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}