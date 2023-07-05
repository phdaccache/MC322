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

public class ArquivoSeguroPF implements I_Arquivo<SeguroPF> {
    @Override
    public boolean gravarDados() {
        String header = "idSeguro,inicio,fim,cnpjSeguradora,cpfCliente,valorMensal,listaSinistros,listaCondutores,placaVeiculo";
        File file = new File("src/arquivos/arquivosCSV/segurosPF.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    if (seguro instanceof SeguroPF) {
                        String dados = getDados((SeguroPF)seguro);
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
            File file = new File("src/arquivos/arquivosCSV/segurosPF.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                //Seguradora seguradora = Admin.getSeguradora(dados[4]);
                //ClientePF cliente = (ClientePF)seguradora.getCliente(dados[1]);
                //Veiculo veiculo = ((ClientePF)cliente).getVeiculo(dados[0]);
                //retorno.add(new SeguroPF(veiculo, cliente, dados[2], dados[3], seguradora));
                retorno.add(dados);
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

    @Override
    public String getDados(SeguroPF seguro) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += seguro.getId() + ",";
        dados += seguro.getDataInicio().format(dtf) + ",";
        dados += seguro.getDataFim().format(dtf) + ",";
        dados += seguro.getSeguradora().getCNPJ() + ",";
        dados += seguro.getCliente().getDocumento()[1] + ",";
        dados += seguro.getValorMensal() + ",";

        for (Sinistro sinistro : seguro.getListaSinistros()) {
            dados += sinistro.getId() + ";";
        }
        dados += ",";

        for (Condutor condutor : seguro.getListaCondutores()) {
            dados += condutor.getCPF() + ";";
        }
        dados += ",";

        dados += seguro.getVeiculo().getPlaca();

        return dados;
    }
}