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


public class ArquivoClientePF implements I_Arquivo<ClientePF> {
    @Override
    public boolean gravarDados() {
        String header = "nome,telefone,endereco,email,cpf,genero,educacao,nascimento,cnpjSeguradora,valorMensalTotal,listaSeguros,listaVeiculos";
        File file = new File("src/arquivos/arquivosCSV/clientesPF.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Cliente cliente : seguradora.getListaClientes()) {
                    if (cliente instanceof ClientePF) {
                        String dados = getDados((ClientePF)cliente);
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
            File file = new File("src/arquivos/arquivosCSV/clientesPF.csv");
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
    public String getDados(ClientePF cliente) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String dados = "";
        dados += cliente.getNome() + ",";
        dados += cliente.getTelefone() + ",";
        dados += cliente.getEndereco() + ",";
        dados += cliente.getEmail() + ",";
        dados += cliente.getDocumento()[1] + ",";
        dados += cliente.getGenero() + ",";
        dados += cliente.getEducacao() + ",";
        dados += cliente.getDataNascimento().format(dtf) + ",";
        dados += cliente.getSeguradora().getCNPJ() + ",";
        dados += cliente.getValorMensalTotal() + ",";

        dados += "\"";
        for (Seguro seguro : cliente.getListaSeguros()) {
            dados += seguro.getId() + ",";
        }
        dados += "\",";

        dados += "\"";
        for (Veiculo veiculo : ((ClientePF)cliente).getListaVeiculos()) {
            dados += veiculo.getPlaca() + ",";
        }
        dados += "\"";

        return dados;
    }
}