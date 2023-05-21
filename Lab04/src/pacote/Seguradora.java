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
        // Caso em que nao ha clientes cadastrados
        if (listaClientes.isEmpty()) {
            System.out.println("Sem clientes cadastrados");
            return;
        }

        System.out.println("Pessoas Juridicas:");
        // Iterando sobre os clientes PJ
        for (Cliente cliente : listaClientes) {
            if (cliente.getClass().getSimpleName().equals("ClientePJ")) {
                System.out.println("---------------------------------------------");
                System.out.println(cliente);
            }
        }
        System.out.println("---------------------------------------------");

        System.out.println("");

        System.out.println("Pessoas Fisicas:");
        // Iterando sobre os clientes PF
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
        
        // Caso em que o CNPJ e invalido
        if (cliente.getClass().getSimpleName().equals("ClientePJ")) {
            clientePJ = (ClientePJ)cliente;
            if (!Validacao.validarCNPJ(clientePJ.getCNPJ())) {
                System.out.println("CNPJ invalido. Nao foi possivel cadastrar o cliente.");
                return;
            }
        // Caso em que o CPF e invalido
        } else if (cliente.getClass().getSimpleName().equals("ClientePF")) {
            clientePF = (ClientePF)cliente;
            if (!Validacao.validarCPF(clientePF.getCPF())) {
                System.out.println("CPF invalido. Nao foi possivel cadastrar o cliente.");
                return;
            }
        }
        // Caso em que o nome e invalido
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

        // Caso em que ja tem um cliente com o mesmo nome
        for (Cliente cl : listaClientes) {
            if (cl.getNome().equals(cliente.getNome())) {
                System.out.printf("Cliente %s ja existe.\n", cliente.getNome());
                return;
            }
        }

        cadastrarCliente(cliente);
    }

    // Excluir cliente com scanner
    public void excluirCliente(Scanner scanner) {
        System.out.print("Insira o nome do cliente que deseja excluir: ");
        String nome = scanner.nextLine();

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equals(nome)) {
                listaClientes.remove(cliente);
                System.out.printf("Cliente %s removido!\n", nome);
                return;
            }
        }

        System.out.printf("Nome invalido. Nao foi possivel remover o cliente %s.\n", nome);
    }

    // Listar todos os sinistros da seguradora por cliente automatico
    public void listarSinistros() {
        // Caso em que nao ha sinistros gerados
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados.");
            return;
        }

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            System.out.printf("Cliente %s:\n", cliente.getNome());
            // Caso em que nao ha sinistros
            if (cliente.getListaSinistros().isEmpty()) {
                System.out.println("---------------------------------------------");
                System.out.println("Nao ha sinistros gerados.");
            }
            // Iterando sobre os sinistros
            for (Sinistro sinistro: cliente.getListaSinistros()) {
                System.out.println("---------------------------------------------");
                System.out.println(sinistro);
            }
            System.out.println("---------------------------------------------");
            System.out.println("");
        }

        int indexSinistro = 0; // No menu interativo, esse indice e escolhido pelo usuario
        visualizarSinistro(listaSinistros.get(indexSinistro));
    }

    // Listar todos os sinistros da seguradora por cliente com scanner
    public void listarSinistros(Scanner scanner) {
        // Caso em que nao ha sinistros gerados
        if (listaSinistros.isEmpty()) {
            System.out.println("Nao ha sinistros gerados.");
            return;
        }

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            System.out.printf("Cliente %s:\n", cliente.getNome());
            // Caso em que nao ha sinistros
            if (cliente.getListaSinistros().isEmpty()) {
                System.out.println("---------------------------------------------");
                System.out.println("Nao ha sinistros gerados.");
            }
            // Iterando sobre os sinistros
            for (Sinistro sinistro: cliente.getListaSinistros()) {
                System.out.println("---------------------------------------------");
                System.out.println(sinistro);
            }
            System.out.println("---------------------------------------------");
            System.out.println("");
        }

        System.out.print("Insira o numero do sinistro que deseja visualizar: ");
        int num = scanner.nextInt();
        scanner.nextLine();

        // Caso em que o sinistro nao existe
        if (num < 1 || num > listaSinistros.size()) {
            System.out.printf("Numero invalido. Nao foi possivel visualizar o sinistro %d.\n", num);
            return;
        }

        visualizarSinistro(listaSinistros.get(num - 1));
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String nomeCliente, String endereco, String placaVeiculo) {
        // Iterando sobre os clientes
        for (Cliente cliente: listaClientes){
            if (cliente.getNome().equals(nomeCliente)) {
                // Iterando sobre os veiculos
                for (Veiculo veiculo: cliente.getListaVeiculos()) {
                    if (veiculo.getPlaca().equals(placaVeiculo)) {
                        Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
                        // Checando se ja existe um sinistro igual
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
        System.out.print("Insira a data que ocorreu o sinistro (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.print("Insira o nome do cliente: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o endereco: ");
        String endereco = scanner.nextLine();
        System.out.print("Insira a placa do veiculo: ");
        String placa = scanner.nextLine();

        gerarSinistro(data, nome, endereco, placa);
    }

    // Excluir sinistro com scanner
    public void excluirSinistro(Scanner scanner) {
        System.out.print("Insira o ID do sinistro que deseja excluir: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        // Iterando sobre os sinistros
        for (Sinistro sinistro: listaSinistros) {
            if (sinistro.getID() == ID) {
                listaSinistros.remove(sinistro);
                System.out.printf("Sinistro %d removido!\n", ID);
                return;
            }
        }

        System.out.printf("ID invalido. Nao foi possivel remover o sinistro %d.\n", ID);
    }

    // Listar veiculos por cliente
    public void listarVeiculos() {
        // Caso em que nao ha clientes cadastrados
        if (listaClientes.isEmpty()) {
            System.out.println("Nao ha clientes cadastrados.");
            return;
        }
        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            System.out.printf("Cliente %s:\n", cliente.getNome());
            // Caso em que nao ha veiculos
            if (cliente.getListaVeiculos().isEmpty()) {
                System.out.println("---------------------------------------------");
                System.out.println("Nao ha veiculos cadastrados.");
            }
            // Iterando sobre os veiculos
            for (Veiculo veiculo: cliente.getListaVeiculos()) {
                System.out.println("---------------------------------------------");
                System.out.println(veiculo);
            }
            System.out.println("---------------------------------------------");
            System.out.println("");
        }
    }

    // Transferir seguro (transferir apenas os veiculos e alterar o valor do seguro)
    public void transferirSeguro(Scanner scanner) {
        Cliente cliente1 = null;
        Cliente cliente2 = null;

        System.out.print("Insira o nome do cliente que ira receber o seguro: ");
        String nome1 = scanner.nextLine();
        System.out.print("Insira o nome do cliente que ira transferir o seguro: ");
        String nome2 = scanner.nextLine();

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equals(nome1)) {
                cliente1 = cliente;
            }
            if (cliente.getNome().equals(nome2)) {
                cliente2 = cliente;
            }
        }

        // Caso em que um ou ambos os clientes passados nao existem
        if (cliente1 == null || cliente2 == null) {
            System.out.println("Nomes invalidos. Nao foi possivel transferir o seguro.");
            return;
        }

        // Caso em que o cliente que ira transferir o seguro tem sinistros em andamento
        if (!cliente2.getListaSinistros().isEmpty()) {
            System.out.println("Nao e possivel transferir um seguro com sinistro(s) em andamento.");
            return;
        }

        // Transferencia do seguro:
        // (nao precisa se preocupar com os sinistros pois nao tem como transferir um seguro com sinistros em andamento)

        for (Veiculo veiculo : cliente2.getListaVeiculos()) {
            cliente1.cadastrarVeiculo(veiculo);
        }

        cliente2.setListaVeiculos(new ArrayList<>());

        double valorSeguro1 = calcularPrecoSeguroCliente(cliente1);
        cliente1.setValorSeguro(valorSeguro1);
        double valorSeguro2 = calcularPrecoSeguroCliente(cliente2);
        cliente2.setValorSeguro(valorSeguro2);
        
        System.out.printf("Seguro transferido de %s para %s!\n", nome2, nome1);
        System.out.printf("Novo valor do seguro de %s: R$ %.2f.\n", nome1, valorSeguro1);
        System.out.printf("Novo valor do seguro de %s: R$ %.2f.\n", nome2, valorSeguro2);
    }

    // Calcular receita
    public void calcularReceita() {
        double receita = 0;
        // Iterando sobre os clientes
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