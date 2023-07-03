package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sistema.Condutor;

public class ArquivoCondutor implements I_Arquivo<Condutor> {
    @Override
    public boolean gravarDados(ArrayList<Condutor> listaCondutores) {
        return false;
    }

    @Override
    public ArrayList<Condutor> lerDados() {
        ArrayList<Condutor> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/condutores.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(new Condutor(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]));
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Condutores carregados!");
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