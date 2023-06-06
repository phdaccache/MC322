package pacote;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Seguradora {
    // Atributos
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;

    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.listaClientes = new ArrayList<>();
        this.listaSeguros = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("CNPJ: " + getCNPJ());
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Email: " + getEmail());

        return joiner.toString();
    }

    // Imprime os dados da seguradora pelo toString()
    public void visualizarDados() {
        System.out.println("Dados seguradora:");
        System.out.println(toString());
    }

    // Listar todos os clientes
    public void listarClientes() {
        // Caso em que nao ha clientes cadastrados
        if (listaClientes.isEmpty()) {
            System.out.println("Sem clientes cadastrados");
            return;
        }

        System.out.println("Pessoas Juridicas:");
        // Iterando sobre os clientes PJ
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClientePJ) {
                System.out.println("---------------------------------------------");
                System.out.println(cliente);
            }
        }
        System.out.println("---------------------------------------------");

        System.out.println("");

        System.out.println("Pessoas Fisicas:");
        // Iterando sobre os clientes PF
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClientePF) {
                System.out.println("---------------------------------------------");
                System.out.println(cliente);
            }
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo cliente automatico
    public void cadastrarCliente(Cliente cliente) {
        String tipo = cliente.getDocumento()[0]; // 'tipo' recebe ou "CPF" ou "CNPJ"
        String documento = cliente.getDocumento()[1]; // 'documento' recebe o numero do documento

        // Caso em que o documento e invalido
        if (!Validacao.validarDocumento(documento, tipo)) {
            System.out.printf("%s invalido. Nao foi possivel cadastrar o cliente.\n", tipo);
            return;
        }

        // Caso em que o nome e invalido
        if (!Validacao.validarNome(cliente.getNome())) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o cliente.\n");
            return;
        }

        // Caso em que o cliente ja esta cadastrado
        for (Cliente clienteCadastrado : listaClientes) {
            if (clienteCadastrado.getDocumento()[1].equals(documento)) {
                System.out.println("Cliente ja existe. Nao foi possivel cadastrar o cliente.\n");
                return;
            }
        }

        listaClientes.add(cliente);
        System.out.println("Cliente cadastrado!");
    }

    // Cadastrar novo cliente com scanner
    public void cadastrarCliente(Scanner scanner) {
        System.out.println("\n############## Tipo de Cliente ##############");
        System.out.println("|-------------------------------------------|");
        System.out.println("| Opcao 1 - Pessoa Juridica                 |");
        System.out.println("| Opcao 2 - Pessoa Fisica                   |");
        System.out.println("|-------------------------------------------|\n");
        System.out.print("Digite uma opcao: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente;

        /* Cadastrar Pessoa Juridica */
        if (tipo == 1) {
            System.out.print("Insira o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Insira o telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Insira o endereco: ");
            String endereco = scanner.nextLine();
            System.out.print("Insira o email: ");
            String email = scanner.nextLine();
            System.out.print("Insira o CNPJ: ");
            String cnpj = scanner.nextLine();
            System.out.print("Insira a data de fundacao (dd/MM/yyyy): ");
            String data = scanner.nextLine();
            System.out.print("Insira a quantidade de funcionarios: ");
            int qtdFuncionarios = scanner.nextInt();
            scanner.nextLine();

            cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, data, qtdFuncionarios);

        /* Cadastrar Pessoa Fisica */
        } else if (tipo == 2) {
            System.out.print("Insira o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Insira o telefone: ");
            String telefone = scanner.nextLine();
            System.out.print("Insira o endereco: ");
            String endereco = scanner.nextLine();
            System.out.print("Insira o email: ");
            String email = scanner.nextLine();
            System.out.print("Insira o CPF: ");
            String cpf = scanner.nextLine();
            System.out.print("Insira o genero: ");
            String genero = scanner.nextLine();
            System.out.print("Insira o nivel de educacao: ");
            String educacao = scanner.nextLine();
            System.out.print("Insira a data de nascimento (dd/MM/yyyy): ");
            String dataNascimento = scanner.nextLine();

            cliente = new ClientePF(nome, telefone, endereco, email, cpf,
                                    genero, educacao, dataNascimento);
        } else {
            System.out.println("Opcao invalida");
            return;
        }

        cadastrarCliente(cliente);
    }

    // Excluir cliente automatico
    public void excluirCliente(String documento) {
        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento().equals(documento)) {
                String nome = cliente.getNome();
                listaClientes.remove(cliente);
                System.out.printf("Cliente %s de documento %s removido!\n", nome, documento);
                return;
            }
        }

        System.out.printf("Documento invalido. Nao foi possivel remover o cliente %s de documento %s.\n",
                        nome, documento);
    }

    // Excluir cliente com scanner
    public void excluirCliente(Scanner scanner) {
        System.out.print("Insira o documento do cliente que deseja excluir: ");
        String documento = scanner.nextLine();

        excluirCliente(documento);
    }

    // Listar seguros por cliente automatico
    public void listarSegurosPorCliente(String documento) {
        return;
    }

    // Lista seguros por cliente com scanner
    public void listarSegurosPorCliente(Scanner scanner) {
        return;
    }

    // Gerar novo seguro automatico
    public void gerarSeguro(String documento, LocalDate inicio, LocalDate fim, ArrayList<Condutor> condutores) {
        return;
    }

    // Gerar novo seguro com scanner
    public void gerarSeguro(Scanner scanner) {
        return;
    }

    // Cancelar seguro automatico
    public void cancelarSeguro(String docoumento, int id) {
        return;
    }

    // Cancelar seguro com scanner
    public void cancelarSeguro(Scanner scanner) {
        return;
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String endereco, Condutor condutor, int id) {
        return;
    }

    // Gerar novo sinistro com scanner
    public void gerarSinistro(Scanner scanner) {
        return;
    }

    // Excluir sinistro automatico
    public void excluirSinistro(int id) {
        return;
    }

    // Excluir sinistro com scanner
    public void excluirSinistro(Scanner scanner) {
        return;
    }

    // Calcular receita
    public void calcularReceita() {
        return;
    }

    // Pegar todos os seguros de um cliente
    public ArrayList<Seguro> getSegurosPorCliente(String documento) {
        return null;
    }

    // Pegar todos os sinistros de um cliente
    public ArrayList<Sinistro> getSinistrosPorCliente(String documento) {
        return null;
    }


    // Getters e Setters
    public String getCNPJ() {
        return this.cnpj;
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

    public ArrayList<Cliente> getListaClientes() {
        return this.listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }
}