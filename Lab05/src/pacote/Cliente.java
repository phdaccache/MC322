package pacote;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public abstract class Cliente {
    // Atributos
    private String nome;
    private String telefone;   
    private String endereco;
    private String email;
    private Seguradora seguradora;
    private double valorMensalTotal;
    private ArrayList<Seguro> listaSeguros;

    // Construtor
    public Cliente(String nome, String telefone, String endereco, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.seguradora = null;
        this.valorMensalTotal = 0;
        this.listaSeguros = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Nome: " + getNome());
        joiner.add("Telefone: " + getTelefone());
        joiner.add("Endereco: " + getEndereco());
        joiner.add("Email: " + getEmail());
        joiner.add(String.format("Seguradora: %s (CNPJ: %s)",
                                getSeguradora().getNome(), getSeguradora().getCNPJ()));
        joiner.add(String.format("Valor mensal total: R$%.2f", getValorMensalTotal()));

        return joiner.toString();
    }

    // Imprime os dados do cliente pelo toString()
    public void visualizarDados() {
        System.out.println("Dados do cliente:");
        System.out.println(toString());
    }

    // Lista todos os seguros do cliente
    public void listarSeguros() {
        System.out.println("Seguros:");
        // Caso em que nao ha seguros gerados
        if (listaSeguros.isEmpty()) {
            System.out.println("---------------------------------------------");
            System.out.println("Nao ha seguros gerados.");
            System.out.println("---------------------------------------------");
            return;
        }
        // Iterando sobre os seguros
        for (Seguro seguro : listaSeguros) {
            System.out.println("---------------------------------------------");
            System.out.printf("Seguro de ID: %03d:\n", seguro.getId());
            System.out.printf("Data inicio: %s\n", seguro.getDataInicio());
            System.out.printf("Data fim: %s\n", seguro.getDataFim());
            System.out.printf("Valor Mensal: R$%.2f\n", seguro.getValorMensal());
            System.out.printf("Quantidade de sinistros: %d\n", seguro.getListaSinistros().size());
            System.out.printf("Quantidade de condutores: %d\n", seguro.getListaCondutores().size()); 
        }
        System.out.println("---------------------------------------------");
    }

    // Visualizar unico seguro (com mais detalhes do que a listagem normal) automatico
    public void visualizarSeguro(int id) {
        Seguro seguro = getSeguro(id);

        // Caso em que o seguro nao foi encontrado
        if (seguro == null) {
            System.out.println("---------------------------------------------");
            System.out.println("Seguro nao encontrado.");
            System.out.println("---------------------------------------------");
            return;
        }
        System.out.println("---------------------------------------------");
        System.out.println(seguro);
        System.out.println("---------------------------------------------");
    }

    // Visualizar unico seguro (com mais detalhes do que a listagem normal) com scanner
    public void visualizarSeguro(Scanner scanner) {
        System.out.print("Insira o ID do seguro que deseja visualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        visualizarSeguro(id);
    }

    // Adicionar seguro
    public void adicionarSeguro(Seguro seguro) {
        listaSeguros.add(seguro);
    }

    // Excluir seguro
    public void excluirSeguro(Seguro seguro) {
        listaSeguros.remove(seguro);
    }

    // Listar todos os condutores
    public void listarCondutores() {
        // Caso em que nao ha seguros gerados
        if (listaSeguros.isEmpty()) {
            System.out.println("Nao ha seguros gerados.");
            return;
        }

        // Iterando sobre os seguros
        for (Seguro seguro : listaSeguros) {
            System.out.printf("Seguro %d:\n", seguro.getId());
            // Iterando sobre os condutores
            for (Condutor condutor : seguro.getListaCondutores()) {
                System.out.println("---------------------------------------------");
                System.out.println(condutor);
            }
            System.out.println("---------------------------------------------");
        }
    }

    // Cadastrar novo condutor automatico
    public void cadastrarCondutor(Condutor condutor, int idSeguro) {
        Seguro seguro = getSeguro(idSeguro);

        // Caso em que o cliente nao tem seguros
        if (listaSeguros.isEmpty()) {
            System.out.println("O cliente nao tem seguros.");
            return;
        }

        // Caso em que o seguro nao foi encontrado
        if (seguro == null) {
            System.out.printf("Seguro de ID %03d nao encontrado.\n", idSeguro);
            return;
        }

        seguro.autorizarCondutor(condutor);
    }

    // Cadastrar novo condutor com scanner
    public void cadastrarCondutor(Scanner scanner) {
        System.out.print("Insira o ID do seguro que deseja adicionar o condutor: ");
        int idSeguro = scanner.nextInt();
        scanner.nextLine();
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
            return;
        }

        // Caso em que o nome e invalido
        if (!Validacao.validarNome(nome)) {
            System.out.println("Nome invalido. Nao foi possivel cadastrar o condutor.");
            return;
        }

        cadastrarCondutor(new Condutor(cpf, nome, telefone, endereco, email, nascimento), idSeguro);
    }

    // Excluir condutor automatico
    public void excluirCondutor(String cpf, int idSeguro) {
        Seguro seguro = getSeguro(idSeguro);

        // Caso em que o ID do seguro nao foi encontrado
        if (seguro == null) {
            System.out.printf("Seguro de ID %03d nao encontrado.\n", idSeguro);
            return;
        }

        // Iterando sobre os condutores
        for (Condutor condutor : seguro.getListaCondutores()) {
            // Caso em que o condutor foi encontrado
            if (condutor.getCPF().equals(cpf)) {
                seguro.desautorizarCondutor(condutor);
                return;
            }
        }
        System.out.printf("Condutor de CPF %s nao encontrado.\n", cpf);
    }

    // Excluir condutor com scanner
    public void excluirCondutor(Scanner scanner) {
        System.out.print("Insira o ID do seguro que deseja excluir o condutor: ");
        int idSeguro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Insira o CPF do condutor: ");
        String cpf = scanner.nextLine();

        excluirCondutor(cpf, idSeguro);
    }

    // Calcular valor total mensal
    public double calcularValorMensalTotal() {
        double valorMensalTotal = 0;
        // Iterando sobre os seguros
        for (Seguro seguro: listaSeguros) {
            // Somando o valor mensal de cada seguro
            valorMensalTotal += seguro.getValorMensal();
        }
        return valorMensalTotal;
    }

    // Retorna o seguro atraves do ID
    public Seguro getSeguro(int id) {
        for (Seguro seguro : listaSeguros) {
            if (seguro.getId() == id) {
                return seguro;
            }
        }
        return null;
    }

    // Retorna o documento do cliente
    public abstract String[] getDocumento();


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

    public Seguradora getSeguradora() {
        return this.seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public double getValorMensalTotal() {
        return this.valorMensalTotal;
    }

    public void setValorMensalTotal(double valorMensalTotal) {
        this.valorMensalTotal = valorMensalTotal;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return this.listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }
}