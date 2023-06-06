package pacote;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class ClientePF extends Cliente{
    // Atributos
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNascimento;
    private ArrayList<Veiculo> listaVeiculos;

    // Construtor
    public ClientePF(String nome, String telefone, String endereco,
                    String email, String CPF, String genero,
                    String educacao, String dataNascimento) {
                        
        super(nome, telefone, endereco, email);
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNascimento = LocalDate.parse(dataNascimento, dtf); // Transformando String em LocalDate
        this.listaVeiculos = new ArrayList<>();
    }

    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataNascimentoString = getDataNascimento().format(dtf); // Transformando LocalDate em String
        joiner.add(super.toString());
        joiner.add("CPF: " + getCPF());
        joiner.add("Genero: " + getGenero());
        joiner.add("Educacao: " + getEducacao());
        joiner.add("Data Nascimento: " + dataNascimentoString);
        joiner.add("Valor Mensal Total: " + getValorMensalTotal());
        joiner.add("Veiculos: ");
        if (listaVeiculos.isEmpty()) {
            joiner.add("    Sem veiculos cadastrados.");
        } else {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                Veiculo veiculo = listaVeiculos.get(i);
                joiner.add(String.format("    Carro %d: %s - %s",
                                        i+1, veiculo.getModelo(), veiculo.getPlaca()));
            }
        }

        return joiner.toString();
    }

    // Listar todos os veiculos
    public void listarVeiculos() {
        // Caso em que nao ha veiculos cadastrados
        if (listaVeiculos.isEmpty()) {
            System.out.println("Nao ha veiculos cadastrados.");
            return;
        }

        System.out.println("Veiculos:");
        // Iterando sobre os veiculos
        for (Veiculo veiculo : listaVeiculos) {
            System.out.println("---------------------------------------------");
            System.out.println(veiculo);
        }
        System.out.println("---------------------------------------------");
    }

    // Cadastrar novo veiculo automatico
    public void cadastrarVeiculo(Veiculo veiculo) {
        // Caso em que a placa passada e invalida
        if (!Validacao.validarPlaca(veiculo.getPlaca())) {
            System.out.println("Placa invalida. Nao foi possivel cadastrar o veiculo.");
            return;
        }

        listaVeiculos.add(veiculo); // Veiculo adicionado
        System.out.println("Veiculo cadastrado!");
    }

    // Cadastrar novo veiculo com scanner
    public void cadastrarVeiculo(Scanner scanner) {
        System.out.print("Digite a placa: ");
        String placa = scanner.nextLine();
        System.out.print("Digite a marca: ");
        String marca = scanner.nextLine();
        System.out.print("Digite o modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Digite o ano de fabricacao: ");
        int ano = scanner.nextInt();

        Veiculo veiculo = new Veiculo(placa, marca, modelo, ano);

        cadastrarVeiculo(veiculo);
    }

    // Excluir veiculo automatico
    public void excluirVeiculo(Veiculo veiculo) {
        String placa = veiculo.getPlaca();

        // Checar se o veiculo existe antes de excluir
        if (listaVeiculos.contains(veiculo)) {
            listaVeiculos.remove(veiculo);
            System.out.printf("Veiculo de placa %s removido!\n", placa);
            return;
        }

        System.out.printf("Veiculo invalido. Nao foi possivel remover o veiculo de placa %s.\n", placa);
    }

    // Excluir veiculo com scanner
    public void excluirVeiculo(Scanner scanner) {
        System.out.print("Insira a placa do veiculo que deseja excluir: ");
        String placa = scanner.nextLine();

        for (Veiculo veiculo: listaVeiculos) {
            if (veiculo.getPlaca().equals(placa)) {
                listaVeiculos.remove(veiculo);
                System.out.printf("Veiculo de placa %s removido!\n", placa);
                return;
            }
        }

        System.out.printf("Veiculo invalido. Nao foi possivel remover o veiculo de placa %s.\n", placa);
    }

    // Listar todos os condutores
    public void listarCondutores() {
        return;
    }

    // Cadastrar novo condutor automatico
    public void cadastrarCondutor(Condutor condutor) {
        return;
    }

    // Cadastrar novo condutor com scanner
    public void cadastrarCondutor(Scanner scanner) {
        return;
    }

    // Excluir condutor automatico
    public void excluirCondutor(String cpf) {
        return;
    }

    // Excluir condutor com scanner
    public void excluirCondutor(Scanner scanner) {
        return;
    }

    // Retorna o documento do cliente
    public String[] getDocumento() {
        return new String [] {"CPF", this.CPF};
    }

    // Calcula idade baseado na data de nascimento do cliente
    public int getIdade() {
        LocalDate then = dataNascimento;
        LocalDate now = LocalDate.now();
        return Period.between(then, now).getYears();
    }


    // Getters e Setters
    public String getCPF() {
        return this.CPF;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEducacao() {
        return this.educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return this.listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
}