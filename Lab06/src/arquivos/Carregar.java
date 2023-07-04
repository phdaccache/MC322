package arquivos;

import java.util.ArrayList;

import arquivos.arquivoObjeto.*;
import sistema.*;

public class Carregar {
    public static void carregarDados() {
        // Instanciando o arquivo
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        // Fazendo a leitura do arquivo
        ArrayList<String[]> listaSeguradoras = seguradoras.lerDados();
        // Adicionando os objetos
        if (listaSeguradoras != null) {
            for (int i = 0; i < listaSeguradoras.size(); i++) {
                String[] dados = listaSeguradoras.get(i);
                Seguradora seguradora = new Seguradora(dados[0], dados[1], dados[2], dados[3], dados[4]);
                Admin.cadastrarSeguradora(seguradora);
            }
        }

        // Instanciando o arquivo
        ArquivoClientePF clientesPF = new ArquivoClientePF();
        // Fazendo a leitura do arquivo
        ArrayList<String[]> listaClientesPF = clientesPF.lerDados();
        // Adicionando os objetos
        if (listaClientesPF != null) {
            for (int i = 0; i < listaClientesPF.size(); i++) {
                String[] dados = listaClientesPF.get(i);
                ClientePF clientePF = new ClientePF(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7]);
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    if (seguradora.getCNPJ().equals(dados[8])) {
                        seguradora.cadastrarCliente(clientePF);
                    }
                }
            }
        }

        // Instanciando o arquivo
        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        // Fazendo a leitura do arquivo
        ArrayList<String[]> listaClientesPJ = clientesPJ.lerDados();
        // Adicionando os objetos
        if (listaClientesPJ != null) {
            for (int i = 0; i < listaClientesPJ.size(); i++) {
                String[] dados = listaClientesPJ.get(i);
                ClientePJ clientePJ = new ClientePJ(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], Integer.parseInt(dados[6]));
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    if (seguradora.getCNPJ().equals(dados[7])) {
                        seguradora.cadastrarCliente(clientePJ);
                    }
                }
            }
        }
    }
}