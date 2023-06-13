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
	LISTAR_SEGUROS_SEGURADORA("Listar Todos os Seguros"),
    LISTAR_SEGUROS_SEGURADORA_CLIENTE("Listar Seguros por Cliente"),
    GERAR_SEGURO("Gerar Seguro"),
	CANCELAR_SEGURO("Cancelar Seguro"),
    LISTAR_SINISTROS_SEGURADORA("Listar Sinistros"),
    LISTAR_SINISTROS_SEGURADORA_CLIENTE("Listar Sinistros por Cliente"),
	GERAR_SINISTRO("Gerar sinistro"),
    EXCLUIR_SINISTRO("Excluir sinistro"),
    CALCULAR_RECEITA_SEGURADORA("Calcular receita"),
    // Cliente PJ
    VISUALIZAR_DADOS_CLIENTE_PJ("Visualizar dados"),
    LISTAR_SEGUROS_CLIENTE_PJ("Listar Seguros"),
    VISUALIZAR_SEGURO_CLIENTE_PJ("Visualizar Seguro"),
    LISTAR_CONDUTORES_PJ("Listar Condutores"),
    CADASTRAR_CONDUTOR_PJ("Cadastrar Condutor"),
    EXCLUIR_CONDUTOR_PJ("Excluir Condutor"),
    LISTAR_FROTAS("Listar Frotas"),
    VISUALIZAR_FROTA("Visualizar Frota"),
    CADASTRAR_FROTA("Cadastrar Frota"),
    ATUALIZAR_FROTA("Atualizar Frota"),
    // Cliente PF
    VISUALIZAR_DADOS_CLIENTE_PF("Visualizar dados"),
    LISTAR_SEGUROS_CLIENTE_PF("Listar Seguros"),
    VISUALIZAR_SEGURO_CLIENTE_PF("Visualizar Seguro"),
    LISTAR_CONDUTORES_PF("Listar Condutores"),
    CADASTRAR_CONDUTOR_PF("Cadastrar Condutor"),
    EXCLUIR_CONDUTOR_PF("Excluir Condutor"),
    LISTAR_VEICULOS("Listar veiculos"),
    CADASTRAR_VEICULO("Cadastrar veiculo"),
    EXCLUIR_VEICULO("Excluir veiculo"),
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