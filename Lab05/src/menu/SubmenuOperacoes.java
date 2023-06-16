package menu;

/* enum para os Submenus
 * Cada constante e uma opcao de todos os submenus
 */
public enum SubmenuOperacoes {
    // Admin
    LISTAR_SEGURADORAS("Listar Seguradoras"),
    CADASTRAR_SEGURADORA("Cadastrar Seguradora"),
    EXCLUIR_SEGURADORA("Excluir Seguradora"),
    // Seguradora
    VISUALIZAR_DADOS_SEGURADORA("Visualizar Dados"),
	LISTAR_CLIENTES("Listar Clientes"),
    CADASTRAR_CLIENTE("Cadastrar Cliente"),
	EXCLUIR_CLIENTE("Excluir Cliente"),
	LISTAR_SEGUROS_SEGURADORA("Listar Todos os Seguros"),
    LISTAR_SEGUROS_SEGURADORA_CLIENTE("Listar Seguros por Cliente"),
    GERAR_SEGURO("Gerar Seguro"),
	CANCELAR_SEGURO("Cancelar Seguro"),
    LISTAR_SINISTROS_SEGURADORA("Listar Sinistros"),
    LISTAR_SINISTROS_SEGURADORA_CLIENTE("Listar Sinistros por Cliente"),
	GERAR_SINISTRO("Gerar Cinistro"),
    EXCLUIR_SINISTRO("Excluir Cinistro"),
    CALCULAR_RECEITA_SEGURADORA("Calcular Receita"),
    // Cliente PJ
    VISUALIZAR_DADOS_CLIENTE_PJ("Visualizar Dados"),
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
    VISUALIZAR_DADOS_CLIENTE_PF("Visualizar Dados"),
    LISTAR_SEGUROS_CLIENTE_PF("Listar Seguros"),
    VISUALIZAR_SEGURO_CLIENTE_PF("Visualizar Seguro"),
    LISTAR_CONDUTORES_PF("Listar Condutores"),
    CADASTRAR_CONDUTOR_PF("Cadastrar Condutor"),
    EXCLUIR_CONDUTOR_PF("Excluir Condutor"),
    LISTAR_VEICULOS("Listar Veiculos"),
    CADASTRAR_VEICULO("Cadastrar Veiculo"),
    EXCLUIR_VEICULO("Excluir Veiculo"),
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