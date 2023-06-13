package menu;

/* enum para o Menu
 * Cada constante e uma opcao do menu, que tem um nome e uma lista de submenus
 */
public enum MenuOperacoes {
    ADMIN("Admin", new SubmenuOperacoes[] {
            SubmenuOperacoes.LISTAR_SEGURADORAS,
            SubmenuOperacoes.CADASTRAR_SEGURADORA,
            SubmenuOperacoes.EXCLUIR_SEGURADORA,
            SubmenuOperacoes.VOLTAR}
    ),
    SEGURADORA("Area Seguradora", new SubmenuOperacoes[] {
            SubmenuOperacoes.VISUALIZAR_DADOS_SEGURADORA,
            SubmenuOperacoes.LISTAR_CLIENTES,
            SubmenuOperacoes.CADASTRAR_CLIENTE,
            SubmenuOperacoes.EXCLUIR_CLIENTE,
            SubmenuOperacoes.LISTAR_SEGUROS_SEGURADORA,
            SubmenuOperacoes.GERAR_SEGURO,
            SubmenuOperacoes.CANCELAR_SEGURO,
            SubmenuOperacoes.GERAR_SINISTRO,
            SubmenuOperacoes.EXCLUIR_SINISTRO,
            SubmenuOperacoes.CALCULAR_RECEITA_SEGURADORA,
            SubmenuOperacoes.VOLTAR
    }),
    CLIENTE_PJ("Area Cliente - Pessoa Juridica", new SubmenuOperacoes[] {
            SubmenuOperacoes.VISUALIZAR_DADOS_CLIENTE_PJ,
            SubmenuOperacoes.LISTAR_SEGUROS_CLIENTE_PJ,
            SubmenuOperacoes.VISUALIZAR_SEGURO_CLIENTE_PJ,
            SubmenuOperacoes.LISTAR_CONDUTORES_PJ,
            SubmenuOperacoes.CADASTRAR_CONDUTOR_PJ,
            SubmenuOperacoes.EXCLUIR_CONDUTOR_PJ,
            SubmenuOperacoes.LISTAR_FROTAS,
            SubmenuOperacoes.VISUALIZAR_FROTA,
            SubmenuOperacoes.CADASTRAR_FROTA,
            SubmenuOperacoes.ATUALIZAR_FROTA,
            SubmenuOperacoes.VOLTAR
    }),
    CLIENTE_PF("Area Cliente - Pessoa Fisica", new SubmenuOperacoes[] {
            SubmenuOperacoes.VISUALIZAR_DADOS_CLIENTE_PF,
            SubmenuOperacoes.LISTAR_SEGUROS_CLIENTE_PF,
            SubmenuOperacoes.VISUALIZAR_SEGURO_CLIENTE_PF,
            SubmenuOperacoes.LISTAR_CONDUTORES_PF,
            SubmenuOperacoes.CADASTRAR_CONDUTOR_PF,
            SubmenuOperacoes.EXCLUIR_CONDUTOR_PF,
            SubmenuOperacoes.LISTAR_VEICULOS,
            SubmenuOperacoes.CADASTRAR_VEICULO,
            SubmenuOperacoes.EXCLUIR_VEICULO,
            SubmenuOperacoes.VOLTAR
    }),
    SAIR("Sair");

    // Atributos
    private final String name;
    private final SubmenuOperacoes[] subOptions;

    // Construtor
    MenuOperacoes(String name, SubmenuOperacoes ... subOptions) {
        this.name = name;
        this.subOptions = subOptions;
    }

    // Getters
    public String getName() {
        return name;
    }

    public SubmenuOperacoes[] getSubOptions() {
        return subOptions;
    }
}