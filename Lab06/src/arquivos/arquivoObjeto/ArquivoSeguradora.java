package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoSeguradora implements I_Arquivo<Seguradora> {
    @Override
    public boolean gravarDados() {
        String header = "cnpj,nome,telefone,endereco,email,listaClientes,listaSeguros";
        File file = new File("src/arquivos/arquivosCSV/seguradoras.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                String dados = getDados(seguradora);
                escritor.write("\n");
                escritor.write(dados);
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
            File file = new File("src/arquivos/arquivosCSV/seguradoras.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
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

    @Override
    public String getDados(Seguradora seguradora) {
        String dados = "";
        dados += seguradora.getCNPJ() + ",";
        dados += seguradora.getNome().replace(',', ';') + ",";
        dados += seguradora.getTelefone().replace(',', ';') + ",";
        dados += seguradora.getEndereco().replace(',', ';') + ",";
        dados += seguradora.getEmail().replace(',', ';') + ",";

        for (Cliente cliente : seguradora.getListaClientes()) {
            dados += cliente.getDocumento()[1] + ";";
        }
        if (seguradora.getListaClientes() == null || seguradora.getListaClientes().isEmpty()) {
            dados += " ";
        }
        dados += ",";

        for (Seguro seguro : seguradora.getListaSeguros()) {
            dados += seguro.getId() + ";";
        }
        if (seguradora.getListaSeguros() == null || seguradora.getListaSeguros().isEmpty()) {
            dados += " ";
        }

        return dados;
    }
}