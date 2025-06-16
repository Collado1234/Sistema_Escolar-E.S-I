/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IU_Sistema;
import java.util.Scanner;
import java.util.List;
import modelo.*;

/**
 *
 * @author renna
 */


public class NewIU {
    private Scanner scanner;
    private Controlador controlador;

    public NewIU() {
        scanner = new Scanner(System.in);
        controlador = new Controlador();
    }

    public void executar() {
        int opcao;
        do {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cadastro");
            System.out.println("2. Listagem");
            System.out.println("3. Buscas");
            System.out.println("4. Relatórios");
            System.out.println("0. Sair");            
            opcao = lerOpcaoInt("Escolha uma opção: ", 4);            

            switch (opcao) {
                case 1 -> submenuCadastro();
                case 2 -> submenuListagem();
                case 3 -> submenuBusca();
                case 4 -> submenuRelatorio();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void submenuCadastro() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Cadastro ---");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Turma");
            System.out.println("3. Cadastrar Disciplina");
            System.out.println("4. Registrar Avaliação");
            System.out.println("5. Cadastrar Alunos na Turma");
            System.out.println("0. Voltar");
            opcao = lerOpcaoInt("Escolha uma opção: ", 5);


            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarTurma();
                case 3 -> cadastrarDisciplina();
                case 4 -> cadastrarAvaliacao();
                case 5 -> cadastrarAlunosNaTurma();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void submenuListagem() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Listagem ---");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Listar Turmas");
            System.out.println("3. Listar Disciplinas");
            System.out.println("4. Listar Avaliações");
            System.out.println("5. Listar Turmas de Aluno Cadastrado");
            System.out.println("6. Listar Turmas de Disciplina cadastrada");
            System.out.println("0. Voltar");
            opcao = lerOpcaoInt("Escolha uma opção: ", 6);

            switch (opcao) {
                case 1 -> listarAlunos();
                case 2 -> listarTurmas();
                case 3 -> listarDisciplinas();
                case 4 -> listarAvaliacoes();
                case 5 -> listarTurmasAlunoCadastrado();
                case 6 -> listarTurmasPorDisciplina();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void submenuBusca() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Busca ---");
            System.out.println("1. Buscar Aluno por Matrícula");
            System.out.println("2. Buscar Turma por Código");
            System.out.println("3. Buscar Disciplina por Código");
            System.out.println("4. Buscar Avaliação");
            System.out.println("5. Buscar Alunos em Turma");
            System.out.println("6. Buscar Avaliações por Aluno");
            System.out.println("7. Buscar Avaliações por Turma");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoInt("Escolha uma opção", 7);            

            switch (opcao) {
                case 1 -> buscarAlunoPorMatricula();
                case 2 -> buscarTurmaPorCodigo();
                case 3 -> buscarDisciplina();
                case 4 -> buscarAvaliacao();
                case 5 -> buscarAlunosEmTurma();
                case 6 -> buscarAvaliacoesPorAluno();
                case 7 -> buscarAvaliacoesPorTurma();
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void submenuRelatorio() {
        int opcao;
        do {
            System.out.println("\n--- Menu de Relatorios ---");
            System.out.println("1. Gerar Relatório de Turma");
            System.out.println("2. Gerar Relatório de Disciplina");            
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcaoInt("Escolha uma opção", 2);

            switch (opcao) {
                case 1 -> gerarRelatorioTurma();
                case 2 -> gerarRelatorioDisciplina();                
                case 0 -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    

    // Métodos de cadastro e listagem (exemplos simplificados)
    // ----------------- Funcoes Cadastro ---------------
    private void cadastrarAluno() {
        System.out.print("Código do aluno: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        Boolean flag = controlador.cadastrarAluno(nome, codigo);
        if(flag == false){
            System.out.println("Erro ao cadastrar aluno. Matrícula já existe.");
            return;
        }
        System.out.println("Aluno cadastrado com sucesso.");
    }
    
    private void cadastrarTurma() {
        System.out.print("Nome da turma: ");
        String nome = scanner.nextLine();
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();
        System.out.print("Código da disciplinea: ");
        String codigoDisciplina = scanner.nextLine();        
        Boolean flag = controlador.cadastrarTurma(nome, codigoTurma, codigoDisciplina);
        if (flag == false) {
            System.out.println("Erro ao cadastrar turma. Código já existe ou disciplina não encontrada.");
            return;
        }
        System.out.println("Turma cadastrada com sucesso.");
    }
    
     private void cadastrarDisciplina() {
        System.out.print("Códigda disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Carga horaria da disciplina: ");
        int cargaHoraria = lerInteiro("Carga horária da disciplina: ");
        Boolean flag = controlador.cadastrarDisciplina(nome, codigo, cargaHoraria);
        if (flag == false) {
            System.out.println("Erro ao cadastrar disciplina. Código já existe.");
            return;
        }
        System.out.println("Disciplina cadastrada com sucesso.");
    }
     
    private void cadastrarAvaliacao() {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();        
        System.out.print("Código do aluno: ");
        String codigoAluno = scanner.nextLine();
        System.out.print("Nota: ");
        float nota1 = lerFloat("Nota da P1: ");
        float nota2 = lerFloat("Nota da P2: ");
        int qtd_faltas = lerInteiro("Quantidade de Faltas: ");

        boolean sucesso = controlador.cadastrarAvaliacao(codigoAluno, codigoTurma, nota1, nota2, qtd_faltas);
        if (sucesso) System.out.println("Avaliação registrada.");
        else System.out.println("Erro ao registrar avaliação.");
    }

    private void cadastrarAlunosNaTurma() {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        System.out.print("Matrículas dos alunos (separadas por vírgula): ");
        String input = scanner.nextLine();
        List<String> matriculas = List.of(input.split(","));

        List<String> erros = controlador.cadastrarAlunosEmTurma(codigoTurma, matriculas);

        if (erros.isEmpty()) {
            System.out.println("Todos os alunos foram adicionados com sucesso à turma.");
        } else {
            System.out.println("Alguns alunos não foram adicionados:");
            erros.forEach(System.out::println);
        }
    }       
    //----------------- Listagem -----------------
    private void listarAlunos() {
        List<String> alunosFormatados = controlador.listarAlunosFormatados();
        if (alunosFormatados.isEmpty()) {
            System.out.println("Nenhum aluno.");
        } else {
            alunosFormatados.forEach(System.out::println);
        }
    }
                
    private void listarTurmas() {
        List<String> turmasFormatadas = controlador.listarTurmasFormatadas();
        if (turmasFormatadas.isEmpty()) {
            System.out.println("Nenhuma turma.");
        } else {
            turmasFormatadas.forEach(System.out::println);
        }
    }   

    private void listarDisciplinas() {
        List<String> disciplinasFormatadas = controlador.listarDisciplinasFormatadas();
        if (disciplinasFormatadas.isEmpty()) {
            System.out.println("Nenhuma disciplina.");
        } else {
            disciplinasFormatadas.forEach(System.out::println);
        }
    }   

    private void listarAvaliacoes() {
        List<String> avaliacoesFormatadas = controlador.listarAvaliacoesFormatadas();
        if (avaliacoesFormatadas.isEmpty()) {
            System.out.println("Nenhum aluno.");
        } else {
            avaliacoesFormatadas.forEach(System.out::println);
        }
    }   
    
    private void listarTurmasAlunoCadastrado() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        List<String> avaliacoesFormatadas = controlador.listarTurmasAlunoCadastrado(matricula);
        if (avaliacoesFormatadas.isEmpty()) {
            System.out.println("Nenhuma turma encontrada para o aluno informado.");
        } else {
            avaliacoesFormatadas.forEach(System.out::println);
        }
    }   
    
    private void listarTurmasPorDisciplina() {
        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        List<String> turmasFormatadas = controlador.listarTurmasPorDisciplina(codigoDisciplina);
        if (turmasFormatadas.isEmpty()) {
            System.out.println("Nenhuma turma encontrada para a disciplina informada.");
        } else {
            turmasFormatadas.forEach(System.out::println);
        }
    }

    //-------------- Funções de busca --------------
    private void buscarAlunoPorMatricula() {
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        String aluno = controlador.buscarAlunoPorMatricula(matricula);
        if (aluno != null) {
            System.out.println("Aluno encontrado: " + aluno);
        } else {
            System.out.println("Aluno não encontrado.");
        }
    }

    private void buscarTurmaPorCodigo() {
        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();
        String turma = controlador.buscarTurmaPorCodigo(codigoTurma);
        if (turma != null) {
            System.out.println("Turma encontrada: " + turma);
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    private void buscarDisciplina(){
        System.out.println("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        String disciplina = controlador.buscarDisciplina(codigoDisciplina);
        if (disciplina != null) {
            System.out.println("Disciplina encontrada: " + disciplina);
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

    private void buscarAlunosEmTurma() {
        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();
        List<String> alunos = controlador.buscarAlunosTurma(codigoTurma);
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado na turma informada.");
        } else {
            System.out.println("Alunos na turma " + codigoTurma + ":");
            alunos.forEach(System.out::println);
        }
    }

    private void buscarAvaliacoesPorAluno() {
        System.out.print("Digite o código do aluno: ");
        String codigoAluno = scanner.nextLine();
        List<String> avaliacoes = controlador.buscarAvaliacoesPorAluno(codigoAluno);
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada para o aluno informado.");
        } else {
            System.out.println("Avaliações do aluno " + codigoAluno + ":");
            avaliacoes.forEach(System.out::println);
        }
    }

    private void buscarAvaliacoesPorTurma(){
        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();
        List<String> avaliacoes = controlador.buscarAvaliacoesPorTurma(codigoTurma);
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada para a turma informada.");
        } else {
            System.out.println("Avaliações da turma " + codigoTurma + ":");
            avaliacoes.forEach(System.out::println);
        }
    }
    
    private void buscarAvaliacao() {
        System.out.print("Digite o código do aluno: ");
        String codigoAluno = scanner.nextLine();
        String codigoTurma;
        if(controlador.buscarAlunoPorMatricula(codigoAluno) == null){
            System.out.println("Aluno não encontrado.");
            return;
        }else{
            System.out.print("Digite o código da turma: ");
            codigoTurma = scanner.nextLine();
            if(controlador.buscarTurmaPorCodigo(codigoTurma) == null){
                System.out.println("Turma não encontrada.");
                return;
            }
        }

        List<String> avaliacoes = controlador.buscarAvaliacoesPorAlunoETurma(codigoAluno, codigoTurma);
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada para o aluno na turma informada.");
        } else {
            System.out.println("Avaliações do aluno " + codigoAluno + " na turma " + codigoTurma + ":");
            avaliacoes.forEach(System.out::println);
        }
    }

    //-------------- Funções de relatório --------------
    private void gerarRelatorioTurma() {
        System.out.print("Digite o código da turma: ");
        String codigoTurma = scanner.nextLine();
        String relatorio = controlador.gerarRelatorioTurma(codigoTurma);
        if (relatorio != null) {
            System.out.println("--------- Relatório da turma ----------");
            System.out.println(relatorio);
        } else {
            System.out.println("Turma não encontrada.");
        }
    }

    private void gerarRelatorioDisciplina() {
        System.out.print("Digite o código da disciplina: ");
        String codigoDisciplina = scanner.nextLine();
        String relatorio = controlador.gerarRelatorioDisciplina(codigoDisciplina);
        if (relatorio != null) {
            System.out.println("--------- Relatório da disciplina ----------");
            System.out.println(relatorio);
        } else {
            System.out.println("Disciplina não encontrada.");
        }
    }

//-------------- Funções de leitura de entrada do usuário --------------

    private int lerOpcaoInt(String prompt, int maxOpcao) {
        int opcao = -1;
        while (true) {
            System.out.print(prompt);
            String entrada = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entrada);
                if (opcao < 0 || opcao > maxOpcao) {
                    System.out.println("Opção inválida!");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida!");
            }
        }
        return opcao;
    }
    
    private int lerInteiro(String prompt) {
        int numero;
        while (true) {
            System.out.print(prompt);
            String entrada = scanner.nextLine();
            try {
                numero = Integer.parseInt(entrada);
                if (numero < 0) {
                    System.out.println("Valor inválido! Digite um número inteiro positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
        return numero;
    }
    
    private float lerFloat(String prompt) {
        float numero;
        while (true) {
            System.out.print(prompt);
            String entrada = scanner.nextLine();
            try {
                numero = Float.parseFloat(entrada);
                if (numero < 0) {
                    System.out.println("Valor inválido! Digite um número inteiro positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número inteiro.");
            }
        }
        return numero;
    }


}
