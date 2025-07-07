package com.petshop;

import com.petshop.dados.*;
import com.petshop.dao.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteDAO clienteDAO = new ClienteDAO();
    private static final FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static final AnimalDAO animalDAO = new AnimalDAO();
    private static final ServicoDAO servicoDAO = new ServicoDAO();
    private static final AtendimentoDAO atendimentoDAO = new AtendimentoDAO();
    private static final ContrataDAO contrataDAO = new ContrataDAO();
    private static final OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
    private static final ProdutoDAO produtoDAO = new ProdutoDAO();
    private static final FornecedorDAO fornecedorDAO = new FornecedorDAO();
    private static final FornecimentoDAO fornecimentoDAO = new FornecimentoDAO();
    private static final CompraDAO compraDAO = new CompraDAO();


    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0) {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1: cadastrarCliente(); break;
                    case 2: listarClientes(); break;
                    case 3: cadastrarAnimal(); break;
                    case 4: listarAnimais(); break;
                    case 5: cadastrarFuncionario(); break;
                    case 6: listarFuncionarios(); break;
                    case 7: cadastrarServico(); break;
                    case 8: listarServicos(); break;
                    case 9: cadastrarFornecedor(); break;
                    case 10: listarFornecedores(); break;
                    case 11: cadastrarProduto(); break;
                    case 12: listarProdutos(); break;
                    case 13: registrarFornecimento(); break;
                    case 14: registrarCompra(); break;
                    case 15: registrarAtendimento(); break;
                    case 16: atendimentoDAO.listarAtendimentosCompletos(); break;
                    case 17: exibirFuncionarioMaisAtivo(); break;
                    case 99: removerEntidade(); break; // Opção genérica de remoção
                    case 0: System.out.println("Saindo do sistema... Até logo!"); break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Erro: Por favor, digite um número válido para a opção.");
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n==========================================");
        System.out.println("    SISTEMA DE GESTÃO PETSHOP");
        System.out.println("==========================================");
        System.out.println("--- GESTÃO DE CLIENTES E ANIMAIS ---");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Cadastrar Animal");
        System.out.println("4. Listar Animais");
        System.out.println("--- GESTÃO DE PESSOAL E SERVIÇOS ---");
        System.out.println("5. Cadastrar Funcionário");
        System.out.println("6. Listar Funcionários");
        System.out.println("7. Cadastrar Serviço");
        System.out.println("8. Listar Serviços");
        System.out.println("--- GESTÃO DE ESTOQUE E VENDAS ---");
        System.out.println("9. Cadastrar Fornecedor");
        System.out.println("10. Listar Fornecedores");
        System.out.println("11. Cadastrar Produto");
        System.out.println("12. Listar Produtos");
        System.out.println("13. Registrar Recebimento de Estoque (Fornecimento)");
        System.out.println("14. Registrar Venda de Produto (Compra)");
        System.out.println("--- OPERAÇÕES E RELATÓRIOS ---");
        System.out.println("15. Registrar Atendimento de Serviço");
        System.out.println("16. [RELATÓRIO] Listar Atendimentos Detalhados");
        System.out.println("17. [RELATÓRIO] Ver Funcionário Mais Ativo");
        System.out.println("------------------------------------------");
        System.out.println("99. Remover uma Entidade (Cliente, Animal, etc.)");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarFornecedor() {
        System.out.println("\n--- Cadastro de Fornecedor ---");
        System.out.print("Nome: "); String nome = scanner.nextLine();
        System.out.print("CNPJ (14 dígitos): "); String cnpj = scanner.nextLine();
        System.out.print("CEP: "); String cep = scanner.nextLine();
        System.out.print("Número: "); String numero = scanner.nextLine();
        System.out.print("Complemento: "); String complemento = scanner.nextLine();
        
        Fornecedor f = new Fornecedor();
        f.setNome(nome); f.setCnpj(cnpj); f.setCep(cep); f.setNumero(numero); f.setComplemento(complemento);
        fornecedorDAO.adicionar(f);
        System.out.println(">>> Fornecedor cadastrado com sucesso!");
    }

    private static void listarFornecedores() {
        System.out.println("\n--- Lista de Fornecedores ---");
        List<Fornecedor> fornecedores = fornecedorDAO.listarTodos();
        if (fornecedores.isEmpty()) { System.out.println("Nenhum fornecedor cadastrado."); }
        else { fornecedores.forEach(System.out::println); }
    }

    private static void cadastrarProduto() {
        try {
            System.out.println("\n--- Cadastro de Produto ---");
            System.out.print("Nome do Produto: "); String nome = scanner.nextLine();
            System.out.print("Tipo (ex: Ração, Brinquedo): "); String tipo = scanner.nextLine();
            System.out.print("Valor de Venda (ex: 45.50): "); BigDecimal valor = new BigDecimal(scanner.nextLine());
            
            Produto p = new Produto();
            p.setNome(nome); p.setTipo(tipo); p.setValor(valor);
            produtoDAO.adicionar(p);
            System.out.println(">>> Produto cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de valor inválido.");
        }
    }

    private static void listarProdutos() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoDAO.listarTodos();
        if (produtos.isEmpty()) { System.out.println("Nenhum produto cadastrado."); }
        else { produtos.forEach(System.out::println); }
    }
    
    private static void registrarFornecimento() {
        try {
            System.out.println("\n--- Registrar Recebimento de Estoque ---");
            listarFornecedores();
            System.out.print("ID do Fornecedor: ");
            long fornecedorId = Long.parseLong(scanner.nextLine());
            if (fornecedorDAO.buscarPorId(fornecedorId) == null) {
                System.err.println("Erro: Fornecedor não encontrado.");
                return;
            }

            listarProdutos();
            System.out.print("ID do Produto: ");
            long produtoId = Long.parseLong(scanner.nextLine());
            if (produtoDAO.buscarPorId(produtoId) == null) {
                System.err.println("Erro: Produto não encontrado.");
                return;
            }

            System.out.print("Quantidade recebida: ");
            int qtd = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Valor de Custo por Unidade: ");
            BigDecimal valorCustoUnitario = new BigDecimal(scanner.nextLine());

            BigDecimal valorTotal = valorCustoUnitario.multiply(new BigDecimal(qtd));
            System.out.println(">>> Valor total do fornecimento calculado: R$" + valorTotal);
            
            Fornecimento f = new Fornecimento();
            f.setId_fornecedor(fornecedorId);
            f.setId_produto(produtoId);
            f.setQuantidade(qtd);
            f.setValor_total(valorTotal);
            f.setData(LocalDate.now());
            
            fornecimentoDAO.adicionar(f);
            System.out.println(">>> Fornecimento registrado com sucesso!");
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de número inválido.");
        }
    }
    
    private static void registrarCompra() {
        System.out.println("\n--- Registrar Venda de Produto ---");
        listarClientes();
        System.out.print("ID do Cliente: "); long clienteId = Long.parseLong(scanner.nextLine());
        listarProdutos();
        System.out.print("ID do Produto: "); long produtoId = Long.parseLong(scanner.nextLine());
        System.out.print("Quantidade vendida: "); int qtd = Integer.parseInt(scanner.nextLine());
        
        Produto p = produtoDAO.buscarPorId(produtoId);
        if (p == null) { System.err.println("Produto não encontrado."); return; }
        BigDecimal valorTotal = p.getValor().multiply(new BigDecimal(qtd));
        System.out.println("Valor total da compra: R$" + valorTotal);
        
        Compra c = new Compra();
        c.setId_cliente(clienteId); c.setId_produto(produtoId); c.setQuantidade(qtd); c.setValor_total(valorTotal);
        c.setData(LocalDate.now()); c.setHora(LocalTime.now());
        compraDAO.adicionar(c);
        System.out.println(">>> Compra registrada com sucesso!");
    }

    private static void removerEntidade() {
        try {
            System.out.println("\n--- Módulo de Remoção ---");
            System.out.println("O que você deseja remover?");
            System.out.println("1. Cliente");
            System.out.println("2. Animal");
            System.out.println("3. Funcionário");
            System.out.println("4. Serviço");
            System.out.println("5. Produto");
            System.out.println("6. Fornecedor");
            System.out.print("Opção: ");
            int tipo = Integer.parseInt(scanner.nextLine());

            switch(tipo) {
                case 1: listarClientes(); break;
                case 2: listarAnimais(); break;
                case 3: listarFuncionarios(); break;
                case 4: listarServicos(); break;
                case 5: listarProdutos(); break;
                case 6: listarFornecedores(); break;
                default: System.out.println("Tipo de entidade inválido."); return;
            }

            System.out.print("\nDigite o ID da entidade a ser removida: ");
            long id = Long.parseLong(scanner.nextLine());

            switch(tipo) {
                case 1: clienteDAO.removerPorId(id); break;
                case 2: animalDAO.removerPorId(id); break;
                case 3: funcionarioDAO.removerPorId(id); break;
                case 4: servicoDAO.removerPorId(id); break;
                case 5: produtoDAO.removerPorId(id); break;
                case 6: fornecedorDAO.removerPorId(id); break;
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de número inválido para opção ou ID.");
        }
    }
    
    private static void cadastrarCliente() {
        System.out.println("\n--- Cadastro de Cliente ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF (sem pontos ou traços): ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);

        clienteDAO.adicionar(cliente);
        System.out.println(">>> Cliente cadastrado com sucesso!");
    }

    private static void listarClientes() {
        System.out.println("\n--- Lista de Clientes ---");
        List<Cliente> clientes = clienteDAO.listarTodos();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente.toString());
            }
        }
    }

    private static void cadastrarAnimal() {
        System.out.println("\n--- Cadastro de Animal ---");
        System.out.println("Clientes disponíveis:");
        listarClientes();
        if (clienteDAO.listarTodos().isEmpty()) {
            System.out.println("É necessário cadastrar um cliente primeiro.");
            return;
        }
        
        System.out.print("\nDigite o ID do Cliente (Dono) para associar ao animal: ");
        long clienteId = Long.parseLong(scanner.nextLine());
        
        System.out.print("Nome do Animal: ");
        String nome = scanner.nextLine();
        System.out.print("Raça: ");
        String raca = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());
        System.out.print("Tamanho (Pequeno, Médio, Grande): ");
        String tamanho = scanner.nextLine();

        Animal animal = new Animal();
        animal.setId_cliente(clienteId);
        animal.setNome(nome);
        animal.setRaca(raca);
        animal.setIdade(idade);
        animal.setTamanho(tamanho);

        animalDAO.adicionar(animal);
        System.out.println(">>> Animal cadastrado com sucesso!");
    }

    private static void listarAnimais() {
        System.out.println("\n--- Lista de Animais ---");
        List<Animal> animais = animalDAO.listarTodos();
        if (animais.isEmpty()) {
            System.out.println("Nenhum animal cadastrado.");
        } else {
            for (Animal animal : animais) {
                System.out.println(animal.toString());
            }
        }
    }


    private static void cadastrarFuncionario() {
        try {
            System.out.println("\n--- Cadastro de Funcionário ---");
            System.out.print("Nome: ");
            String nome = scanner.nextLine();
            System.out.print("CPF (sem pontos ou traços): ");
            String cpf = scanner.nextLine();
            System.out.print("Salário (ex: 3500.50): ");
            BigDecimal salario = new BigDecimal(scanner.nextLine());
            System.out.print("Data de Nascimento (dd/MM/yyyy): ");
            String dataStr = scanner.nextLine();
            LocalDate dataNascimento = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setSalario(salario);
            funcionario.setData_nascimento(dataNascimento);

            funcionarioDAO.adicionar(funcionario);
            System.out.println(">>> Funcionário cadastrado com sucesso!");
        } catch (DateTimeParseException e) {
            System.err.println("Erro: Formato de data inválido. Use dd/MM/yyyy.");
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de salário inválido. Use ponto como separador decimal.");
        }
    }

    private static void listarFuncionarios() {
        System.out.println("\n--- Lista de Funcionários ---");
        List<Funcionario> funcionarios = funcionarioDAO.listarTodos();
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario.toString());
            }
        }
    }

    private static void cadastrarServico() {
        try {
            System.out.println("\n--- Cadastro de Serviço ---");
            System.out.print("Tipo do Serviço (ex: Banho e Tosa): ");
            String tipo = scanner.nextLine();
            System.out.print("Preço (ex: 80.00): ");
            BigDecimal preco = new BigDecimal(scanner.nextLine());
            System.out.print("Duração em Minutos: ");
            int duracao = Integer.parseInt(scanner.nextLine());

            Servico servico = new Servico();
            servico.setTipo(tipo);
            servico.setPreco(preco);
            servico.setDuracao(duracao);

            servicoDAO.adicionar(servico);
            System.out.println(">>> Serviço cadastrado com sucesso!");
        } catch (NumberFormatException e) {
            System.err.println("Erro: Formato de número inválido para preço ou duração.");
        }
    }

    private static void listarServicos() {
        System.out.println("\n--- Lista de Serviços Disponíveis ---");
        List<Servico> servicos = servicoDAO.listarTodos();
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
        } else {
            for (Servico servico : servicos) {
                System.out.println(servico.toString());
            }
        }
    }


    private static void registrarAtendimento() {
        try {
            System.out.println("\n--- Registrar Novo Atendimento ---");
            
            System.out.println("--- Animais Disponíveis ---");
            listarAnimais();
            System.out.println("\n--- Funcionários Disponíveis ---");
            listarFuncionarios();
            System.out.println("\n--- Serviços Disponíveis ---");
            listarServicos();
            
            System.out.print("\nDigite o ID do Animal: ");
            long idAnimal = Long.parseLong(scanner.nextLine());
            Animal animal = animalDAO.buscarPorId(idAnimal);
            if (animal == null) {
                System.err.println("Erro: Animal com ID " + idAnimal + " não encontrado.");
                return; 
            }

            System.out.print("Digite o ID do Funcionário: ");
            long idFuncionario = Long.parseLong(scanner.nextLine());
            if (funcionarioDAO.buscarPorId(idFuncionario) == null) {
                System.err.println("Erro: Funcionário com ID " + idFuncionario + " não encontrado.");
                return; 
            }

            System.out.print("Digite o ID do Serviço: ");
            long idServico = Long.parseLong(scanner.nextLine());
            Servico servico = servicoDAO.buscarPorId(idServico);
            if (servico == null) {
                System.err.println("Erro: Serviço com ID " + idServico + " não encontrado.");
                return;
            }
            
            BigDecimal valor = servico.getPreco();
            System.out.println("Valor do serviço selecionado: R$" + valor);

            Atendimento novoAtendimento = new Atendimento();
            novoAtendimento.setId_funcionario(idFuncionario);
            novoAtendimento.setId_servico(idServico);
            novoAtendimento.setData(LocalDate.now());
            novoAtendimento.setHora(LocalTime.now());
            novoAtendimento.setValor_atendimento(valor);

            long idNovoAtendimento = atendimentoDAO.adicionar(novoAtendimento);

            if (idNovoAtendimento != -1) {
                contrataDAO.adicionar(idNovoAtendimento, idAnimal);
                System.out.println(">>> Atendimento registrado com sucesso com o ID: " + idNovoAtendimento);

                System.out.print("Houve alguma ocorrência? (s/n): ");
                String resposta = scanner.nextLine();

                if (resposta.equalsIgnoreCase("s")) {
                    System.out.print("Descreva a ocorrência: ");
                    String descOcorrencia = scanner.nextLine();

                    Ocorrencia novaOcorrencia = new Ocorrencia();
                    novaOcorrencia.setDescricao(descOcorrencia);
                    novaOcorrencia.setId_atendimento(idNovoAtendimento);

                    ocorrenciaDAO.adicionar(novaOcorrencia);
                    System.out.println(">>> Ocorrência registrada para o atendimento " + idNovoAtendimento);
                }
            } else {
                System.err.println("Falha ao registrar o atendimento na tabela principal.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Erro: ID inválido. Por favor, digite apenas números.");
        }
    }
    
    private static void exibirFuncionarioMaisAtivo() {
        System.out.println("\n--- Funcionário Mais Ativo ---");
        Funcionario funcionario = atendimentoDAO.encontrarFuncionarioMaisAtivo();
        if (funcionario != null) {
            System.out.println("O funcionário com mais atendimentos é: " + funcionario.getNome());
            System.out.println(funcionario.toString());
        } else {
            System.out.println("Nenhum atendimento registrado para determinar o funcionário mais ativo.");
        }
    }
}