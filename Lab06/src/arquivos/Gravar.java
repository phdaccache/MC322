package arquivos;

import java.io.IOException;

import arquivos.arquivoObjeto.*;

public class Gravar {
    public static void gravarDados() throws IOException {
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        boolean status1 = seguradoras.gravarDados();
        if (status1) {
            System.out.println("Alteracoes nas seguradoras salvas!");
        }

        ArquivoClientePF clientesPF = new ArquivoClientePF();
        boolean status2 = clientesPF.gravarDados();
        if (status2) {
            System.out.println("Alteracoes nos clientes PF salvas!");
        }

        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        boolean status3 = clientesPJ.gravarDados();
        if (status3) {
            System.out.println("Alteracoes nos clientes PJ salvas!");
        }

        ArquivoVeiculo veiculos = new ArquivoVeiculo();
        boolean status4 = veiculos.gravarDados();
        if (status4) {
            System.out.println("Alteracoes nos veiculos salvas!");
        }

        ArquivoFrota frotas = new ArquivoFrota();
        boolean status5 = frotas.gravarDados();
        if (status5) {
            System.out.println("Alteracoes nas frotas salvas!");
        }

        ArquivoSeguroPF segurosPF = new ArquivoSeguroPF();
        boolean status6 = segurosPF.gravarDados();
        if (status6) {
            System.out.println("Alteracoes nos seguros PF salvas!");
        }

        ArquivoSeguroPJ segurosPJ = new ArquivoSeguroPJ();
        boolean status7 = segurosPJ.gravarDados();
        if (status7) {
            System.out.println("Alteracoes nos seguros PJ salvas!");
        }

        ArquivoCondutor condutores = new ArquivoCondutor();
        boolean status8 = condutores.gravarDados();
        if (status8) {
            System.out.println("Alteracoes nos condutores salvas!");
        }

        ArquivoSinistro sinistros = new ArquivoSinistro();
        boolean status9 = sinistros.gravarDados();
        if (status9) {
            System.out.println("Alteracoes nos sinistros salvas!");
        }

        if (status1 && status2 && status3 && status4 && status5 && status6 && status7 && status8 && status9) {
            System.out.println("Todas as alteracoes foram salvas!");
        }

        System.out.println("");
    }
}