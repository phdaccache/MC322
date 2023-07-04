package arquivos;

import java.io.IOException;

import arquivos.arquivoObjeto.ArquivoSeguradora;

public class Gravar {
    public static void gravarDados() throws IOException {
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        seguradoras.gravarDados();
    }
}
