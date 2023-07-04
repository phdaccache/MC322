package arquivos;

import java.util.ArrayList;

import arquivos.arquivoObjeto.*;
import sistema.*;

public class Carregar {
    public static void carregarDados() {
        // Carregando seguradoras
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        ArrayList<String[]> listaSeguradoras = seguradoras.lerDados();
        if (listaSeguradoras != null && !listaSeguradoras.isEmpty()) {
            for (int i = 0; i < listaSeguradoras.size(); i++) {
                String[] dados = listaSeguradoras.get(i);
                Seguradora seguradora = new Seguradora(dados[0], dados[1], dados[2], dados[3], dados[4]);
                Admin.cadastrarSeguradora(seguradora);
            }
            System.out.println("Seguradoras carregadas!");
        }

        // Carregando clientes PF
        ArquivoClientePF clientesPF = new ArquivoClientePF();
        ArrayList<String[]> listaClientesPF = clientesPF.lerDados();
        if (listaClientesPF != null && !listaClientesPF.isEmpty()) {
            for (int i = 0; i < listaClientesPF.size(); i++) {
                String[] dados = listaClientesPF.get(i);
                ClientePF clientePF = new ClientePF(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7]);
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    if (seguradora.getCNPJ().equals(dados[8])) {
                        seguradora.cadastrarCliente(clientePF);
                    }
                }
            }
            System.out.println("Clientes PF carregados!");
        }

        // Carregando clientes PJ
        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        ArrayList<String[]> listaClientesPJ = clientesPJ.lerDados();
        if (listaClientesPJ != null && !listaClientesPJ.isEmpty()) {
            for (int i = 0; i < listaClientesPJ.size(); i++) {
                String[] dados = listaClientesPJ.get(i);
                ClientePJ clientePJ = new ClientePJ(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], Integer.parseInt(dados[6]));
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    if (seguradora.getCNPJ().equals(dados[7])) {
                        seguradora.cadastrarCliente(clientePJ);
                    }
                }
            }
            System.out.println("Clientes PJ carregados!");
        }

        // Carregando veiculos
        ArquivoVeiculo veiculos = new ArquivoVeiculo();
        ArrayList<String[]> listaVeiculos = veiculos.lerDados();
        if (listaVeiculos!= null && !listaVeiculos.isEmpty()) {
            for (int i = 0; i < listaVeiculos.size(); i++) {
                String[] dados = listaVeiculos.get(i);
                Veiculo veiculo = new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    for (Cliente cliente : seguradora.getListaClientes()) {
                        if (cliente.getDocumento()[1].equals(dados[5])) {
                            ((ClientePF)cliente).cadastrarVeiculo(veiculo);
                        }
                    }
                }
            }
        }

        // Carregando frotas

    }
}