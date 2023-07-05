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

public class ArquivoSinistro implements I_Arquivo<Sinistro> {
    @Override
    public boolean gravarDados() {
        String header = "idSinistro,data,endereco,cpfCondutor,idSeguro";
        File file = new File("src/arquivos/arquivosCSV/sinistros.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    for (Sinistro sinistro : seguro.getListaSinistros()) {
                        String dados = getDados(sinistro);
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
            File file = new File("src/arquivos/arquivosCSV/sinistros.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
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

    @Override
    public String getDados(Sinistro sinistro) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += sinistro.getId();
        dados += sinistro.getData().format(dtf) + ",";
        dados += sinistro.getEndereco() + ",";
        dados += sinistro.getCondutor().getCPF() + ",";
        dados += sinistro.getSeguro().getId();

        return dados;
    }
}