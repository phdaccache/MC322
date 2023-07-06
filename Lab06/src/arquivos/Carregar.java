package arquivos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import arquivos.arquivoObjeto.*;
import sistema.*;

public class Carregar {
    public static void carregarDados() {
        /******************************* CARREGANDO OBJETOS *******************************/

        // Carregando seguradoras
        ArquivoSeguradora seguradoras = new ArquivoSeguradora();
        ArrayList<String[]> dadosSeguradora = seguradoras.lerDados();
        if (dadosSeguradora != null && !dadosSeguradora.isEmpty()) {
            for (int i = 0; i < dadosSeguradora.size(); i++) {
                String[] dados = dadosSeguradora.get(i);
                Seguradora seguradora = new Seguradora(dados[0], dados[1].replace(';', ','),
                                                       dados[2].replace(';', ','),
                                                       dados[3].replace(';', ','),
                                                       dados[4].replace(';', ','));
                Admin.listaSeguradoras.add(seguradora);
            }
            System.out.println("Seguradoras carregadas!");
        }

        // Carregando clientes PF
        ArquivoClientePF clientesPF = new ArquivoClientePF();
        ArrayList<String[]> dadosClientesPF = clientesPF.lerDados();
        if (dadosClientesPF != null && !dadosClientesPF.isEmpty()) {
            for (int i = 0; i < dadosClientesPF.size(); i++) {
                String[] dados = dadosClientesPF.get(i);
                ClientePF clientePF = new ClientePF(dados[0].replace(';', ','),
                                                    dados[1].replace(';', ','),
                                                    dados[2].replace(';', ','),
                                                    dados[3].replace(';', ','),
                                                    dados[4],
                                                    dados[5].replace(';', ','),
                                                    dados[6].replace(';', ','),
                                                    dados[7]);
                Seguradora seguradora = Admin.getSeguradora(dados[8]);
                clientePF.setValorMensalTotal(Double.parseDouble(dados[9]));
                clientePF.setSeguradora(seguradora);
                seguradora.getListaClientes().add(clientePF);
            }
            System.out.println("Clientes PF carregados!");
        }

        // Carregando clientes PJ
        ArquivoClientePJ clientesPJ = new ArquivoClientePJ();
        ArrayList<String[]> dadosClientesPJ = clientesPJ.lerDados();
        if (dadosClientesPJ != null && !dadosClientesPJ.isEmpty()) {
            for (int i = 0; i < dadosClientesPJ.size(); i++) {
                String[] dados = dadosClientesPJ.get(i);
                ClientePJ clientePJ = new ClientePJ(dados[0].replace(';', ','),
                                                    dados[1].replace(';', ','),
                                                    dados[2].replace(';', ','),
                                                    dados[3].replace(';', ','),
                                                    dados[4], dados[5], Integer.parseInt(dados[6]));
                Seguradora seguradora = Admin.getSeguradora(dados[7]);
                clientePJ.setValorMensalTotal(Double.parseDouble(dados[8]));
                clientePJ.setSeguradora(seguradora);
                seguradora.getListaClientes().add(clientePJ);
            }
            System.out.println("Clientes PJ carregados!");
        }

        // Carregando frotas
        ArquivoFrota frotas = new ArquivoFrota();
        ArrayList<String[]> dadosFrotas = frotas.lerDados();
        if (dadosFrotas != null && !dadosFrotas.isEmpty()) {
            for (int i = 0; i < dadosFrotas.size(); i++) {
                String[] dados = dadosFrotas.get(i);
                Frota frota = new Frota(Integer.parseInt(dados[0]));
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    Cliente cliente = seguradora.getCliente(dados[3]);
                    if (cliente != null && cliente instanceof ClientePJ) {
                        ((ClientePJ)cliente).getListaFrotas().add(frota);
                    }
                }
            }
        }

        // Carregando veiculos
        ArquivoVeiculo veiculos = new ArquivoVeiculo();
        ArrayList<String[]> dadosVeiculos = veiculos.lerDados();
        if (dadosVeiculos != null && !dadosVeiculos.isEmpty()) {
            for (int i = 0; i < dadosVeiculos.size(); i++) {
                String[] dados = dadosVeiculos.get(i);
                Veiculo veiculo = new Veiculo(dados[0],
                                              dados[1].replace(';', ','),
                                              dados[2].replace(';', ','),
                                              Integer.parseInt(dados[3]));
                for (Seguradora seguradora : Admin.listaSeguradoras) {
                    // Carregando veiculos de clientes PF
                    Cliente cliente = seguradora.getCliente(dados[5]);
                    if (cliente instanceof ClientePF) {     
                        if (cliente != null) {
                            ((ClientePF)cliente).getListaVeiculos().add(veiculo);
                        }
                    }
                    // Carregando veiculos das frotas
                    else if (cliente instanceof ClientePJ) {
                        for (Cliente cl : seguradora.getListaClientes()) {
                            Frota frota = ((ClientePJ)cl).getFrota(Integer.parseInt(dados[5]));
                            if (frota != null) {
                                frota.getListaVeiculos().add(veiculo);
                            }
                        }
                    }
                }
            }
        }

        // Carregando condutores
        ArrayList<Condutor> listaCondutores = new ArrayList<>();
        ArquivoCondutor condutores = new ArquivoCondutor();
        ArrayList<String[]> dadosCondutores = condutores.lerDados();
        if (dadosCondutores != null && !dadosCondutores.isEmpty()) {
            for (int i = 0; i < dadosCondutores.size(); i++) {
                String[] dados = dadosCondutores.get(i);
                Condutor condutor = new Condutor(dados[0],
                                                 dados[1].replace(';', ','),
                                                 dados[2].replace(';', ','),
                                                 dados[3].replace(';', ','),
                                                 dados[4].replace(';', ','),
                                                 dados[5]);
                listaCondutores.add(condutor);
            }
        }

        // Carregando seguros PF
        ArquivoSeguroPF segurosPF = new ArquivoSeguroPF();
        ArrayList<String[]> dadosSegurosPF = segurosPF.lerDados();
        if (dadosSegurosPF != null && !dadosSegurosPF.isEmpty()) {
            for (int i = 0; i < dadosSegurosPF.size(); i++) {
                String[] dados = dadosSegurosPF.get(i);
                Seguradora seguradora = Admin.getSeguradora(dados[3]);
                ClientePF cliente = (ClientePF)seguradora.getCliente(dados[4]);
                Veiculo veiculo = ((ClientePF)cliente).getVeiculo(dados[8]);
                SeguroPF seguroPF = new SeguroPF(Integer.parseInt(dados[0]),
                                                 veiculo, cliente, dados[1],
                                                 dados[2], seguradora);
                
                seguroPF.setValorMensal(Double.parseDouble(dados[5]));
                seguradora.getListaSeguros().add(seguroPF);
                cliente.getListaSeguros().add(seguroPF);
                veiculo.setSeguro(seguroPF);

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
                SeguroPJ seguroPJ = new SeguroPJ(Integer.parseInt(dados[0]),
                                                 frota, cliente, dados[1],
                                                 dados[2], seguradora);
                
                seguroPJ.setValorMensal(Double.parseDouble(dados[5]));
                seguradora.getListaSeguros().add(seguroPJ);
                cliente.getListaSeguros().add(seguroPJ);
                frota.setSeguro(seguroPJ);

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
                Sinistro sinistro = new Sinistro(Integer.parseInt(dados[0]),
                                                 dados[1],
                                                 dados[2].replace(';', ','),
                                                 condutor, seguro);
                seguro.getListaSinistros().add(sinistro);
                condutor.getListaSinistros().add(sinistro);
            }
        }
    }
}