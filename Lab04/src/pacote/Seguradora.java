package pacote;

import java.util.ArrayList;
import java.util.Scanner;
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
        if (listaClientes.isEmpty()) {
            System.out.println("Sem clientes cadastrados");
            return;
        }

        System.out.println("Pessoas Juridicas:");
        for (Cliente cliente : listaClientes) {
            if (cliente.getClass().getSimpleName().equals("ClientePJ")) {
                System.out.println("---------------------------------------------");
                System.out.println(cliente);
            }
        }
        System.out.println("---------------------------------------------");

        System.out.println("");

        System.out.println("Pessoas Fisicas:");
        for (Cliente cliente : listaClientes) {
            if (cliente.getClass().getSimpleName().equals("ClientePF")) {
                System.out.println("---------------------------------------------");
                System.out.println(cliente);
            }
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo cliente automatico
    public void cadastrarCliente(Cliente cliente) {
        ClientePJ clientePJ;
        ClientePF clientePF;
        
        if (cliente.getClass().getSimpleName().equals("ClientePJ")) {
            clientePJ = (ClientePJ)cliente;
            if (!Validacao.validarCNPJ(clientePJ.getCNPJ())) {
                System.out.println("CNPJ invalido. Nao foi possivel cadastrar o cliente");
                return;
            }
        } else if (cliente.getClass().getSimpleName().equals("ClientePF")) {
            clientePF = (ClientePF)cliente;
            if (!Validacao.validarCPF(clientePF.getCPF())) {
                System.out.println("CPF invalido. Nao foi possivel cadastrar o cliente");
                return;
            }
        }
        if (!Validacao.validarNome(cliente.getNome())) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o cliente");
            return;
        }

        listaClientes.add(cliente);
        double valorSeguro = calcularPrecoSeguroCliente(cliente);
        cliente.setValorSeguro(valorSeguro);
    }

    // Cadastrar novo cliente com scanner
    public void cadastrarCliente(Scanner scanner) {
        return;
    }

    // Excluir cliente
    public void excluirCliente(Scanner scanner) {
        return;
    }

    // Listar todos os sinistros da seguradora por cliente
    public void listarSinistros() {
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados");
            return;
        }

        System.out.println("Sinistros:");
        for (Sinistro sinistro : listaSinistros) {
            System.out.println("---------------------------------------------");
            System.out.println(sinistro);
        }
        System.out.println("---------------------------------------------");
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String nomeCliente, String endereco, String placaVeiculo) {
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nomeCliente)) {
                for (Veiculo veiculo: cliente.getListaVeiculos()) {
                    if (veiculo.getPlaca().equals(placaVeiculo)) {
                        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
                        if (listaSinistros.contains(sinistro)) {
                            System.out.println("Sinistro ja adicionado.");
                            return;
                        }
                        listaSinistros.add(sinistro);
                        cliente.getListaSinistros().add(sinistro);
                        cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
                        System.out.println("Sinistro adicionado!");
                        return;
                    }
                }
            }
        }

        System.out.println("Nao foi possivel gerar o sinistro.");
    }

    // Gerar novo sinistro com scanner
    public void gerarSinistro(Scanner scanner) {
        return;
    }

    // Excluir sinistro
    public void excluirSinistro(Scanner scanner) {
        return;
    }

    // Listar veiculos por cliente
    public void listarVeiculos() {
        visualizarSinistro(null);
        return;
    }

    // Trasnferir seguro
    public void transferirSeguro(Scanner scanner) {
        return;
    }

    // Calcular receita
    public void calcularReceita() {
        double receita = 0;
        for (Cliente cliente : listaClientes) {
            receita += cliente.getValorSeguro();
        }
        System.out.printf("Receita: R$ %.2f\n", receita);
    }

    // Calcular preco do seguro do cliente
    public double calcularPrecoSeguroCliente(Cliente cliente) {
        double score = cliente.calcularScore();
        int qtdSinistros = cliente.getListaSinistros().size();
        return score * (1 + qtdSinistros);
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