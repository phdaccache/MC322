package menu;

/* enum para os Submenus
 * Cada constante e uma opcao de todos os submenus
 */
public enum SubmenuOperacoes {
    CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS_SEGURADORA("Listar sinistros por seguradora"),
	LISTAR_SINISTROS_CLIENTE("Listar sinistros por cliente"),
	LISTAR_VEICULOS_SEGURADORA("Listar veiculos por seguradora"),
	LISTAR_VEICULOS_CLIENTE("Listar veiculos por cliente"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SINISTRO("Excluir sininstro"),
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
