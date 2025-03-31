import Models.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        exercicio2();
//        exercicio3();
//        exercicio4();
//        exercicio5();
//        exercicio6();
//        exercicio7();
//        exercicio8();
//        exercicio9();
//        exercicio10();
//        exercicio11();
        exercicio12();
    }

    private static void exercicio2() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------- VALIDAÇÃO DE SENHA ---------");
        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) {
            System.out.println("[ERRO] O seu nome não pode estar vazio!");
        }

        boolean senhaValida = false;
        String senha;

        while (!senhaValida) {
            String message = "Olá " + nome + "! Por favor, crie uma senha que atenda aos seguintes critérios:\n"
                    + "- Mínimo de 8 caracteres\n"
                    + "- Pelo menos uma letra maiúscula\n"
                    + "- Pelo menos um número\n"
                    + "- Pelo menos um caractere especial (@, #, $, etc.)";

            System.out.println(message);

            System.out.print("Digite sua senha: ");
            senha = scanner.nextLine();

            if (senha.isEmpty()) {
                System.out.println("[ERRO] A sua senha não pode estar vazia!");
                continue;
            }

            boolean temTamanhoMinimo = senha.length() >= 8;
            boolean temMaiuscula = senha.matches(".*[A-Z].*");
            boolean temNumero = senha.matches(".*\\d.*");
            boolean temCaractereEspecial = senha.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");

            if (!temTamanhoMinimo) {
                System.out.println("[ERRO] Sua senha deve ter pelo menos 8 caracteres");
                System.out.println("\nPor favor, tente novamente.");
                continue;
            }

            if (!temMaiuscula) {
                System.out.println("[ERRO] Sua senha deve conter pelo menos uma letra maiúscula");
                System.out.println("\nPor favor, tente novamente.");
                continue;
            }

            if (!temNumero) {
                System.out.println("[ERRO] Sua senha deve conter pelo menos um número");
                System.out.println("\nPor favor, tente novamente.");
                continue;
            }

            if (!temCaractereEspecial) {
                System.out.println("[ERRO] Sua senha deve conter pelo menos um caractere especial");
                System.out.println("\nPor favor, tente novamente.");
                continue;
            }

            System.out.println("\nSenha criada com sucesso! Sua senha atende a todos os critérios de segurança.");
            senhaValida = true;
        }
    }

    private static void exercicio3() {
        final float limiteSalarioIsento = 22847.76f;
        final float limiteSalarioPrimeiraAliquota = 33919.80f;
        final float limiteSalarioSegundaAliquota = 45012.60f;

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------- CALCULADORA DE IMPOSTOS -----------");

        System.out.println("Digite seu nome:");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) {
            System.out.println("[ERRO] Nome não pode estar vazio!");
            exercicio3();
            return;
        }

        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                System.out.println("Digite seu salário mensal (R$):");
                float salarioMensal = scanner.nextFloat();

                if (salarioMensal < 0) {
                    throw new InputMismatchException("O salário não pode ser negativo. Tente novamente.");
                }

                float salarioAnual = salarioMensal * 12;
                double aliquota = 0;
                double imposto = 0;

                if (salarioAnual <= limiteSalarioIsento) {
                    aliquota = 0;
                }

                if (salarioAnual <= limiteSalarioPrimeiraAliquota) {
                    aliquota = 0.075;
                }

                if (salarioAnual <= limiteSalarioSegundaAliquota) {
                    aliquota = 0.15;
                }

                if (salarioAnual > limiteSalarioSegundaAliquota) {
                    aliquota = 0.275;
                }

                imposto = salarioAnual * aliquota;
                double salarioLiquido = salarioAnual - imposto;
                double impostoMensal = imposto / 12;
                double salarioLiquidoMensal = salarioMensal - impostoMensal;

                String mensagem = "\n===== RELATÓRIO DE IMPOSTO DE RENDA =====\n"
                        + "Nome: " + nome + "\n"
                        + "Salário Mensal: R$ " + String.format("%.2f", salarioMensal) + "\n"
                        + "Salário Anual: R$ " + String.format("%.2f", salarioAnual) + "\n"
                        + "Alíquota aplicada: " + String.format("%.1f", aliquota * 100) + "%\n"
                        + "Imposto anual: R$ " + String.format("%.2f", imposto) + "\n"
                        + "Imposto mensal: R$ " + String.format("%.2f", impostoMensal) + "\n"
                        + "Salário líquido anual: R$ " + String.format("%.2f", salarioLiquido) + "\n"
                        + "Salário líquido mensal: R$ " + String.format("%.2f", salarioLiquidoMensal);

                System.out.println(mensagem);
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private static void exercicio4() {
        final double taxaJurosMensal = 0.03;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simulador de Empréstimo Bancário");

        System.out.println("Por favor, informe seu nome:");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) {
            System.out.println("[ERRO] Nome não pode estar vazio!");
            exercicio4();
            return;
        }

        try {
            System.out.println("Informe o valor do empréstimo desejado (R$):");
            float valorEmprestimo = scanner.nextFloat();

            if (valorEmprestimo <= 0) {
                throw new InputMismatchException("O valor do empréstimo deve ser positivo. Tente novamente.");
            }

            System.out.println("Em quantas parcelas deseja pagar? (mínimo 6, máximo 48):");
            int parcelas = scanner.nextInt();

            if (parcelas < 6 || parcelas > 48) {
                throw new InputMismatchException("O número de parcelas deve estar entre 6 e 48. Tente novamente.");
            }

            double valorTotalPago = valorEmprestimo * Math.pow(1 + taxaJurosMensal, parcelas);
            double valorParcela = valorTotalPago / parcelas;

            String mensagem = "\n===== SIMULAÇÃO DE EMPRÉSTIMO =====\n"
                    + "Nome do cliente: " + nome + "\n"
                    + "Valor do empréstimo: R$ " + String.format("%.2f", valorEmprestimo) + "\n"
                    + "Taxa de juros: 3% ao mês\n"
                    + "Número de parcelas: " + parcelas + "\n"
                    + "Valor total a ser pago: R$ " + String.format("%.2f", valorTotalPago) + "\n"
                    + "Valor de cada parcela: R$ " + String.format("%.2f", valorParcela) + "\n"
                    + "Total de juros: R$ " + String.format("%.2f", valorTotalPago - valorEmprestimo);

            System.out.println(mensagem);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }

    private static void exercicio5() {
        System.out.println("Content-Type: text/html\n");

        String html = "<html>\n"
                + "<head><title>Saudação CGI</title></head>\n"
                + "<body>\n"
                + "<h1>Olá, Terráqueos!</h1>\n"
                + "</body>\n"
                + "</html>";

        System.out.println(html);
    }

    private static void exercicio6() {
        System.out.println("-------------- CADASTRO DE VEÍCULOS --------------");

        Veiculo veiculo1 = new Veiculo("ABC1234", "Toyota Corolla", 2019, 25000.5);
        Veiculo veiculo2 = new Veiculo("XYZ9876", "Honda Civic", 2021, 10500.75);

        System.out.println("\nDetalhes iniciais dos veículos:");
        veiculo1.exibirDetalhes();
        veiculo2.exibirDetalhes();

        System.out.println("\nRegistrando viagens...");
        System.out.println(veiculo1.registrarViagem(350.5));
        System.out.println(veiculo2.registrarViagem(275.25));

        System.out.println("\nDetalhes após as viagens:");
        veiculo1.exibirDetalhes();
        veiculo2.exibirDetalhes();
    }

    private static void exercicio7() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-------------- GERENCIADOR DE ALUNOS --------------");

        System.out.println("Digite o nome do aluno:");
        String nome = scanner.nextLine();

        if (nome.isEmpty()) {
            System.out.println("O nome do aluno não pode estar vazio!");
            exercicio7();
            return;
        }

        System.out.println("Digite a matrícula do aluno:");
        String matricula = scanner.nextLine();

        if (matricula.isEmpty()) {
            System.out.println("A matricula do aluno não pode estar vazia!");
            exercicio7();
            return;
        }

        double[] notas = new double[3];

        for (int i = 0; i < 3; i++) {
            boolean notaValida = false;
            while (!notaValida) {
                try {
                    System.out.println("Informe o valor " + (i + 1) + "ª da nota, de 0 a 10:");
                    double nota = scanner.nextDouble();
                    if (nota < 0 || nota > 10) {
                        System.out.println("A nota deve estar entre 0 e 10. Tente novamente.");
                        continue;
                    }

                    notas[i] = nota;
                    notaValida = true;

                } catch (InputMismatchException e) {
                    System.out.println("Valor inválido! Por favor, digite um número.");
                    scanner.nextLine();
                }
            }
        }

        Aluno aluno = new Aluno(nome, matricula, notas[0], notas[1], notas[2]);


        String relatorio = "\n===== RELATÓRIO DO ALUNO =====\n"
                + "Nome: " + aluno.nome + "\n"
                + "Matrícula: " + String.format("%.2f", aluno.matricula) + "\n"
                + "Nota 1: " + String.format("%.2f", aluno.nota1) + "\n"
                + "Nota 2: " + String.format("%.2f", aluno.nota2) + "\n"
                + "Nota 3: " + String.format("%.2f", aluno.nota3) + "\n"
                + "Média: " + String.format("%.2f", aluno.calcularMedia());

        System.out.println(relatorio);
        aluno.verificarAprovacao();

        scanner.nextLine();
    }

    private static void exercicio8() {
        System.out.println("-------------- SISTEMA DE FUNCIONÁRIOS --------------");

        Gerente gerente = new Gerente("Carlos Silva", 5000.00);
        Estagiario estagiario = new Estagiario("Ana Oliveira", 1800.00);

        System.out.println("\n===== RELATÓRIO DE SALÁRIOS =====");
        System.out.println("Gerente: " + gerente.nome);
        System.out.println("Salário Base: R$ " + String.format("%.2f", gerente.salarioBase));
        System.out.println("Salário Final (com bônus de 20%): R$ " + String.format("%.2f", gerente.calcularSalario()));

        System.out.println("\nEstagiário: " + estagiario.nome);
        System.out.println("Salário Base: R$ " + String.format("%.2f", estagiario.salarioBase));
        System.out.println("Salário Final (com desconto de 10%): R$ " + String.format("%.2f", estagiario.calcularSalario()));
    }

    private static void exercicio9() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome do titular da conta:");
        String titular = scanner.nextLine();

        if (titular.isEmpty()) {
            System.out.println("O nome do cliente não pode ser vazio!");
            exercicio9();
            return;
        }

        ContaBancaria conta = new ContaBancaria(titular);

        System.out.println("\nConta criada com sucesso para " + titular);
        conta.exibirSaldo();

        boolean continuar = true;

        while (continuar) {
            System.out.println("\nEscolha uma operação:");
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Exibir saldo");
            System.out.println("4. Sair");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println("Informe o valor a depositar:");
                        float valorDeposito = scanner.nextFloat();
                        scanner.nextLine();
                        conta.depositar(valorDeposito);
                        break;
                    case 2:
                        System.out.println("Informe o valor a sacar:");
                        float valorSaque = scanner.nextFloat();
                        scanner.nextLine();
                        conta.sacar(valorSaque);
                        break;
                    case 3:
                        conta.exibirSaldo();
                        break;
                    case 4:
                        System.out.println("Operações bancárias encerradas.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void exercicio10() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--------------- REGISTRO DE COMPRAS EM ARQUIVO ---------------");

        List<Compra> compras = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("\nCadastro da " + (i + 1) + "ª compra :");

            System.out.println("Digite o nome do produto:");
            String produto = scanner.nextLine();

            int quantidade = 0;
            float precoUnitario = 0;

            try {
                System.out.println("Digite a quantidade:");
                quantidade = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Digite o preço unitário (R$):");
                precoUnitario = scanner.nextFloat();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Usando valores padrão.");
                scanner.nextLine();
                quantidade = 1;
                precoUnitario = 0.0f;
            }

            compras.add(new Compra(produto, quantidade, precoUnitario));
        }

        try {
            FileWriter escritor = new FileWriter("compras.txt");
            PrintWriter saida = new PrintWriter(escritor);

            saida.println("===== REGISTRO DE COMPRAS =====");
            for (int i = 0; i < compras.size(); i++) {
                Compra c = compras.get(i);
                saida.println("\nCompra " + (i + 1) + ":");
                saida.println("Produto: " + c.produto);
                saida.println("Quantidade: " + c.quantidade);
                saida.println("Preço Unitário: R$ " + String.format("%.2f", c.precoUnitario));
                saida.println("Total: R$ " + String.format("%.2f", c.calcularTotal()));
            }

            saida.close();
            System.out.println("\nDados salvos com sucesso no arquivo compras.txt");
        } catch (IOException e) {
            System.out.println("\nErro ao salvar os dados: " + e.getMessage());
        }

        try {
            System.out.println("\nLendo dados do arquivo compras.txt:");
            FileReader leitor = new FileReader("compras.txt");
            BufferedReader entrada = new BufferedReader(leitor);

            String linha;
            while ((linha = entrada.readLine()) != null) {
                System.out.println(linha);
            }

            entrada.close();
        } catch (IOException e) {
            System.out.println("\nErro ao ler os dados: " + e.getMessage());
        }
    }

    private static void exercicio11() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("---------- SIMULAÇÃO DE LOTERIA ----------");

        int[] numerosSorteados = new int[6];
        for (int i = 0; i < 6; i++) {
            boolean repetido;
            int numero;
            do {
                numero = random.nextInt(60) + 1;
                repetido = false;


                for (int j = 0; j < i; j++) {
                    if (numerosSorteados[j] == numero) {
                        repetido = true;
                        break;
                    }
                }
            } while (repetido);

            numerosSorteados[i] = numero;
        }


        int[] numerosUsuario = new int[6];
        System.out.println("\nDigite 6 números entre 1 e 60:");

        for (int i = 0; i < 6; i++) {
            boolean valido = false;

            while (!valido) {
                try {
                    System.out.println("Número " + (i + 1) + ":");
                    int numero = scanner.nextInt();

                    if (numero < 1 || numero > 60) {
                        System.out.println("Por favor, digite um número entre 1 e 60.");
                    } else {

                        boolean jaEscolhido = false;
                        for (int j = 0; j < i; j++) {
                            if (numerosUsuario[j] == numero) {
                                jaEscolhido = true;
                                break;
                            }
                        }

                        if (jaEscolhido) {
                            System.out.println("Você já escolheu este número. Tente outro.");
                        } else {
                            numerosUsuario[i] = numero;
                            valido = true;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida! Por favor, digite um número inteiro.");
                    scanner.nextLine();
                }
            }
        }


        int contadorAcertos = 0;
        int[] numerosAcertados = new int[6];

        for (int numUsuario : numerosUsuario) {
            for (int numSorteado : numerosSorteados) {
                if (numUsuario == numSorteado) {
                    numerosAcertados[contadorAcertos] = numUsuario;
                    contadorAcertos++;
                    break;
                }
            }
        }


        System.out.println("\n===== RESULTADO DA LOTERIA =====");
        System.out.println("Seus números: " + Arrays.toString(numerosUsuario));
        System.out.println("Números sorteados: " + Arrays.toString(numerosSorteados));
        System.out.println("Total de acertos: " + contadorAcertos);

        if (contadorAcertos > 0) {

            int[] acertosFinais = Arrays.copyOf(numerosAcertados, contadorAcertos);
            System.out.println("Números acertados: " + Arrays.toString(acertosFinais));
        }


        switch (contadorAcertos) {
            case 6:
                System.out.println("PARABÉNS! Você acertou todos os números e ganhou o prêmio máximo!");
                break;
            case 5:
                System.out.println("INCRÍVEL! Você acertou 5 números e ganhou um prêmio!");
                break;
            case 4:
                System.out.println("MUITO BOM! Você acertou 4 números e ganhou um prêmio menor!");
                break;
            case 3:
                System.out.println("BOM! Você acertou 3 números.");
                break;
            default:
                System.out.println("Infelizmente você não ganhou desta vez. Tente novamente!");
        }

        scanner.close();
    }

    private static void exercicio12() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------ CHAT SIMPLES ------------");

        System.out.println("Digite o seu nome do primeiro usuário:");
        String usuario1 = scanner.nextLine();

        System.out.println("Digite o nome do segundo usuário:");
        String usuario2 = scanner.nextLine();

        String[] mensagens = new String[10];

        System.out.println("\nIniciando o chat... Cada usuário pode enviar até 5 mensagens.");

        for (int i = 0; i < 10; i++) {
            String usuarioAtual = (i % 2 == 0) ? usuario1 : usuario2;

            System.out.println("\n" + usuarioAtual + ", digite sua mensagem:");
            String mensagem = scanner.nextLine();

            mensagens[i] = usuarioAtual + ": " + mensagem;
        }

        System.out.println("\n===== Histórico de Mensagens =====");
        for (String mensagem : mensagens) {
            System.out.println(mensagem);
        }

        System.out.println("\nEncerrando o sistema! Obrigado por utilizarem!");
    }
}
