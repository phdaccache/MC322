package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import sistema.*;

public class ArquivoSeguroPJ implements I_Arquivo<SeguroPJ> {
    @Override
    public boolean gravarDados() {
        String header = "idSeguro,inicio,fim,cnpjSeguradora,cpfCliente,valorMensal,listaSinistros,listaCondutores,idFrota";
        File file = new File("src/arquivos/arquivosCSV/segurosPJ.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    if (seguro instanceof SeguroPJ) {
                        String dados = getDados((SeguroPJ)seguro);
                        escritor.write("\n");
                        escritor.write(dados);
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
            File file = new File("src/arquivos/arquivosCSV/segurosPJ.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                // Seguradora seguradora = Admin.getSeguradora(dados[4]);
                // ClientePJ cliente = (ClientePJ)seguradora.getCliente(dados[1]);
                // Frota frota = ((ClientePJ)cliente).getFrota(Integer.parseInt(dados[0]));
                // retorno.add(new SeguroPJ(frota, cliente, dados[2], dados[3], seguradora));
                retorno.add(dados);
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

    @Override
    public String getDados(SeguroPJ seguro) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += seguro.getId() + ",";
        dados += seguro.getDataInicio().format(dtf) + ",";
        dados += seguro.getDataFim().format(dtf) + ",";
        dados += seguro.getSeguradora().getCNPJ() + ",";
        dados += seguro.getCliente().getDocumento()[1] + ",";
        dados += seguro.getValorMensal() + ",";

        dados += "\"";
        for (Sinistro sinistro : seguro.getListaSinistros()) {
            dados += sinistro.getId() + ",";
        }
        dados += "\",";

        dados += "\"";
        for (Condutor condutor : seguro.getListaCondutores()) {
            dados += condutor.getCPF() + ",";
        }
        dados += "\",";

        dados += seguro.getFrota().getId();

        return dados;
    }
}