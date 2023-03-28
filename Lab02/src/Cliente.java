public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }

    public String toString() {
        return String.format("Nome: %s.\nCPF: %s.\nData de Nascimento: %s.\nIdade: %d.\nEndereço: %s.\n", getNome(), getCpf(), getDataNascimento(), getIdade(), getEndereco());
    }

    private boolean digitosIguais(String cpf){
        char firstChar = cpf.charAt(0);
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != firstChar) {
                return false;
            }
        }

        return true;
    }

    private boolean digitosVerificadores(String cpf){
        int num, r1, r2, d1, d2;
        int sum1 = 0, sum2 = 0;

        // Primeiro dígito verificador
        for (int i = 0, j = 10; i < cpf.length() - 2; i++, j--) {
            num = (int)(cpf.charAt(i) - 48);
            sum1 += num * j;
        }

        r1 = sum1 % 11;
        if (r1 == 0 || r1 == 1) {
            d1 = 0;
        } else {
            d1 = 11 - r1;
        }

        if (d1 != (int)(cpf.charAt(9) - 48)) {
            return false;
        }

        // Segundo dígito verificador
        for (int i = 1, j = 10; i < cpf.length() - 1; i++, j--) {
            num = (int)(cpf.charAt(i) - 48);
            sum2 += num * j;
        }

        r2 = sum2 % 11;
        if (r2 == 0 || r2 == 1) {
            d2 = 0;
        } else {
            d2 = 11 - r2;
        }

        if (d2 != (int)(cpf.charAt(10) - 48)) {
            return false;
        }

        return true;
    }

    public boolean validarCPF(String cpf_original){ 
        String cpf_num = cpf_original.replaceAll("[^0-9]", "");
        int tam = cpf_num.length();

        if (tam != 11) {
            return false;
        }
        if (digitosIguais(cpf_num)) {
            return false;
        }
        if (!digitosVerificadores(cpf_num)) {
            return false;
        }

        return true;
    }

    // Getters e Setters
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
}
