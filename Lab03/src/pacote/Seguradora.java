package pacote;

import java.util.ArrayList;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;

    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)){
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    public boolean removerCliente(String nome) {
        int i = 0;
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nome)) {
                listaClientes.remove(i);
                return true;
            }
            i++;
        }

        return false;
    }

    public void listarClientes(String tipoCliente) {
        for (Cliente cliente: listaClientes){
            if (tipoCliente.equals(cliente.getTipo())){
                System.out.println(cliente.toString());
            }
        }
    }

    public void listarSinistros() {
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados.");
        } else {
            for (Sinistro sinistro: listaSinistros) {
                System.out.println(sinistro.toString());
            }
        }
    }

    public boolean gerarSinistro(String data, String nomeCliente, String endereco, String placaVeiculo) {
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nomeCliente)) {
                for (Veiculo veiculo: cliente.getListaVeiculos()) {
                    if (veiculo.getPlaca().equals(placaVeiculo)) {
                        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
                        if (listaSinistros.contains(sinistro)) {
                            return false;
                        }
                        listaSinistros.add(sinistro);
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean visualizarSinistro(String nomeCliente) {
        for (Sinistro sinistro: listaSinistros) {
            if (nomeCliente.equals(sinistro.getCliente().getNome())) {
                System.out.println("Sinistro:");
                System.out.println(sinistro);
                return true;
            }
        }

        System.out.printf("O cliente %s nao tem nenhum sinistro", nomeCliente);
        return false;
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
