package sistema;

/* enum para calcular o valor do seguro
 * A primeira constante e um valor base
 * As proximas constantes sao fatores que variam com a idade
 */
public enum CalcSeguro {
    VALOR_BASE(10),
    // Idade entre 18 e 30
    FATOR_18_30(1.25),
    // Idade entre 30 e 60
    FATOR_30_60(1.0),
    // Idade maior que 60
    FATOR_60(1.5);

    // Atributo
    private double valor;

    // Construtor
    CalcSeguro(double valor) {
        this.valor = valor;
    }

    // Getter
    public double getValor() {
        return valor;
    }
}