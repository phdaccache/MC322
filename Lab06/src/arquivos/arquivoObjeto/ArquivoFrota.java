package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoFrota implements I_Arquivo<Frota> {
    @Override
    public boolean gravarDados() {
        String header = "idFrota,idSeguro,listaVeiculos,cnpjCliente";
        File file = new File("src/arquivos/arquivosCSV/frotas.csv");
        File file2 = new File("src/arquivos/arquivosCSV/veiculos.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            FileWriter escritor2 = new FileWriter(file2, true);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente instanceof ClientePJ) {
                        for (Frota frota : ((ClientePJ)cliente).getListaFrotas()) {
                            String dados = getDados(frota);
                            escritor.write("\n");
                            escritor.write(dados);
                            for (Veiculo veiculo : frota.getListaVeiculos()) {
                                String dados2 = getDados(veiculo, frota.getId(), frota);
                                escritor2.write("\n");
                                escritor2.write(dados2);
                            }
                        }
                    }
                }
            }
            escritor.close();
            escritor2.close();
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
            File file = new File("src/arquivos/arquivosCSV/frotas.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(dados);
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

    @Override
    public String getDados(Frota frota) {
        String dados = "";
        dados += frota.getId() + ",";

        Seguro seguro = frota.getSeguro();
        if (seguro != null) {
            dados += seguro.getId() + ",";
        } else {
            dados += " ,";
        }

        for (Veiculo veiculo : frota.getListaVeiculos()) {
            dados += veiculo.getPlaca() + ",";
        }
        dados += ",";

        for (Seguradora seguradora : Admin.listaSeguradoras) {
            for (Cliente cliente : seguradora.getListaClientes()) {
                if (cliente instanceof ClientePJ) {
                    for (Frota f : ((ClientePJ)cliente).getListaFrotas()) {
                        if (f.getId() == frota.getId()) {
                            dados += cliente.getDocumento()[1];
                        }
                    }
                }
            }
        }

        return dados;
    }

    public String getDados(Veiculo veiculo, int id, Frota frota) {
        String dados = "";
        dados += veiculo.getPlaca() + ",";
        dados += veiculo.getMarca() + ",";
        dados += veiculo.getModelo() + ",";
        dados += veiculo.getAnoFabricacao() + ",";

        Seguro seguro = frota.getSeguro();
        if (seguro != null) {
            dados += seguro.getId() + ",";
        } else {
            dados += " ,";
        }

        dados += id;

        return dados;
    }
}