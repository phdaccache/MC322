package pacote;

public class Validacao {
    // Validar CPF
    public static boolean validarCPF(String cpf) {
        String cpfNum = cpf.replaceAll("[^0-9]", ""); // Retira todos os caracteres não numericos
        int tam = cpfNum.length();

        if (tam != 11) {
            return false;
        }
        if (digitosIguais(cpfNum)) {
            return false;
        }
        if (!digitosVerificadoresCPF(cpfNum)) {
            return false;
        }

        return true;
    }

    // Validar CNPJ
    public static boolean validarCNPJ(String cnpj) {
        String cnpjNum = cnpj.replaceAll("[^0-9]", ""); // Retira todos os caracteres não numericos
        int tam = cnpjNum.length();

        if (tam != 14) {
            return false;
        }
        if (digitosIguais(cnpjNum)) {
            return false;
        }
        if (!digitosVerificadoresCNPJ(cnpjNum)) {
            return false;
        }

        return true;
    }

    // Validar nome
    public static boolean validarNome(String nome) {
        String nomeFormatado = nome.replaceAll("[^A-Za-z ]", "");
        if (nome.equals(nomeFormatado)) {
            return true;
        }

        return false;
    }

    // Validar placa
    public static boolean validarPlaca(String placa) {
        if (placa.matches("[A-Z]{3}-[0-9]{4}") ||
                        placa.matches("[A-Z]{3}-[0-9][A-Z][0-9]{2}")) {
            return true;
        }

        return false;
    }


    // Verificar se os digitos sao iguais
    private static boolean digitosIguais(String id){
        char firstChar = id.charAt(0);
        for (int i = 1; i < id.length(); i++) {
            if (id.charAt(i) != firstChar) {
                return false;
            }
        }

        return true;
    }

    // Verificar digitos verificadores do CPF
    private static boolean digitosVerificadoresCPF(String cpf){
        int num, r1, r2, d1, d2;
        int sum1 = 0, sum2 = 0;

        // Primeiro digito verificador
        // Soma com pesos
        for (int i = 0, j = 10; i < cpf.length() - 2; i++, j--) {
            num = (int)(cpf.charAt(i) - 48);
            sum1 += num * j;
        }

        // Calculo do digito verificador
        r1 = sum1 % 11;
        if (r1 == 0 || r1 == 1) {
            d1 = 0;
        } else {
            d1 = 11 - r1;
        }

        // Comparacao do digito calculado com o digito passado
        if (d1 != (int)(cpf.charAt(9) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        // Segundo digito verificador
        // Soma com pesos
        for (int i = 1, j = 10; i < cpf.length() - 1; i++, j--) {
            num = (int)(cpf.charAt(i) - 48); // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            sum2 += num * j;
        }

        // Cálculo do digito verificador
        r2 = sum2 % 11;
        if (r2 == 0 || r2 == 1) {
            d2 = 0;
        } else {
            d2 = 11 - r2;
        }

        // Comparacao do digito calculado com o digito passado
        if (d2 != (int)(cpf.charAt(10) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        return true;
    }

    // Verificar digitos verificadores do CNPJ
    private static boolean digitosVerificadoresCNPJ(String cnpj){
        int num, r1, r2, d1, d2;
        int sum1 = 0, sum2 = 0;

        // Primeiro digito verificador
        // Soma com pesos
        for (int i = 0, j = 5; i < cnpj.length() - 2; i++, j--) {
            num = (int)(cnpj.charAt(i) - 48);
            sum1 += num * j;

            if (j == 2) {
                j = 10;
            }
        }

        // Cálculo do digito verificador
        r1 = sum1 % 11;
        if (r1 == 0 || r1 == 1) {
            d1 = 0;
        } else {
            d1 = 11 - r1;
        }

        // Comparacao do digito calculado com o digito passado
        if (d1 != (int)(cnpj.charAt(12) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        // Segundo digito verificador
        // Soma com pesos
        for (int i = 0, j = 6; i < cnpj.length() - 1; i++, j--) {
            num = (int)(cnpj.charAt(i) - 48); // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            sum2 += num * j;

            if (j == 2) {
                j = 10;
            }
        }

        // Cálculo do digito verificador
        r2 = sum2 % 11;
        if (r2 == 0 || r2 == 1) {
            d2 = 0;
        } else {
            d2 = 11 - r2;
        }

        // Comparacao do digito calculado com o digito passado
        if (d2 != (int)(cnpj.charAt(13) - 48)) { // Subtraio 48 ('0' na tabelas ASCII) para transformar em inteiro
            return false;
        }

        return true;
    }
}