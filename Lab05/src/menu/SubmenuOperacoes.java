package menu;

/* enum para os Submenus
 * Cada constante e uma opcao de todos os submenus
 */
public enum SubmenuOperacoes {
    // Admin
    LISTAR_SEGURADORAS("Listar seguradoras"),
    CADASTRAR_SEGURADORA("Cadastrar seguradora"),
    EXCLUIR_SEGURADORA("Excluir seguradora"),
    // Seguradora
    VISUALIZAR_DADOS_SEGURADORA("Visualizar dados"),
	LISTAR_CLIENTES("Listar clientes"),
    CADASTRAR_CLIENTE("Cadastrar cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	LISTAR_SEGUROS_SEGURADORA("Listar Seguros"),
    GERAR_SEGURO("Gerar Seguro"),
	CANCELAR_SEGURO("Cancelar Seguro"),
	GERAR_SINISTRO("Gerar sinistro"),
    EXCLUIR_SINISTRO("Excluir sinistro"),
    CALCULAR_RECEITA_SEGURADORA("Calcular receita"),
    // Cliente PF
    VISUALIZAR_DADOS_CLIENTE_PF("Visualizar dados"),
    LISTAR_SEGUROS_CLIENTE_PF("Listar Seguros"),
    VISUALIZAR_SEGURO_CLIENTE_PF("Visualizar Seguro"),
    LISTAR_VEICULOS("Listar veiculos"),
    CADASTRAR_VEICULO("Cadastrar veiculo"),
    EXCLUIR_VEICULO("Excluir veiculo"),
    LISTAR_CONDUTORES("Listar Condutores"),
    CADASTRAR_CONDUTOR("Cadastrar Condutor"),
    EXCLUIR_CONDUTOR("Excluir Condutor"),
    // Cliente PJ
    VISUALIZAR_DADOS_CLIENTE_PJ("Visualizar dados"),
    LISTAR_SEGUROS_CLIENTE_PJ("Listar Seguros"),
    VISUALIZAR_SEGURO_CLIENTE_PJ("Visualizar Seguro"),
    LISTAR_FROTAS("Listar Frotas"),
    VISUALIZAR_FROTA("Visualizar Frota"),
    CADASTRAR_FROTA("Cadastrar Frota"),
    ATUALIZAR_FROTA("Atualizar Frota"),
    // Comum
	VOLTAR("Voltar");
	
    // Atributo
	private final String name;
	
    // Construtor
	SubmenuOperacoes(String name){
		this.name = name;
	}
	
    // Getter
	public String getName() {
		return name;
	}
}