package arquivos.arquivoObjeto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import sistema.*;

public class ArquivoCondutor implements I_Arquivo<Condutor> {
    @Override
    public boolean gravarDados() {
        String header = "cpf,nome,telefone,endereco,email,nascimento,listaSinistros";
        File file = new File("src/arquivos/arquivosCSV/condutores.csv");
        try{
            FileWriter escritor = new FileWriter(file, false);
            escritor.write(header);
            for (Seguradora seguradora : Admin.listaSeguradoras) {
                for (Seguro seguro : seguradora.getListaSeguros()) {
                    for (Condutor condutor : seguro.getListaCondutores()) {
                        String dados = getDados(condutor);
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
            File file = new File("src/arquivos/arquivosCSV/condutores.csv");
            String demilitador = ",";

            BufferedReader br = new BufferedReader(new FileReader(file));
            String linha = br.readLine(); // Lendo a primeira linha (cabeçalho)

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(demilitador);
                retorno.add(dados);
            }
            br.close();

            if (!retorno.isEmpty()) {
                System.out.println("Condutores carregados!");
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
    public String getDados(Condutor condutor) {
        String dados = "";
        dados += condutor.getCPF() + ",";
        dados += condutor.getNome() + ",";
        dados += condutor.getTelefone() + ",";
        dados += condutor.getEndereco() + ",";

        for (Sinistro sinistro : condutor.getListaSinistros()) {
            dados += sinistro.getId() + ";";
        }
        if (condutor.getListaSinistros() == null || condutor.getListaSinistros().isEmpty()) {
            dados += " ";
        }

        return dados;
    }
}