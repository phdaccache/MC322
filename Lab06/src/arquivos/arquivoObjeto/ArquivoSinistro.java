package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArquivoSinistro implements I_Arquivo {
    @Override
    public boolean gravarDados() {
        return false;
    }

    @Override
    public ArrayList<String[]> lerDados() {
        ArrayList<String[]> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/sinistros.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                // Seguro seguro = null;
                // for (Seguradora seguradora : Admin.listaSeguradoras) {
                //     Seguro seg;
                //     if ((seg = seguradora.getSeguro(Integer.parseInt(dados[3]))) != null) {
                //         seguro = seg;
                //     }
                // }
                // Condutor condutor = seguro.getCondutor(dados[2]);
                // retorno.add(new Sinistro(dados[0], dados[1], condutor, seguro));
                retorno.add(dados);
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Sinistros carregados!");
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