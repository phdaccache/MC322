package menu;

/* enum para o Menu
 * Cada constante e uma opcao do menu, que tem um nome e uma lista de submenus
 */
public enum MenuOperacoes {
    ADMIN("Admin", new SubmenuOperacoes[] {
            SubmenuOperacoes.CADASTRAR_SEGURADORA,
            SubmenuOperacoes.EXCLUIR_SEGURADORA,
            SubmenuOperacoes.LISTAR_CLIENTES_SEGURADORA,
            SubmenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
            SubmenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
            SubmenuOperacoes.VOLTAR}
    ),
    SEGURADORA("Seguradora", new SubmenuOperacoes[] {
            SubmenuOperacoes.VISUALIZAR_DADOS_SEGURADORA,
            SubmenuOperacoes.LISTAR_CLIENTES,
            SubmenuOperacoes.CADASTRAR_CLIENTE,
            SubmenuOperacoes.EXCLUIR_CLIENTE,
            SubmenuOperacoes.LISTAR_SINISTROS_CLIENTE,
            SubmenuOperacoes.GERAR_SINISTRO,
            SubmenuOperacoes.EXCLUIR_SINISTRO,
            SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE,
            SubmenuOperacoes.TRANSFERIR_SEGURO,
            SubmenuOperacoes.CALCULAR_RECEITA_SEGURADORA,
            SubmenuOperacoes.VOLTAR
    }),
    CLIENTE("Cliente", new SubmenuOperacoes[] {
            SubmenuOperacoes.VISUALIZAR_DADOS_CLIENTE,
            SubmenuOperacoes.LISTAR_VEICULOS,
            SubmenuOperacoes.CADASTRAR_VEICULO,
            SubmenuOperacoes.EXCLUIR_VEICULO,
            SubmenuOperacoes.LISTAR_SINISTROS,
            SubmenuOperacoes.VOLTAR}),
    SAIR("Sair");

    // Atributos
    private String name;
    private SubmenuOperacoes[] subOptions;

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
