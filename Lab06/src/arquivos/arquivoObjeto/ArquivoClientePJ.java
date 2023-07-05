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

public class ArquivoClientePJ implements I_Arquivo<ClientePJ> {
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
                        String dados = getDados((ClientePJ)cliente);
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
    public String getDados(ClientePJ cliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += cliente.getNome() + ",";
        dados += cliente.getTelefone() + ",";
        dados += cliente.getEndereco() + ",";
        dados += cliente.getEmail() + ",";
        dados += cliente.getDocumento()[1] + ",";
        dados += cliente.getDataFundacao().format(dtf) + ",";
        dados += cliente.getQtdFuncionarios() + ",";
        dados += cliente.getSeguradora().getCNPJ() + ",";
        dados += cliente.getValorMensalTotal() + ",";

        for (Seguro seguro : cliente.getListaSeguros()) {
            dados += seguro.getId() + ";";
        }
        if (cliente.getListaSeguros() == null || cliente.getListaSeguros().isEmpty()) {
            dados += " ";
        }
        dados += ",";

        for (Frota frota : ((ClientePJ)cliente).getListaFrotas()) {
            dados += frota.getId() + ";";
        }
        if (((ClientePJ)cliente).getListaFrotas() == null || ((ClientePJ)cliente).getListaFrotas().isEmpty()) {
            dados += " ";
        }

        return dados;
    }
}