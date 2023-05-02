package pacote;

import java.util.ArrayList;
import java.util.StringJoiner;

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

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("---------------------------------");
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Email: " + getEmail());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("---------------------------------");

        return joiner.toString();
    }

    /*
     * Funcao que cadastra o cliente caso nao tenha algum
     * objeto cliente identico em listaClientes.
     * Retorna true se o cliente for novo.
     */
    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)){
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    /*
     * Funcao que remove o cliente.
     * Caso nao tenha nenhum cliente com o nome passado, retorna false.
     */
    public boolean removerCliente(String nome) {
        int i = 0; /* Index do cliente na listaClientes */
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nome)) {
                listaClientes.remove(i);
                return true;
            }
            i++;
        }

        return false;
    }

    /* 
     * Funcao que lista todos os clientes do tipo (PF ou PJ) passado.
     */
    public void listarClientes(String tipoCliente) {
        for (Cliente cliente: listaClientes){
            if (tipoCliente.equals(cliente.getTipo())){
                System.out.println(cliente.toString());
            }
        }
    }

    /*
     * Funcao que lista tidis os sinistros gerados
     */
    public void listarSinistros() {
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados.");
        } else {
            for (Sinistro sinistro: listaSinistros) {
                System.out.println(sinistro.toString());
            }
        }
    }

    /*
     * Funcao que gera um novo sinistro.
     * Retorna true se o nome do cliente esta na lista de clientes e
     * se a placa do veiculo passada esta na lista de veiculos do cliente.
     */
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

    /*
     * Funcao que printa os sinistros dado o nome do cliente.
     * Retorna true caso o cliente tenha pelo menos 1 sinistro cadastrado.
     */
    public boolean visualizarSinistro(String nomeCliente) {
        boolean tem_sinistros = false;
        for (Sinistro sinistro: listaSinistros) {
            if (nomeCliente.equals(sinistro.getCliente().getNome())) {
                System.out.println("\nSinistro:");
                System.out.println(sinistro.toString());
                tem_sinistros = true;
            }
        }

        if (tem_sinistros) {
            return true;
        }

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
