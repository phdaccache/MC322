package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sistema.Veiculo;

public class ArquivoVeiculo implements I_Arquivo<Veiculo> {
    @Override
    public boolean gravarDados(ArrayList<Veiculo> listaVeiculos) {
        return false;
    }

    @Override
    public ArrayList<Veiculo> lerDados() {
        ArrayList<Veiculo> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/veiculos.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3])));
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Veiculos carregados!");
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