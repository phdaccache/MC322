package pacote;

import java.util.ArrayList;

public class Admin {
    // Atributo
    private ArrayList<Seguradora> listaSeguradoras;

    // Construtor
    public Admin(ArrayList<Seguradora> listaSeguradoras) {
        this.listaSeguradoras = listaSeguradoras;
    }

    // Cadastrar nova seguradora
    public void cadastrarSeguradora() {
        return;
    }

    // Excluir seguradora
    public void excluirSeguradora() {
        return;
    }

    // Listar clientes por seguradora
    public void listarClientes() {
        return;
    }

    // Listar sinistros por seguradora
    public void listarSinistros() {
        return;
    }

    // Listar veiculos por seguradora
    public void listarVeiculos() {
        return;
    }


    // Getter e Setter
    public ArrayList<Seguradora> getListaSeguradoras() {
        return this.listaSeguradoras;
    }

    public void setListaSeguradoras(ArrayList<Seguradora> listaSeguradoras) {
        this.listaSeguradoras = listaSeguradoras;
    }
}