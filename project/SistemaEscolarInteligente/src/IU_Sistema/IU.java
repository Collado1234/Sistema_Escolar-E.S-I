/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IU_Sistema;
import java.util.Scanner;
import modelo.*;
import controlador.Controlador;
import java.util.List;

/**
 *  Classe onde desenvolveremos a interface do projeto
 * @author renna
 */
public class IU {
    private static Scanner scanner = new Scanner(System.in);
    private static Controlador controlador = new Controlador();

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== Sistema Escolar Inteligente =====");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Cadastrar Disciplina");
            System.out.println("3. Cadastrar Turma");
            System.out.println("4. Lançar Avaliação");
            System.out.println("5. Consultar Avaliações por Aluno");
            System.out.println("6. Adicionar Alunos à Turma (vários)");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarDisciplina();
                case 3 -> cadastrarTurma();
                case 4 -> registrarAvaliacao();
                case 5 -> consultarAvaliacoesPorAluno();
                case 6 -> adicionarAlunosNaTurma();
                case 7 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 7);
    }

    private static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();

        boolean sucesso = controlador.cadastrarAluno(nome, matricula);
        System.out.println(sucesso ? "Aluno cadastrado com sucesso." : "Aluno já existe.");
    }

    private static void cadastrarDisciplina() {
        System.out.print("Nome da disciplina: ");
        String nome = scanner.nextLine();
        System.out.print("Código da disciplina: ");
        String codigo = scanner.nextLine();
        System.out.print("Carga horária: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());

        boolean sucesso = controlador.cadastrarDisciplina(nome, codigo, cargaHoraria);
        System.out.println(sucesso ? "Disciplina cadastrada com sucesso." : "Disciplina já existe.");
    }

    private static void cadastrarTurma() {
        System.out.print("Nome da turma: ");
        String nome = scanner.nextLine();
        System.out.print("Código da turma: ");
        String codigo = scanner.nextLine();
        System.out.print("Código da Disciplina: ");
        String codigo_disciplina = scanner.nextLine();

        boolean sucesso = controlador.cadastrarTurma(nome, codigo, codigo_disciplina);
        System.out.println(sucesso ? "Turma cadastrada com sucesso." : "Turma já existe.");
    }

    private static void registrarAvaliacao() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = controlador.buscarAlunoPorMatricula(matricula);

        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();
        Turma turma = controlador.buscarTurmaPorCodigo(codigoTurma);

        if (aluno == null || turma == null) {
            System.out.println("Aluno ou turma não encontrados.");
            return;
        }

        System.out.print("Nota da Prova 1: ");
        int nota1 = Integer.parseInt(scanner.nextLine());

        System.out.print("Nota da Prova 2: ");
        int nota2 = Integer.parseInt(scanner.nextLine());

        System.out.print("Total de faltas: ");
        int faltas = Integer.parseInt(scanner.nextLine());

        boolean sucesso = controlador.lancarAvaliacao(aluno, turma, nota1, nota2, faltas);
        System.out.println(sucesso ? "Avaliação lançada com sucesso." : "Avaliação já existe para este aluno e turma.");
    }

    private static void consultarAvaliacoesPorAluno() {
        System.out.print("Matrícula do aluno: ");
        String matricula = scanner.nextLine();
        Aluno aluno = controlador.buscarAlunoPorMatricula(matricula);

        if (aluno == null) {
            System.out.println("Aluno não encontrado.");
            return;
        }

        var avaliacoes = controlador.buscarAvaliacoesPorAluno(aluno);
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliação encontrada para esse aluno.");
        } else {
            System.out.println("Avaliações do aluno:");
            for (Avaliacao a : avaliacoes) {
                System.out.println("- Turma: " + a.getTurma().getCodigoTurma());
            }
        }
    }
    
    private static void adicionarAlunosNaTurma() {
        System.out.print("Código da turma: ");
        String codigoTurma = scanner.nextLine();

        System.out.print("Matrículas dos alunos (separadas por vírgula): ");
        String input = scanner.nextLine();
        List<String> matriculas = List.of(input.split(","));

        List<String> erros = controlador.adicionarAlunosEmTurma(codigoTurma, matriculas);

        if (erros.isEmpty()) {
            System.out.println("Todos os alunos foram adicionados com sucesso à turma.");
        } else {
            System.out.println("Alguns alunos não foram adicionados:");
            erros.forEach(System.out::println);
        }
    }
}

