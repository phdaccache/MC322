package arquivos.arquivoObjeto;

import java.util.ArrayList;

public interface I_Arquivo<T> {
    public boolean gravarDados();
    public ArrayList<String[]> lerDados();
    public String getDados(T objeto);

    // Em getDados, usei replace(',', ';') para substituir as virgulas por ponto e virgula
    // Isso foi feito para nao atrapalhar a leitura do arquivo csv (que faz um split com a virgula)
    // Na hora de ler os arquivos, usei replace(';', ',') para substituir o ponto e virgula por virgula novamente
    // Isso foi feito no arquivo 'Carregar.java', hora de carregar os dados

    // Alem disso, paras as listas, coloquei um espaco em branco caso nao tenha nenhum elemento
    // Isso foi feito para nao dar o erro de index out of bounds na hora de carregar os dados
}