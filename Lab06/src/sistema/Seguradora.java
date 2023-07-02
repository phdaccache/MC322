package sistema;

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
        joiner.add("Quantidade de Clientes: " + getListaClientes().size());
        joiner.add("Quantidade de Seguros: " + getListaSeguros().size());

        return joiner.toString();
    }

    // Imprime os dados da seguradora pelo toString()
    public void visualizarDados() {
        System.out.println("Dados Seguradora:");
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
        for (Cliente cliente: getClientesPJ()) {
            System.out.println("---------------------------------------------");
            System.out.println(cliente);
        }
        System.out.println("---------------------------------------------");
        System.out.println("");

        System.out.println("Pessoas Fisicas:");
        for (Cliente cliente: getClientesPF()) {
            System.out.println("---------------------------------------------");
            System.out.println(cliente);
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo cliente automatico
    public void cadastrarCliente(Cliente cliente) {
        // Checando se foi passado um cliente
        if (cliente == null) {
            System.out.println("Nao foi possivel cadastrar o cliente.");
            return;
        }

        String tipo = cliente.getDocumento()[0]; // 'tipo' recebe ou "CPF" ou "CNPJ"
        String documento = cliente.getDocumento()[1]; // 'documento' recebe o numero do documento

        // Caso em que o documento e invalido
        if (!Validacao.validarDocumento(documento, tipo)) {
            System.out.printf("%s invalido. Nao foi possivel cadastrar o cliente.\n", tipo);
            return;
        }

        // Caso em que o nome e invalido
        if (!Validacao.validarNome(cliente.getNome())) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o cliente.");
            return;
        }

        // Caso em que o cliente ja esta cadastrado
        if (getCliente(documento) != null) {
            System.out.println("Cliente ja existe. Nao foi possivel cadastrar o cliente.");
            return;
        }

        cliente.setSeguradora(this);
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

        Cliente cliente = null;

        switch (tipo) {
            case 1:
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
                break;
            case 2:
                System.out.print("Insira o nome: ");
                String nome2 = scanner.nextLine();
                System.out.print("Insira o telefone: ");
                String telefone2 = scanner.nextLine();
                System.out.print("Insira o endereco: ");
                String endereco2 = scanner.nextLine();
                System.out.print("Insira o email: ");
                String email2 = scanner.nextLine();
                System.out.print("Insira o CPF: ");
                String cpf = scanner.nextLine();
                System.out.print("Insira o genero: ");
                String genero = scanner.nextLine();
                System.out.print("Insira o nivel de educacao: ");
                String educacao = scanner.nextLine();
                System.out.print("Insira a data de nascimento (dd/MM/yyyy): ");
                String dataNascimento = scanner.nextLine();

                cliente = new ClientePF(nome2, telefone2, endereco2, email2, cpf,
                                        genero, educacao, dataNascimento);    
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }

        cadastrarCliente(cliente);
    }

    // Excluir cliente automatico
    public void excluirCliente(String documento) {
        Cliente cliente = getCliente(documento);

        // Caso em que o cliente nao existe
        if (cliente == null) {
            System.out.printf("Documento invalido. Nao foi possivel remover o cliente de documento %s.\n",
                            documento);
            return;
        }

        String nome = cliente.getNome();

        // Remove todos os seguros do cliente na seguradora
        for (Seguro seguro: getSegurosPorCliente(documento)) {
            listaSeguros.remove(seguro);
        }
        
        listaClientes.remove(cliente);
        System.out.printf("Cliente %s de documento %s removido!\n", nome, documento);
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
            listarSegurosPorCliente(cliente.getDocumento()[1]);
        }
    }

    // Listar seguros por cliente automatico
    public void listarSegurosPorCliente(String documento) {
        Cliente cliente = getCliente(documento);

        // Caso em que o cliente nao existe
        if (cliente == null) {
            System.out.printf("Documento invalido. Nao foi possivel listar os seguros do cliente de documento %s.\n",
                            documento);
            return;
        }

        ArrayList<Seguro> segurosCliente = getSegurosPorCliente(documento);

        System.out.printf("Seguros Cliente %s:\n", cliente.getNome());
        // Caso em que nao ha seguros gerados
        if (segurosCliente == null || segurosCliente.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Nao ha seguros gerados.");
        }

        // Iterando sobre os seguros do cliente
        for (Seguro seguro : segurosCliente) {
            System.out.println("---------------------------------------------");
            System.out.println(seguro);
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
        // Checando se foram passados parametros validos
        if (cliente == null || frota == null || condutor == null) {
            System.out.println("Parametros invalidos. Nao foi possivel gerar o seguro.");
            return;
        }

        // Checando se a frota ja possui um seguro
        if (frota.getSeguro() != null) {
            System.out.printf("Nao foi possivel gerar o seguro. A frota de ID %03d ja esta segurada.\n",
                            frota.getId());
            return;
        }

        SeguroPJ seguro = new SeguroPJ(frota, cliente, inicio, fim, this);
        seguro.autorizarCondutor(condutor); // Autorizar condutor no seguro
        seguro.setValorMensal(seguro.calcularValorMensal()); // Calcular e setar valor mensal do seguro

        listaSeguros.add(seguro); // Adicionar seguro na seguradora
        cliente.adicionarSeguro(seguro); // Adicionar seguro no cliente
        frota.setSeguro(seguro); // Adicionar seguro na frota do cliente
        cliente.setValorMensalTotal(cliente.calcularValorMensalTotal()); // Atualizar valor mensal total do cliente

        System.out.printf("Seguro gerado! ID do Seguro: %03d.\n", seguro.getId());
    }

    // Gerar novo seguro PF automatico
    public void gerarSeguroPF(ClientePF cliente, Veiculo veiculo, String inicio,
                            String fim, Condutor condutor) {
        // Checando se foram passados parametros validos
        if (cliente == null || veiculo == null || condutor == null) {
            System.out.println("Parametros invalidos. Nao foi possivel gerar o seguro.");
            return;
        }

        // Checando se o veiculo ja possui um seguro
        if (veiculo.getSeguro() != null) {
            System.out.printf("Nao foi possivel gerar o seguro. O veiculo de placa %s ja esta segurado.\n",
                            veiculo.getPlaca());
            return;
        }

        SeguroPF seguro = new SeguroPF(veiculo, cliente, inicio, fim, this);
        seguro.autorizarCondutor(condutor); // Autorizar condutor no seguro
        seguro.setValorMensal(seguro.calcularValorMensal()); // Calcular e setar valor mensal do seguro
        
        listaSeguros.add(seguro); // Adicionar seguro na seguradora
        cliente.adicionarSeguro(seguro); // Adicionar seguro no cliente
        veiculo.setSeguro(seguro); // Adicionar seguro no veiculo do cliente
        cliente.setValorMensalTotal(cliente.calcularValorMensalTotal()); // Atualizar valor mensal total do cliente

        System.out.printf("Seguro gerado! ID do Seguro: %03d.\n", seguro.getId());
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

        switch (tipo) {
            case 1:
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
                if (id > ((ClientePJ)cliente).getListaFrotas().size() || id < 1) {
                    System.out.printf("Frota de ID %03d nao encontrada. Nao foi possivel gerar o seguro.\n", id);
                    return;
                }
                frota = ((ClientePJ)cliente).getListaFrotas().get(id - 1);
                // Pegar informacoes do seguro
                System.out.print("Insira a data de inicio do seguro: ");
                String inicio = scanner.nextLine();
                System.out.print("Insira a data de fim do seguro: ");
                String fim = scanner.nextLine();
                System.out.println("E necessario cadastrar pelo menos um condutor para gerar o seguro.");
                Condutor condutor = novoCondutor(scanner);
                if (condutor == null) { return;}
                System.out.println("Para cadastrar mais condutores, acesse a area do cliente.");

                gerarSeguroPJ((ClientePJ)cliente, frota, inicio, fim, condutor);
                break;
            case 2:
                // Encontra cliente
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
                String inicio2 = scanner.nextLine();
                System.out.print("Insira a data de fim do seguro: ");
                String fim2 = scanner.nextLine();
                System.out.println("E necessario cadastrar pelo menos um condutor para gerar o seguro.");
                Condutor condutor2 = novoCondutor(scanner);
                System.out.println("Para cadastrar mais condutores, acesse a area do cliente.");

                gerarSeguroPF((ClientePF)cliente, veiculo, inicio2, fim2, condutor2);
                break;
            default:
                System.out.println("Opcao invalida.");
                break;
        }
    }

    // Cancelar seguro automatico
    public void cancelarSeguro(String documento, int id) {
        Seguro seguro = getSeguro(id);

        if (seguro.getCliente().getDocumento()[1].equals(documento)) {
            // Cancelar seguro na seguradora
            listaSeguros.remove(seguro);
            // Cancelar seguro no cliente (tambem remove o seguro do veiculo ou frota. metodo sobrescrito em PF e PJ)
            seguro.getCliente().excluirSeguro(seguro);
            // Atualizar valor mensal total do cliente
            seguro.getCliente().setValorMensalTotal(seguro.getCliente().calcularValorMensalTotal());
            
            System.out.printf("Seguro de ID %03d cancelado!\n", id);
            return;
        }
        System.out.printf("Documento %s ou ID %03d invalido. Nao foi possivel cancelar o seguro.", documento, id);
    }

    // Cancelar seguro com scanner
    public void cancelarSeguro(Scanner scanner) {
        System.out.print("Insira o documento do cliente que deseja cancelar o seguro: ");
        String documento = scanner.nextLine();
        System.out.print("Insira o ID do seguro que deseja cancelar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        cancelarSeguro(documento, id);
    }

    // Listar todos os sinistros
    public void listarSinistros() {
        for (Cliente cliente: listaClientes) {
            listarSinistrosPorCliente(cliente.getDocumento()[1]);
        }
    }

    // Listar sinistros por cliente automatico
    public void listarSinistrosPorCliente(String documento) {
        Cliente cliente = getCliente(documento);

        // Caso em que o cliente nao existe
        if (cliente == null) {
            System.out.printf("Documento invalido. Nao foi possivel listar os seguros do cliente de documento %s.\n",
                            documento);
            return;
        }

        ArrayList<Sinistro> sinistrosCliente = getSinistrosPorCliente(documento);
    
        System.out.printf("Sinistros Cliente %s:\n", cliente.getNome());
        // Caso em que nao ha sinistros gerados
        if (sinistrosCliente == null || sinistrosCliente.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Nao ha sinistros gerados.");
        }

        // Iterando sobre os sinistros do cliente
        for (Sinistro sinistro : sinistrosCliente) {
            System.out.println("---------------------------------------------");
            System.out.println(sinistro);
        }
        System.out.println("---------------------------------------------");
        System.out.println("");  
    }

    // Listar sinistros por cliente com scanner
    public void listarSinistrosPorCliente(Scanner scanner) {
        System.out.print("Insira o documento do cliente que deseja listar os sinistros: ");
        String documento = scanner.nextLine();
        listarSinistrosPorCliente(documento);
    }

    // Gerar novo sinistro automatico
    public void gerarSinistro(String data, String endereco, String cpfCondutor, int id) {
        Seguro seguro = getSeguro(id);

        // Caso em que o seguro nao existe
        if (seguro == null) {
            System.out.printf("Nao foi possivel gerar o sinistro para o seguro de ID %03d.\n", id);
            return;
        }

        seguro.gerarSinistro(data, endereco, cpfCondutor);
    }

    // Gerar novo sinistro com scanner
    public void gerarSinistro(Scanner scanner) {
        System.out.print("Insira a data do sinistro (dd/MM/yyyy): ");
        String data = scanner.nextLine();
        System.out.print("Insira o endereco do sinistro: ");
        String endereco = scanner.nextLine();
        System.out.print("Insira o CPF do condutor: ");
        String cpf = scanner.nextLine();
        System.out.print("Insira o ID do seguro: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        gerarSinistro(data, endereco, cpf, id);
    }

    // Excluir sinistro automatico
    public void excluirSinistro(int id) {
        Sinistro sinistro = getSinistro(id);

        // Caso em que o sinistro nao existe
        if (sinistro == null) {
            System.out.printf("Nao foi possivel excluir o sinistro de ID %03d.\n", id);
            return;
        }

        sinistro.getSeguro().excluirSinistro(sinistro);
    }

    // Excluir sinistro com scanner
    public void excluirSinistro(Scanner scanner) {
        System.out.print("Insira o ID do sinistro que deseja excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        excluirSinistro(id);
    }

    // Calcular receita
    public void calcularReceita() {
        double receita = 0;

        // Iterando sobre os clientes
        for (Cliente cliente : listaClientes) {
            receita += cliente.getValorMensalTotal();
        }
        System.out.printf("Receita Total: R$%.2f\n", receita);
    }

    private Condutor novoCondutor(Scanner scanner) {
        System.out.print("Insira o CPF do condutor: ");
        String cpf = scanner.nextLine();
        System.out.print("Insira o nome do condutor: ");
        String nome = scanner.nextLine();
        System.out.print("Insira o telefone do condutor: ");
        String telefone = scanner.nextLine();
        System.out.print("Insira o endereco do condutor: ");
        String endereco = scanner.nextLine();
        System.out.print("Insira o email do condutor: ");
        String email = scanner.nextLine();
        System.out.print("Insira a data de nascimento do condutor: ");
        String nascimento = scanner.nextLine();

        // Caso em que o documento e invalido
        if (!Validacao.validarDocumento(cpf, "CPF")) {
            System.out.println("CPF invalido. Nao foi possivel cadastrar o condutor.");
            return null;
        }

        // Caso em que o nome e invalido
        if (!Validacao.validarNome(nome)) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o condutor.");
            return null;
        }

        return new Condutor(cpf, nome, telefone, endereco, email, nascimento);
    }

    // Retorna todos os clientes PJ
    private ArrayList<ClientePJ> getClientesPJ() {
        ArrayList<ClientePJ> clientesPJ = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClientePJ) {
                clientesPJ.add((ClientePJ)cliente);
            }
        }
        return clientesPJ;
    }

    // Retorna todos os clientes PF
    private ArrayList<ClientePF> getClientesPF() {
        ArrayList<ClientePF> clientesPF = new ArrayList<>();
        for (Cliente cliente : listaClientes) {
            if (cliente instanceof ClientePF) {
                clientesPF.add((ClientePF)cliente);
            }
        }
        return clientesPF;
    }

    // Retorna todos os seguros de um cliente
    private ArrayList<Seguro> getSegurosPorCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento()[1].equals(documento)) {
                return cliente.getListaSeguros();
            }
        }
        return null;
    }

    // Retorna todos os sinistros de um cliente
    private ArrayList<Sinistro> getSinistrosPorCliente(String documento) {
        ArrayList<Sinistro> sinistros = new ArrayList<>();
        Cliente cliente = getCliente(documento);

        // Caso em que o cliente nao existe
        if (cliente == null) {
            return null;
        }
        // Iterando sobre todos os seguros do cliente
        for (Seguro seguro : cliente.getListaSeguros()) {
            sinistros.addAll(seguro.getListaSinistros());
        }

        return sinistros;
    }

    // Retorna o cliente atraves do documento
    private Cliente getCliente(String documento) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getDocumento()[1].equals(documento)) {
                return cliente;
            }
        }
        return null;
    }

    // Retorna o seguro atraves do id
    private Seguro getSeguro(int id) {
        for (Seguro seguro : listaSeguros) {
            if (seguro.getId() == id) {
                return seguro;
            }
        }
        return null;
    }

    // Retorna o sinistro atraves do id
    private Sinistro getSinistro(int id) {
        for (Seguro seguro : listaSeguros) {
            for (Sinistro sinistro : seguro.getListaSinistros()) {
                if (sinistro.getId() == id) {
                    return sinistro;
                }
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