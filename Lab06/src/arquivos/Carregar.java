package arquivos;

import java.util.ArrayList;

import arquivos.arquivoObjeto.ArquivoSeguradora;
import sistema.Admin;
import sistema.Seguradora;

public class Carregar {
    public static void carregarDados() {
        // Instanciando o arquivo
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        // Fazendo a leitura do arquivo
        ArrayList<Seguradora> listaSeguradoras = seguradoras.lerDados();
        // Instanciando e atualizando objeto
        if (listaSeguradoras != null) {
            for (Seguradora seguradora : listaSeguradoras) {
                Admin.cadastrarSeguradora(seguradora);
            }
        }

        
    }
}