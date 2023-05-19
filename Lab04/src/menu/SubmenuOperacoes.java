package menu;

/* enum para os Submenus
 * Cada constante e uma opcao de todos os submenus
 */
public enum SubmenuOperacoes {
    // Admin
    CADASTRAR_SEGURADORA("Cadastrar seguradora"),
    EXCLUIR_SEGURADORA("Excluir seguradora"),
    LISTAR_CLIENTES_SEGURADORA("Listar cliente por seguradora"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculos por seguradora"),
    // Seguradora
    VISUALIZAR_DADOS_SEGURADORA("Visualizar dados"),
	LISTAR_CLIENTES("Listar cliente"),
    CADASTRAR_CLIENTE("Cadastrar cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
    GERAR_SINISTRO("Gerar sinistro"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
    TRANSFERIR_SEGURO("Trasnferir seguro"),
    CALCULAR_RECEITA_SEGURADORA("Calcular receita"),
    // Cliente
    VISUALIZAR_DADOS_CLIENTE("Visualizar dados"),
    LISTAR_VEICULOS("Listar veiculos"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	EXCLUIR_VEICULO("Excluir veiculo"),
    LISTAR_SINISTROS("Listar sinistros"),
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
