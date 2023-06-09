package pacote;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class Seguradora {
    // Atributos
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;

    // Construtor
    public Seguradora(String CNPJ, String nome, String telefone, String endereco, String email) {
        this.CNPJ = CNPJ;
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
        Cliente cliente = getCliente(documento);

        if (cliente != null) {
            String nome = cliente.getNome();
            // Remove todos os seguros do cliente
            for (Seguro seguro: getSegurosPorCliente(documento)) {
                cancelarSeguro(documento, seguro.getId());
            }
            // Remove o cliente
            listaClientes.remove(cliente);
            System.out.printf("Cliente %s de documento %s removido!\n", nome, documento);
        } else {
            System.out.printf("Documento invalido. Nao foi possivel remover o cliente de documento %s.\n",
                            documento);
        }
    }

    // Excluir cliente com scanner
    public void excluirCliente(Scanner scanner) {
        System.out.print("Insira o documento do cliente que deseja excluir: ");
        String documento = scanner.nextLine();

        excluirCliente(documento);
    }

    // Listar todos os seguros da seguradora
    public void listarSeguros() {
        // Caso em que nao ha seguros gerados
        if (listaSeguros.isEmpty()) {
            System.out.println("Nao ha seguros gerados.");
            return;
        }

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            System.out.printf("Cliente %s:\n", cliente.getNome());
            // Caso em que nao ha seguros
            if (cliente.getListaSeguros().isEmpty()) {
                System.out.println("---------------------------------------------");
                System.out.println("Nao ha seguros gerados.");
            }
            // Iterando sobre os seguros de cada cliente
            for (Seguro seguro: cliente.getListaSeguros()) {
                System.out.println("---------------------------------------------");
                System.out.printf("Seguro de ID: %03d:\n", seguro.getId());
                System.out.printf("Data inicio: %s\n", seguro.getDataInicio());
                System.out.printf("Data fim: %s\n", seguro.getDataFim());
                System.out.printf("Valor Mensal: R$%.2f\n", seguro.getValorMensal());
                System.out.printf("Quantidade de sinistros: %d\n", seguro.getListaSinistros().size());
                System.out.printf("Quantidade de condutores: %d\n", seguro.getListaCondutores().size());    
            }
            System.out.println("---------------------------------------------");
            System.out.println("");
        }
    }

    // Listar seguros por cliente automatico
    public void listarSegurosPorCliente(String documento) {
        ArrayList<Seguro> segurosCliente = getSegurosPorCliente(documento);

        System.out.printf("Cliente de documento %s:\n", documento);
        // Caso em que nao ha seguros gerados
        if (segurosCliente.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Nao ha seguros gerados.");
            return;
        }

        // Iterando sobre os seguros do cliente
        for (Seguro seguro : segurosCliente) {
            System.out.println("---------------------------------------------");
            System.out.printf("Seguro de ID: %03d:\n", seguro.getId());
            System.out.printf("Data inicio: %s\n", seguro.getDataInicio());
            System.out.printf("Data fim: %s\n", seguro.getDataFim());
            System.out.printf("Valor Mensal: R$%.2f\n", seguro.getValorMensal());
            System.out.printf("Quantidade de sinistros: %d\n", seguro.getListaSinistros().size());
            System.out.printf("Quantidade de condutores: %d\n", seguro.getListaCondutores().size());
        }
        System.out.println("---------------------------------------------");
        System.out.println("");  
    }

    // Listar seguros por cliente com scanner
    public void listarSegurosPorCliente(Scanner scanner) {
        System.out.print("Insira o documento do cliente que deseja listar os seguros: ");
        String documento = scanner.nextLine();
        listarSegurosPorCliente(documento);
    }

    // Gerar novo seguro PJ automatico
    public void gerarSeguroPJ(ClientePJ cliente, Frota frota, String inicio,
                            String fim, Condutor condutor) {
        SeguroPJ seguro = new SeguroPJ(frota, cliente, inicio, fim, this);
        seguro.autorizarCondutor(condutor); // Autorizar condutor no seguro
        seguro.setValorMensal(seguro.calcularValorMensal()); // Calcular e setar valor mensal do seguro
        frota.setSeguro(seguro); // Adicionar seguro na frota do cliente
        listaSeguros.add(seguro); // Adicionar seguro na seguradora
        cliente.adicionarSeguro(seguro); // Adicionar seguro no cliente

        System.out.println("Seguro gerado com sucesso.");
    }

    // Gerar novo seguro PF automatico
    public void gerarSeguroPF(ClientePF cliente, Veiculo veiculo, String inicio,
                            String fim, Condutor condutor) {
        SeguroPF seguro = new SeguroPF(veiculo, cliente, inicio, fim, this);
        seguro.autorizarCondutor(condutor); // Autorizar condutor no seguro
        seguro.setValorMensal(seguro.calcularValorMensal()); // Calcular e setar valor mensal do seguro
        veiculo.setSeguro(seguro); // Adicionar seguro no veiculo do cliente
        listaSeguros.add(seguro); // Adicionar seguro na seguradora
        cliente.adicionarSeguro(seguro); // Adicionar seguro no cliente

        System.out.println("Seguro gerado com sucesso.");
    }

    // Gerar novo seguro com scanner
    public void gerarSeguro(Scanner scanner) {
        System.out.println("\n############## Tipo de Cliente ##############");
        System.out.println("|-------------------------------------------|");
        System.out.println("| Opcao 1 - Pessoa Juridica                 |");
        System.out.println("| Opcao 2 - Pessoa Fisica                   |");
        System.out.println("|-------------------------------------------|\n");
        System.out.print("Digite uma opcao: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = null;
        Frota frota = null;
        Veiculo veiculo = null;

        /* Cadastar Seguro de Pessoa Juridica */
        if (tipo == 1) {
            // Encontra cliente
            System.out.print("Insira o CNPJ do cliente: ");
            String cnpj = scanner.nextLine();
            cliente = getCliente(cnpj);
            if (cliente == null) {
                System.out.printf("Cliente de CNPJ %s nao encontrado. Nao foi possivel gerar o seguro.\n", cnpj);
                return;
            }
            // Encontrar frota
            System.out.print("Insira o ID da frota: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            frota = ((ClientePJ)cliente).getFrota(id);
            if (frota == null) {
                System.out.printf("Frota de ID %s nao encontrada. Nao foi possivel gerar o seguro.\n", id);
                return;
            }
            // Pegar informacoes do seguro
            System.out.print("Insira a data de inicio do seguro: ");
            String inicio = scanner.nextLine();
            System.out.print("Insira a data de fim do seguro: ");
            String fim = scanner.nextLine();
            System.out.println("E necessario cadastrar pelo menos um condutor para gerar o seguro.");
            Condutor condutor = cliente.cadastrarCondutor(scanner);
            System.out.println("Para cadastrar mais condutores, acesse a area do cliente.");

            gerarSeguroPJ((ClientePJ)cliente, frota, inicio, fim, condutor);

        /* Cadastrar Seguro de Pessoa Fisica */
        } else if (tipo == 2) {
            // Encontrar cliente
            System.out.print("Insira o CPF do cliente: ");
            String cpf = scanner.nextLine();
            cliente = getCliente(cpf);
            if (cliente == null) {
                System.out.printf("Cliente de CPF %s nao encontrado. Nao foi possivel gerar o seguro.\n", cpf);
                return;
            }
            // Encontrar veiculo
            System.out.print("Insira a placa do veiculo: ");
            String placa = scanner.nextLine();
            veiculo = ((ClientePF)cliente).getVeiculo(placa);
            if (veiculo == null) {
                System.out.printf("Veiculo de placa %s nao encontrado. Nao foi possivel gerar o seguro.\n", placa);
                return;
            }
            // Pegar informacoes do seguro
            System.out.print("Insira a data de inicio do seguro: ");
            String inicio = scanner.nextLine();
            System.out.print("Insira a data de fim do seguro: ");
            String fim = scanner.nextLine();
            System.out.println("E necessario cadastrar pelo menos um condutor para gerar o seguro.");
            Condutor condutor = cliente.cadastrarCondutor(scanner);
            System.out.println("Para cadastrar mais condutores, acesse a area do cliente.");

            gerarSeguroPF((ClientePF)cliente, veiculo, inicio, fim, condutor);
            
        } else {
            System.out.println("Opcao invalida");
            return;
        }
    }

    // Cancelar seguro automatico
    public void cancelarSeguro(String documento, int id) {
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

    // Retorna todos os seguros de um cliente
    public ArrayList<Seguro> getSegurosPorCliente(String documento) {
        return null;
    }

    // Retorna todos os sinistros de um cliente
    public ArrayList<Sinistro> getSinistrosPorCliente(String documento) {
        return null;
    }

    // Retorna o cliente atraves do documento
    public Cliente getCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento()[1].equals(documento)) {
                return cliente;
            }
        }
        return null;
    }


    // Getters e Setters
    public String getCNPJ() {
        return this.CNPJ;
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