package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoSeguroPF implements I_Arquivo<SeguroPF> {
    @Override
    public boolean gravarDados(ArrayList<SeguroPF> listaSegurosPF) {
        return false;
    }

    @Override
    public ArrayList<SeguroPF> lerDados() {
        ArrayList<SeguroPF> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/segurosPF.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                Seguradora seguradora = Admin.getSeguradora(dados[4]);
                ClientePF cliente = (ClientePF)seguradora.getCliente(dados[1]);
                Veiculo veiculo = ((ClientePF)cliente).getVeiculo(dados[0]);
                retorno.add(new SeguroPF(veiculo, cliente, dados[2], dados[3], seguradora));
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Seguros PF carregados!");
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