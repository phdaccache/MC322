package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoVeiculo implements I_Arquivo<Veiculo> {
    @Override
    public boolean gravarDados() {
        String header = "placa,marca,modelo,ano,idSeguro,cpfCliente/idFrota";
        File file = new File("src/arquivos/arquivosCSV/veiculos.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente instanceof ClientePF) {
                        for (Veiculo veiculo : ((ClientePF)cliente).getListaVeiculos()) {
                            String dados = getDados(veiculo);
                            escritor.write("\n");
                            escritor.write(dados);
                        }
                    }
                }
            }
            escritor.close();
            return true;
        } catch(Exception e){
            System.out.println("Erro ao gravar arquivo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<String[]> lerDados() {
        ArrayList<String[]> retorno = new ArrayList<>();

        try {
            File file = new File("src/arquivos/arquivosCSV/veiculos.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(dados);
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

    @Override
    public String getDados(Veiculo veiculo) {
        String dados = "";
        dados += veiculo.getPlaca() + ",";
        dados += veiculo.getMarca() + ",";
        dados += veiculo.getModelo() + ",";
        dados += veiculo.getAnoFabricacao() + ",";

        Seguro seguro = veiculo.getSeguro();
        if (seguro != null) {
            dados += seguro.getId() + ",";
        } else {
            dados += " ,";
        }

        for (Seguradora seguradora : Admin.listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente instanceof ClientePF) {  
                    if (((ClientePF)cliente).getListaVeiculos().contains(veiculo)) {
                        dados += cliente.getDocumento()[1];
                    }
                }
            }
        }

        return dados;
    }
}