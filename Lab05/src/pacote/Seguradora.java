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
        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento()[1].equals(documento)) {
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

    // Gerar novo seguro automatico
    public void gerarSeguro(String documento, String identificador,
                            String inicio, String fim, ArrayList<String> condutoresCPF) {
        // Encontrar cliente pelo documento
        for (Cliente cliente : listaClientes) {
            // Cliente PJ
            if (cliente.getDocumento()[1].equals(documento) && cliente instanceof ClientePJ){
                gerarSeguroPJ((ClientePJ)cliente, identificador, inicio, fim, condutoresCPF);
                return;
            }
            // Cliente PF
            else if (cliente.getDocumento()[1].equals(documento) && cliente instanceof ClientePF){
                gerarSeguroPF((ClientePF)cliente, identificador, inicio, fim, condutoresCPF);
                return;
            }
        }

        System.out.printf("Documento invalido. Nao foi possivel gerar o seguro para o cliente de documento %s.\n",
                        documento);
    }

    // Gerar novo seguro PJ automatico
    public void gerarSeguroPJ(ClientePJ cliente, String idFrota, String inicio,
                            String fim, ArrayList<String> condutoresCPF) {

        // Encontrar frota do cliente pelo identificador
        for (Frota frota : cliente.getListaFrotas()) {
            // Checar se alguma frota do cliente tem o id passado
            if (frota.getId() == Integer.parseInt(idFrota)) {
                // Criar objeto do tipo Seguro PJ
                SeguroPJ seguro = new SeguroPJ(frota, cliente, inicio, fim, this);
                // Autorizar condutores no seguro
                for (String cpf: condutoresCPF) {
                    for (Condutor condutor: cliente.getListaCondutores()) {
                        if (condutor.getCPF().equals(cpf)) {
                            seguro.autorizarCondutor(condutor);
                        }
                    }
                }
                // Calcular e setar valor mensal do seguro
                seguro.setValorMensal(seguro.calcularValorMensal());
                // Adicionar seguro na frota do cliente
                frota.setSeguro(seguro);
                // Adicionar seguro na seguradora
                listaSeguros.add(seguro);
                // Adicionar seguro no cliente
                cliente.adicionarSeguro(seguro);

                System.out.println("Seguro gerado com sucesso.");
                return;
            }
        }

        System.out.printf("ID invalido. Nao foi possivel gerar o seguro para a frota de ID %s.\n", idFrota);
        return;
    }

    // Gerar novo seguro PF automatico
    public void gerarSeguroPF(ClientePF cliente, String placa, String inicio,
                            String fim, ArrayList<String> condutoresCPF) {
        // Encontrar veiculo do cliente pelo identificador
        for (Veiculo veiculo : cliente.getListaVeiculos()) {
            // Checar se algum veiculo do cliente tem a placa passada
            if (veiculo.getPlaca().equals(placa)) {
                // Criar objeto do tipo Seguro PF
                SeguroPF seguro = new SeguroPF(veiculo, cliente, inicio, fim, this);
                // Autorizar condutores no seguro
                for (String cpf: condutoresCPF) {
                    for (Condutor condutor: cliente.getListaCondutores()) {
                        if (condutor.getCPF().equals(cpf)) {
                            seguro.autorizarCondutor(condutor);
                        }
                    }
                }
                // Calcular e setar valor mensal do seguro
                seguro.setValorMensal(seguro.calcularValorMensal());
                // Adicionar seguro no veiculo do cliente
                veiculo.setSeguro(seguro);
                // Adicionar seguro na seguradora
                listaSeguros.add(seguro);
                // Adicionar seguro no cliente
                cliente.adicionarSeguro(seguro);

                System.out.println("Seguro gerado com sucesso.");
                return;
            }
        }
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

        String documento, identificador;

        /* Cadastar Seguro de Pessoa Juridica */
        if (tipo == 1) {
            System.out.print("Insira o documento do cliente: ");
            documento = scanner.nextLine();
            System.out.print("Insira o ID da frota: ");
            identificador = scanner.nextLine();

        /* Cadastrar Seguro de Pessoa Fisica */
        } else if (tipo == 2) {
            System.out.print("Insira o documento do cliente: ");
            documento = scanner.nextLine();
            System.out.print("Insira a placa do veiculo: ");
            identificador = scanner.nextLine();
            
        } else {
            System.out.println("Opcao invalida");
            return;
        }

        System.out.print("Insira a data de inicio do seguro: ");
        String inicio = scanner.nextLine();
        System.out.print("Insira a data de fim do seguro: ");
        String fim = scanner.nextLine();
        ArrayList<String> condutoresCPF = new ArrayList<>();
        System.out.println("|-------------------------------------------|");
        System.out.println("| Opcao 1 - Cadastrar Condutor              |");
        System.out.println("| Opcao 0 - Sair                            |");
        System.out.println("|-------------------------------------------|\n");
        int op;
        do {
            op = scanner.nextInt();
            scanner.nextLine();
            if (op == 1) {
                System.out.print("Insira o documento do condutor que deseja autorizar para esse seguro: ");
                String documentoCondutor = scanner.nextLine();
                condutoresCPF.add(documentoCondutor);
            } else if (op != 0 && op != 1) {
                System.out.println("Opcao invalida");
                return;
            }
        } while (op != 0);

        gerarSeguro(documento, identificador, inicio, fim, condutoresCPF);
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

    // Retorna todos os seguros de um cliente
    public ArrayList<Seguro> getSegurosPorCliente(String documento) {
        return null;
    }

    // Retorna todos os sinistros de um cliente
    public ArrayList<Sinistro> getSinistrosPorCliente(String documento) {
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