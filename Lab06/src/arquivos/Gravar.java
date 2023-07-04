package arquivos;

import java.io.IOException;

import arquivos.arquivoObjeto.*;

public class Gravar {
    public static void gravarDados() throws IOException {
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        boolean status1 = seguradoras.gravarDados();
        if (status1) {
            System.out.println("Alteracoes nas seguradoras salvas!");
        }

        ArquivoClientePF clientesPF = new ArquivoClientePF();
        boolean status2 = clientesPF.gravarDados();
        if (status2) {
            System.out.println("Alteracoes nos clientes PF salvas!");
        }

        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        boolean status3 = clientesPJ.gravarDados();
        if (status3) {
            System.out.println("Alteracoes nos clientes PJ salvas!");
        }
    }
}