package pacote;

import java.time.format.DateTimeFormatter;
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
        System.out.println("Dados seguradora:");
        System.out.println(toString());
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
                System.out.println("CNPJ invalido. Nao foi possivel cadastrar o cliente.");
                return;
            }
        } else if (cliente.getClass().getSimpleName().equals("ClientePF")) {
            clientePF = (ClientePF)cliente;
            if (!Validacao.validarCPF(clientePF.getCPF())) {
                System.out.println("CPF invalido. Nao foi possivel cadastrar o cliente.");
                return;
            }
        }
        if (!Validacao.validarNome(cliente.getNome()) || listaClientes.contains(cliente)) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o cliente.");
            return;
        }

        listaClientes.add(cliente);
        double valorSeguro = calcularPrecoSeguroCliente(cliente);
        cliente.setValorSeguro(valorSeguro);
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

        /* Cadastar Pessoa Juridica */
        if (tipo == 1) {
            System.out.print("Insira o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Insira o endereco: ");
            String endereco = scanner.nextLine();
            System.out.print("Insira o cnpj: ");
            String cnpj = scanner.nextLine();
            System.out.print("Insira a data de fundacao (dd/MM/yyyy): ");
            String data = scanner.nextLine();
            System.out.print("Insira a quantidade de funcionarios: ");
            int qtdFuncionarios = scanner.nextInt();
            scanner.nextLine();

            cliente = new ClientePJ(nome, endereco, cnpj, data, qtdFuncionarios);

        /* Cadastrar Pessoa Fisica */
        } else if (tipo == 2) {
            System.out.print("Insira o nome: ");
            String nome = scanner.nextLine();
            System.out.print("Insira o endereco: ");
            String endereco = scanner.nextLine();
            System.out.print("Insira o cpf: ");
            String cpf = scanner.nextLine();
            System.out.print("Insira o genero: ");
            String genero = scanner.nextLine();
            System.out.print("Insira a data de licenca (dd/MM/yyyy): ");
            String dataLicenca = scanner.nextLine();
            System.out.print("Insira o nivel de educacao: ");
            String educacao = scanner.nextLine();
            System.out.print("Insira a data de nascimento (dd/MM/yyyy): ");
            String dataNascimento = scanner.nextLine();
            System.out.print("Insira a classe economica: ");
            String classe = scanner.nextLine();

            cliente = new ClientePF(nome, endereco, cpf, genero, dataLicenca,
                                    educacao, dataNascimento, classe);
        } else {
            System.out.println("Opcao invalida");
            return;
        }

        for (Cliente cl : listaClientes) {
            if (cl.getNome().equals(cliente.getNome())) {
                System.out.printf("Cliente %s ja existe.\n", cliente.getNome());
                return;
            }
        }

        cadastrarCliente(cliente);
    }

    // Excluir cliente
    public void excluirCliente(Scanner scanner) {
        System.out.print("Insira o nome do cliente que deseja excluir: ");
        String nome = scanner.nextLine();

        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equals(nome)) {
                listaClientes.remove(cliente);
                System.out.printf("Cliente %s removido!\n", nome);
                
                return;
            }
        }

        System.out.printf("Nome invalido. Nao foi possivel remover o cliente %s.\n", nome);
    }

    // Listar todos os sinistros da seguradora por cliente
    public void listarSinistros() {
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados.");
            return;
        }

        System.out.println("Sinistros:");
        for (Sinistro sinistro : listaSinistros) {
            System.out.println("---------------------------------------------");
            System.out.println(sinistro);
        }
        System.out.println("---------------------------------------------");

        System.out.println("");

        int indexSinistro = 0; // No menu interativo, esse indice e escolhido pelo usuario
        visualizarSinistro(listaSinistros.get(indexSinistro));
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String nomeCliente, String endereco, String placaVeiculo) {
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nomeCliente)) {
                for (Veiculo veiculo: cliente.getListaVeiculos()) {
                    if (veiculo.getPlaca().equals(placaVeiculo)) {
                        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
                        if (listaSinistros.contains(sinistro)) {
                            System.out.println("Sinistro ja foi gerado.");
                            return;
                        }
                        listaSinistros.add(sinistro);
                        cliente.getListaSinistros().add(sinistro);
                        cliente.setValorSeguro(calcularPrecoSeguroCliente(cliente));
                        System.out.println("Sinistro gerado!");
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

    // Visualizar unico sinistro (com mais detalhes do que a listagem normal)
    public void visualizarSinistro(Sinistro sinistro) {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataString = sinistro.getData().format(dtf); // Tranformando LocalDate em String

        System.out.println(String.format("Sinistro de ID %03d:", sinistro.getID()));
        joiner.add("---------------------------------------------");
        joiner.add("Data: " + dataString);
        joiner.add("Endereco: " + sinistro.getEndereco());
        joiner.add("---------------------------------------------");
        joiner.add("Seguradora:");
        joiner.add(sinistro.getSeguradora().toString());
        joiner.add("---------------------------------------------");
        joiner.add("Veiculo:");
        joiner.add(sinistro.getVeiculo().toString());
        joiner.add("---------------------------------------------");
        joiner.add("Cliente:");
        joiner.add(sinistro.getCliente().toString());
        joiner.add("---------------------------------------------");

        System.out.println(joiner.toString());
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