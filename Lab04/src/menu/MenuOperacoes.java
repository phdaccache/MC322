package menu;

public enum MenuOperacoes {
    CADASTROS("Cadastros", new SubmenuOperacoes[] {
            SubmenuOperacoes.CADASTRAR_CLIENTE,
            SubmenuOperacoes.CADASTRAR_VEICULO,
            SubmenuOperacoes.CADASTRAR_SEGURADORA,
            SubmenuOperacoes.VOLTAR
    }),
    LISTAR("Listar", new SubmenuOperacoes[] {
            SubmenuOperacoes.LISTAR_CLIENTES,
            SubmenuOperacoes.LISTAR_SINISTROS_SEGURADORA,
            SubmenuOperacoes.LISTAR_SINISTROS_CLIENTE,
            SubmenuOperacoes.LISTAR_VEICULOS_SEGURADORA,
            SubmenuOperacoes.LISTAR_VEICULOS_CLIENTE,
            SubmenuOperacoes.VOLTAR
    }),
    EXCLUIR("Excluir", new SubmenuOperacoes[] {
            SubmenuOperacoes.EXCLUIR_CLIENTE,
            SubmenuOperacoes.EXCLUIR_VEICULO,
            SubmenuOperacoes.EXCLUIR_SINISTRO,
            SubmenuOperacoes.VOLTAR}),
    GERAR_SINISTRO("Gerar Sinistro"),
    TRANSFERIR_SEGURO("Transferir Seguro"),
    CALCULAR_RECEITA_SEGURADORA("Calcular Receita Seguradora"),
    SAIR("Sair");

    private String name;
    private SubmenuOperacoes[] subOptions;

    MenuOperacoes(String name, SubmenuOperacoes ... subOptions) {
        this.name = name;
        this.subOptions = subOptions;
    }

    public String getName() {
        return name;
    }

    public SubmenuOperacoes[] getSubOptions() {
        return subOptions;
    }
}
