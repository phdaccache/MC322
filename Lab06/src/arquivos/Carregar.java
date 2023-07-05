package arquivos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arquivos.arquivoObjeto.*;
import sistema.*;

public class Carregar {
    public static void carregarDados() {
        /******************************* CRIANDO LISTAS DE OBJETOS *******************************/

        ArrayList<Seguradora> listaSeguradoras = new ArrayList<>();
        ArrayList<ClientePF> listaClientesPF = new ArrayList<>();
        ArrayList<ClientePJ> listaClientesPJ = new ArrayList<>();
        ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
        ArrayList<Frota> listaFrotas = new ArrayList<>();
        ArrayList<SeguroPF> listaSegurosPF = new ArrayList<>();
        ArrayList<SeguroPJ> listaSegurosPJ = new ArrayList<>();
        ArrayList<Condutor> listaCondutores = new ArrayList<>();
        ArrayList<Sinistro> listaSinistros = new ArrayList<>();

        // Seguradora
        ArrayList<List<String>> documentosClientes = new ArrayList<>();
        ArrayList<List<String>> idsSegurosSeguradora = new ArrayList<>();

        // Cliente PF
        ArrayList<List<String>> idsSegurosClientePF = new ArrayList<>();
        ArrayList<List<String>> placasVeiculosClientePF = new ArrayList<>();

        // Cliente PJ
        ArrayList<List<String>> idsSegurosClientePJ = new ArrayList<>();
        ArrayList<List<String>> idsFrotasClientePJ = new ArrayList<>();

        // Frota
        ArrayList<List<String>> placasVeiculosFrota = new ArrayList<>();

        /******************************* CARREGANDO OBJETOS *******************************/

        // Carregando seguradoras
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        ArrayList<String[]> dadosSeguradora = seguradoras.lerDados();
        if (dadosSeguradora != null && !dadosSeguradora.isEmpty()) {
            for (int i = 0; i < dadosSeguradora.size(); i++) {
                String[] dados = dadosSeguradora.get(i);
                Seguradora seguradora = new Seguradora(dados[0], dados[1], dados[2], dados[3], dados[4]);
                listaSeguradoras.add(seguradora);

                List<String> clientes = new ArrayList<String>(Arrays.asList(dados[5].split(";")));
                documentosClientes.add(clientes);
                List<String> seguros = new ArrayList<String>(Arrays.asList(dados[6].split(";")));
                idsSegurosSeguradora.add(seguros);
            }
            System.out.println("Seguradoras carregadas!");
        }

        // Carregando clientes PF
        ArquivoClientePF clientesPF = new ArquivoClientePF();
        ArrayList<String[]> dadosClientesPF = clientesPF.lerDados();
        if (dadosClientesPF != null && !dadosClientesPF.isEmpty()) {
            for (int i = 0; i < dadosClientesPF.size(); i++) {
                String[] dados = dadosClientesPF.get(i);
                ClientePF clientePF = new ClientePF(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], dados[6], dados[7]);
                clientePF.setValorMensalTotal(Double.parseDouble(dados[9]));
                listaClientesPF.add(clientePF);

                List<String> seguros = new ArrayList<String>(Arrays.asList(dados[10].split(";")));
                idsSegurosClientePF.add(seguros);
                List<String> veiculos = new ArrayList<String>(Arrays.asList(dados[11].split(";")));
                placasVeiculosClientePF.add(veiculos);
            }
            System.out.println("Clientes PF carregados!");
        }

        // Carregando clientes PJ
        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        ArrayList<String[]> dadosClientesPJ = clientesPJ.lerDados();
        if (dadosClientesPJ != null && !dadosClientesPJ.isEmpty()) {
            for (int i = 0; i < dadosClientesPJ.size(); i++) {
                String[] dados = dadosClientesPJ.get(i);
                ClientePJ clientePJ = new ClientePJ(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5], Integer.parseInt(dados[6]));
                clientePJ.setValorMensalTotal(Double.parseDouble(dados[8]));
                listaClientesPJ.add(clientePJ);

                List<String> seguros = new ArrayList<String>(Arrays.asList(dados[9].split(";")));
                idsSegurosClientePJ.add(seguros);
                List<String> frotas = new ArrayList<String>(Arrays.asList(dados[10].split(";")));
                idsFrotasClientePJ.add(frotas);
            }
            System.out.println("Clientes PJ carregados!");
        }

        // Carregando veiculos
        ArquivoVeiculo veiculos = new ArquivoVeiculo();
        ArrayList<String[]> dadosVeiculos = veiculos.lerDados();
        if (dadosVeiculos != null && !dadosVeiculos.isEmpty()) {
            for (int i = 0; i < dadosVeiculos.size(); i++) {
                String[] dados = dadosVeiculos.get(i);
                Veiculo veiculo = new Veiculo(dados[0], dados[1], dados[2], Integer.parseInt(dados[3]));
                listaVeiculos.add(veiculo);
            }
        }

        // Carregando frotas
        ArquivoFrota frotas = new ArquivoFrota();
        ArrayList<String[]> dadosFrotas = frotas.lerDados();
        if (dadosFrotas != null && !dadosFrotas.isEmpty()) {
            for (int i = 0; i < dadosFrotas.size(); i++) {
                String[] dados = dadosFrotas.get(i);
                Frota frota = new Frota(Integer.parseInt(dados[0]));
                listaFrotas.add(frota);

                List<String> veiculosFrota = new ArrayList<String>(Arrays.asList(dados[2].split(";")));
                placasVeiculosFrota.add(veiculosFrota);
            }
        }

        // Carregando condutores
        ArquivoCondutor condutores = new ArquivoCondutor();
        ArrayList<String[]> dadosCondutores = condutores.lerDados();
        if (dadosCondutores != null && !dadosCondutores.isEmpty()) {
            for (int i = 0; i < dadosCondutores.size(); i++) {
                String[] dados = dadosCondutores.get(i);
                Condutor condutor = new Condutor(dados[0], dados[1], dados[2], dados[3], dados[4], dados[5]);
                listaCondutores.add(condutor);
            }
        }

        /******************************* ATUALIZANDO OBJETOS *******************************/

        // Adicionando seguradoras
        Admin.listaSeguradoras.addAll(listaSeguradoras);

        // Adicionando clientes
        for (int i = 0; i < Admin.listaSeguradoras.size(); i++) {
            Seguradora seguradora = Admin.listaSeguradoras.get(i);
            List<String> clientes = documentosClientes.get(i);
            for (Cliente cliente : listaClientesPF) {
                if (clientes.contains(cliente.getDocumento()[1])) {
                    cliente.setSeguradora(seguradora);
                    seguradora.getListaClientes().add(cliente);
                }      
            }
            for (Cliente cliente : listaClientesPJ) {
                if (clientes.contains(cliente.getDocumento()[1])) {
                    cliente.setSeguradora(seguradora);
                    seguradora.getListaClientes().add(cliente);
                }      
            }
        }

        // Adicionando veiculos
        for (int i = 0; i < listaClientesPF.size(); i++) {
            ClientePF clientePF = listaClientesPF.get(i);
            List<String> veiculosPF = placasVeiculosClientePF.get(i);
            for (Veiculo veiculo : listaVeiculos) {
                if (veiculosPF.contains(veiculo.getPlaca())) {
                    clientePF.getListaVeiculos().add(veiculo);
                }
            }
        }

        // Adicionando frotas
        for (int i = 0; i < listaClientesPJ.size(); i++) {
            ClientePJ clientePJ = listaClientesPJ.get(i);
            List<String> frotasPJ = idsFrotasClientePJ.get(i);
            for (Frota frota : listaFrotas) {
                if (frotasPJ.contains(Integer.toString(frota.getId()))) {
                    clientePJ.getListaFrotas().add(frota);
                }
            }
        }

        // Adicionando veiculos nas frotas
        for (int i = 0; i < listaFrotas.size(); i++) {
            Frota frota = listaFrotas.get(i);
            List<String> veiculosFrota = placasVeiculosFrota.get(i);
            for (Veiculo veiculo : listaVeiculos) {
                if (veiculosFrota.contains(veiculo.getPlaca())) {
                    frota.getListaVeiculos().add(veiculo);
                }
            }
        }

        /******************************* CARREGANDO OBJETOS FINAIS *******************************/

        // Carregando seguros PF
        ArquivoSeguroPF segurosPF = new ArquivoSeguroPF();
        ArrayList<String[]> dadosSegurosPF = segurosPF.lerDados();
        if (dadosSegurosPF != null && !dadosSegurosPF.isEmpty()) {
            for (int i = 0; i < dadosSegurosPF.size(); i++) {
                String[] dados = dadosSegurosPF.get(i);
                Seguradora seguradora = Admin.getSeguradora(dados[3]);
                ClientePF cliente = (ClientePF)seguradora.getCliente(dados[4]);
                Veiculo veiculo = ((ClientePF)cliente).getVeiculo(dados[8]);
                SeguroPF seguroPF = new SeguroPF(Integer.parseInt(dados[0]), veiculo, cliente, dados[1], dados[2], seguradora);
                
                seguroPF.setValorMensal(Double.parseDouble(dados[5]));
                seguradora.getListaSeguros().add(seguroPF);
                cliente.getListaSeguros().add(seguroPF);
                veiculo.setSeguro(seguroPF);
                listaSegurosPF.add(seguroPF);

                List<String> cpfscondutoresPF = new ArrayList<String>(Arrays.asList(dados[7].split(";")));
                for (Condutor condutor : listaCondutores) {
                    if (cpfscondutoresPF.contains(condutor.getCPF())) {
                        seguroPF.getListaCondutores().add(condutor);
                    }
                }
            }
        }

        // Carregando seguros PJ
        ArquivoSeguroPJ segurosPJ = new ArquivoSeguroPJ();
        ArrayList<String[]> dadosSegurosPJ = segurosPJ.lerDados();
        if (dadosSegurosPJ != null && !dadosSegurosPJ.isEmpty()) {
            for (int i = 0; i < dadosSegurosPJ.size(); i++) {
                String[] dados = dadosSegurosPJ.get(i);
                Seguradora seguradora = Admin.getSeguradora(dados[3]);
                ClientePJ cliente = (ClientePJ)seguradora.getCliente(dados[4]);
                Frota frota = ((ClientePJ)cliente).getFrota(Integer.parseInt(dados[8]));
                SeguroPJ seguroPJ = new SeguroPJ(Integer.parseInt(dados[0]), frota, cliente, dados[1], dados[2], seguradora);
                
                seguroPJ.setValorMensal(Double.parseDouble(dados[5]));
                seguradora.getListaSeguros().add(seguroPJ);
                cliente.getListaSeguros().add(seguroPJ);
                frota.setSeguro(seguroPJ);
                listaSegurosPJ.add(seguroPJ);

                List<String> cpfscondutoresPJ = new ArrayList<String>(Arrays.asList(dados[7].split(";")));
                for (Condutor condutor : listaCondutores) {
                    if (cpfscondutoresPJ.contains(condutor.getCPF())) {
                        seguroPJ.getListaCondutores().add(condutor);
                    }
                }
            }
        }

        // Carregando sinistros
        ArquivoSinistro sinistros = new ArquivoSinistro();
        ArrayList<String[]> dadosSinistros = sinistros.lerDados();
        if (dadosSinistros != null && !dadosSinistros.isEmpty()) {
            for (int i = 0; i < dadosSinistros.size(); i++) {
                String dados[] = dadosSinistros.get(i);
                Seguro seguro = null;
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    Seguro seg;
                    if ((seg = seguradora.getSeguro(Integer.parseInt(dados[4]))) != null) {
                        seguro = seg;
                    }
                }
                Condutor condutor = seguro.getCondutor(dados[3]);
                Sinistro sinistro = new Sinistro(Integer.parseInt(dados[0]), dados[1], dados[2], condutor, seguro);
                seguro.getListaSinistros().add(sinistro);
                condutor.getListaSinistros().add(sinistro);
                listaSinistros.add(sinistro);
            }
        }
    }
}