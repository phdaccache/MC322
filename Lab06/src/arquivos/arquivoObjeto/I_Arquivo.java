package arquivos.arquivoObjeto;

import java.util.ArrayList;

public interface I_Arquivo<T> {
    public boolean gravarDados(ArrayList<T> listaObjetos);
    public ArrayList<T> lerDados();
}