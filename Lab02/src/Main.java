import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("INSTRUÇÕES:\n\nDigite 1 para testar a Seguradora.\nDigite 2 para testar o Cliente.\nDigite 3 para testar o Veículo.\nDigite 4 para testar o Sinistro.\nDigite 0 para encerrar a execução.\n");

        while (true) {
            System.out.print("Comando: ");
            int comando = input.nextInt();
            input.nextLine();

            // PARAR A EXECUÇÃO
            if (comando == 0) {
                break;
            }

            // TESTANDO SEGURADORA
            else if (comando == 1) {
                // Lendo os dados da entrada padrão
                System.out.print("Insira o nome: ");
                String nome = input.nextLine();
                System.out.print("Insira o telefone: ");
                String telefone = input.nextLine();
                System.out.print("Insira o email: ");
                String email = input.nextLine();
                System.out.print("Insira o endereco: ");
                String endereco = input.nextLine();

                // Criando seguradora com os dados inseridos
                Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);

                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Seguradora:");
                System.out.println(seguradora.toString());

                // Lendo os dados da entrada padrão para mudança
                System.out.print("Insira o novo nome: ");
                String novo_nome = input.nextLine();
                System.out.print("Insira o novo telefone: ");
                String novo_telefone = input.nextLine();
                System.out.print("Insira o novo email: ");
                String novo_email = input.nextLine();
                System.out.print("Insira o novo endereco: ");
                String novo_endereco = input.nextLine();

                seguradora.setNome(novo_nome);
                seguradora.setTelefone(novo_telefone);
                seguradora.setEmail(novo_email);
                seguradora.setEndereco(novo_endereco);

                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Seguradora:");
                System.out.println(seguradora.toString());
            }

            // TESTANDO CLIENTE
            else if (comando == 2) {
                // Lendo os dados da entrada padrão
                System.out.print("Insira o nome: ");
                String nome = input.nextLine();
                System.out.print("Insira o cpf: ");
                String cpf = input.nextLine();
                System.out.print("Insira a data de nascimento: ");
                String data = input.nextLine();
                System.out.print("Insira a idade: ");
                int idade = input.nextInt();
                input.nextLine();
                System.out.print("Insira o endereço: ");
                String endereco = input.nextLine();

                // Criando cliente com os dados inseridos
                Cliente cliente = new Cliente(nome, cpf, data, idade, endereco);

                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Cliente:");
                System.out.println(cliente.toString());

                // Opção para validar o CPF
                System.out.println("Validar CPF? Digite 's' para sim ou 'n' para não.");
                String val_cpf = input.nextLine();
                if (val_cpf.equals("s")){
                    if (cliente.validarCPF(cpf) == true){
                        System.out.println("CPF Válido.");
                    } else {
                        System.out.println("CPF Inválido.");
                    }
                }

                // Lendo os dados da entrada padrão para mudança
                System.out.print("Insira o novo nome: ");
                String novo_nome = input.nextLine();
                System.out.print("Insira o novo cpf: ");
                String novo_cpf = input.nextLine();
                System.out.print("Insira a nova data de nascimento: ");
                String nova_data = input.nextLine();
                System.out.print("Insira a nova idade: ");
                int nova_idade = input.nextInt();
                input.nextLine();
                System.out.print("Insira o novo endereço: ");
                String novo_endereco = input.nextLine();

                cliente.setNome(novo_nome);
                cliente.setCpf(novo_cpf);
                cliente.setDataNascimento(nova_data);
                cliente.setIdade(nova_idade);
                cliente.setEndereco(novo_endereco);

                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Cliente:");
                System.out.println(cliente.toString());

                // Opção para validar o CPF
                System.out.println("Validar CPF? Digite 's' para sim ou 'n' para não.");
                val_cpf = input.nextLine();
                if (val_cpf.equals("s")){
                    if (cliente.validarCPF(cpf) == true){
                        System.out.println("CPF Válido.");
                    } else {
                        System.out.println("CPF Inválido.");
                    }
                }
            }

            // TESTANDO VEÍCULO
            else if (comando == 3) {
                // Lendo os dados da entrada padrão
                System.out.print("Insira a placa: ");
                String placa = input.nextLine();
                System.out.print("Insira a marca: ");
                String marca = input.nextLine();
                System.out.print("Insira o modelo: ");
                String modelo = input.nextLine();

                // Criando veículo com os dados inseridos
                Veiculo veiculo = new Veiculo(placa, marca, modelo);
                
                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Veiculo:");
                System.out.println(veiculo.toString());

                // Lendo os dados da entrada padrão para mudança
                System.out.print("Insira a nova placa: ");
                String nova_placa = input.nextLine();
                System.out.print("Insira a nova marca: ");
                String nova_marca = input.nextLine();
                System.out.print("Insira o novo modelo: ");
                String novo_modelo = input.nextLine();

                veiculo.setPlaca(nova_placa);
                veiculo.setMarca(nova_marca);
                veiculo.setModelo(novo_modelo);

                // Imprimindo os dados na saída padrão
                System.out.println("\nResumo dos dados tirados da classe Veiculo:");
                System.out.println(veiculo.toString());
            }

            // TESTANDO SINISTRO
            else if (comando == 4) {
                
            }
        }

        input.close();

    }
}