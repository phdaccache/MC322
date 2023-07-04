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

public class ArquivoClientePJ implements I_Arquivo {
    @Override
    public boolean gravarDados() {
        String header = "nome,telefone,endereco,email,cnpj,fundacao,qtdFuncionarios,cnpjSeguradora,valorMensalTotal,listaSeguros,listaFrotas";
        File file = new File("src/arquivos/arquivosCSV/clientesPJ.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente instanceof ClientePJ) {
                        String dados = getDados(cliente);
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
            File file = new File("src/arquivos/arquivosCSV/clientesPJ.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                //retorno.add(new ClientePJ(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], Integer.parseInt(dados[6])));
                retorno.add(dados);
            }
            br.close();
            return retorno;
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo: " + e.getMessage());
            return null;
        }
    }

    private String getDados(Cliente cliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += cliente.getNome() + ",";
        dados += cliente.getTelefone() + ",";
        dados += cliente.getEndereco() + ",";
        dados += cliente.getEmail() + ",";
        dados += cliente.getDocumento()[1] + ",";
        dados += ((ClientePJ)cliente).getDataFundacao().format(dtf) + ",";
        dados += ((ClientePJ)cliente).getQtdFuncionarios() + ",";
        dados += cliente.getSeguradora().getCNPJ() + ",";
        dados += cliente.getValorMensalTotal() + ",";

        dados += "\"";
        for (Seguro seguro : cliente.getListaSeguros()) {
            dados += seguro.getId() + ",";
        }
        dados += "\",";

        dados += "\"";
        for (Frota frota : ((ClientePJ)cliente).getListaFrotas()) {
            dados += frota.getId() + ",";
        }
        dados += "\"";

        return dados;
    }
}