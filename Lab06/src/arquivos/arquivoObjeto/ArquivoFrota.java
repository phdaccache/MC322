package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sistema.Frota;

public class ArquivoFrota implements I_Arquivo<Frota> {
    @Override
    public boolean gravarDados(ArrayList<Frota> listaFrotas) {
        return false;
    }

    @Override
    public ArrayList<Frota> lerDados() {
        ArrayList<Frota> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/frotas.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(new Frota(Integer.parseInt(dados[0])));
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Frotas carregadas!");
            }
            return retorno;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }
}
