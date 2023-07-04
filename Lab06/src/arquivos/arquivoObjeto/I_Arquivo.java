package arquivos.arquivoObjeto;

import java.util.ArrayList;

public interface I_Arquivo {
    public boolean gravarDados();
    public ArrayList<String[]> lerDados();
}