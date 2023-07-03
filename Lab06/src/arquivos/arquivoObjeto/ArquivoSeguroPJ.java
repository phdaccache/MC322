package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoSeguroPJ implements I_Arquivo<SeguroPJ> {
    @Override
    public boolean gravarDados(ArrayList<SeguroPJ> listaSegurosPJ) {
        return false;
    }

    @Override
    public ArrayList<SeguroPJ> lerDados() {
        ArrayList<SeguroPJ> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/segurosPJ.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                Seguradora seguradora = Admin.getSeguradora(dados[4]);
                ClientePJ cliente = (ClientePJ)seguradora.getCliente(dados[1]);
                Frota frota = ((ClientePJ)cliente).getFrota(Integer.parseInt(dados[0]));
                retorno.add(new SeguroPJ(frota, cliente, dados[2], dados[3], seguradora));
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Seguros PJ carregados!");
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