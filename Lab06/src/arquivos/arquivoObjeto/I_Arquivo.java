package arquivos.arquivoObjeto;

import java.util.ArrayList;

public interface I_Arquivo<T> {
    public boolean gravarDados();
    public ArrayList<String[]> lerDados();
    public String getDados(T objeto);
}