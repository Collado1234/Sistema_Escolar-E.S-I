/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IU_Sistema;
import java.util.Scanner;
import modelo.*;
import modelo.Controlador;
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
            System.out.println("7. Listagem");
            System.out.println("8. Buscas");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> cadastrarDisciplina();
                case 3 -> cadastrarTurma();
                case 4 -> registrarAvaliacao();
                case 5 -> consultarAvaliacoesPorAluno();
                case 6 -> adicionarAlunosNaTurma();
                case 7 -> submenuListagem();
                case 8 -> submenuBusca();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }
    
    private static void submenuListagem() {
        int opcaoListagem;
        do {
            System.out.println("\n----- Menu de Listagem -----");
            System.out.println("1. Listar Alunos");
            System.out.println("2. Listar Disciplinas");
            System.out.println("3. Listar Turmas");
            System.out.println("4. Listar Avaliações");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoListagem = Integer.parseInt(scanner.nextLine());

            switch (opcaoListagem) {
                case 1 -> listarAlunos();
                case 2 -> listarDisciplinas();
                case 3 -> listarTurmas();
                case 4 -> listarAvaliacoes();
                case 0 -> System.out.println("Retornando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoListagem != 0);
    }
    
    private static void submenuBusca() {
        int opcaoBusca;
        do {
            System.out.println("\n----- Menu de Busca -----");
            System.out.println("1. Buscar Aluno Nome");
            System.out.println("2. Buscar Aluno Matricula");
            System.out.println("3. Buscar Disciplina");
            System.out.println("4. Buscar Turma");
            System.out.println("5. Buscar Avaliacao");
            System.out.println("0. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcaoBusca = Integer.parseInt(scanner.nextLine());

            switch (opcaoBusca) {
                case 1 -> buscarAlunoNome();
                case 2 -> buscarAlunoMatricula();
                case 3 -> buscarDisciplina();
                case 4 -> buscarTurma();
//                case 0 -> System.out.println("Retornando ao menu principal...");
//                case 5 -> buscarAvaliacao();;
                default -> System.out.println("Opção inválida!");
            }
        } while (opcaoBusca != 0);
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
        float nota1 = Float.parseFloat(scanner.nextLine());

        System.out.print("Nota da Prova 2: ");
        float nota2 = Float.parseFloat(scanner.nextLine());

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

    // Métodos stub para listagem - você implementa o conteúdo deles depois
    private static void listarAlunos() {
        List<Aluno> alunos = controlador.listarAlunos(); // pega os alunos do controlador
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            System.out.println("Lista de Alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("- Nome: " + aluno.getNome_Aluno()+ ", Matrícula: " + aluno.getMatricula());
            }
        }
    }

    private static void listarDisciplinas() {
        List<Disciplina> disciplinas = controlador.listarDisciplinas(); // pega os alunos do controlador
        if (disciplinas.isEmpty()) {
            System.out.println("Nenhuma Disciplina cadastrada.");
        } else {
            System.out.println("Lista de Disciplinas:");
            for (Disciplina disciplina : disciplinas) {
                System.out.println("- Nome da disciplina: " + disciplina.getNome()+ ", Codigo: "
                + disciplina.getCodigo() + ", Carga horaria: " + disciplina.getCarga_horaria());
            }
        }
    }

    private static void listarTurmas() {
        List<Turma> turmas = controlador.listarTurmas(); // pega os alunos do controlador
        if (turmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada.");
        } else {
            System.out.println("Lista de Turmas:");
            for (Turma turma : turmas) {
                System.out.println("- Nome da turma: " + turma.getNomeTurma()+ ", Codigo: "
                + turma.getCodigoTurma() + ", Disciplina: " + turma.getDisciplina());
            }
        }
    }

    private static void listarAvaliacoes() {
        List<Avaliacao> avaliacoes = controlador.listarAvaliacoes(); // 
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliacao cadastrado.");
        } else {
            System.out.println("Lista de Avaliacoes:");
            for (Avaliacao avaliacao: avaliacoes) {
                System.out.println("- Nome do Aluno: " + avaliacao.getAluno().getNome_Aluno()+ ", Turma: "
                + avaliacao.getTurma().getNomeTurma() + "P1: " + avaliacao.getProva1()+ "P2: " + avaliacao.getProva2()
                + "Freqencia: "+ avaliacao.calcFrequencia());
            }
        }
    }
    
    
    //metodos do submenu Busca
    
    private static void buscarAlunoNome(){
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
       
        Aluno aluno = controlador.buscarAlunoNome(nome);
        if(aluno==null){
            System.out.println("Aluno nao encontrado!");
            return;
        }
        
        List<Turma> turmas_matriculado = controlador.turmasAlunoCadastrado(aluno.getMatricula());
        System.out.println("Nome: "+aluno.getNome_Aluno()+
                           "\nMatricula: "+aluno.getMatricula());
        
        if(turmas_matriculado==null){
            System.out.println("Nao matriculado em nenhuma turma");
        }
        for(Turma turmas : turmas_matriculado){
            System.out.println("Turma: "+turmas.getNomeTurma());
        }                                       
       return;                             
    }
    
    private static void buscarAlunoMatricula(){
        System.out.print("Matricula do aluno: ");
        String matricula = scanner.nextLine();
       
        Aluno aluno = controlador.buscarAlunoPorMatricula(matricula);
        if(aluno==null){
            System.out.println("Aluno nao encontrado!");
            return;
        }
        
        List<Turma> turmas_matriculado = controlador.turmasAlunoCadastrado(matricula);
        System.out.println("Nome: "+aluno.getNome_Aluno()+
                           "\nMatricula: "+aluno.getMatricula());
        
        if(turmas_matriculado==null){
            System.out.println("Nao matriculado em nenhuma turma");
        }
        for(Turma turmas : turmas_matriculado){
            System.out.println("Turma: "+turmas.getNomeTurma());
        }                                       
       return;                             
    }

    private static void buscarDisciplina(){
        System.out.println("Codigo da Disciplina: ");
        String codigo = scanner.nextLine();
        
        Disciplina disc = controlador.buscarDisciplina(codigo);        
        if(disc == null){
            System.out.println("Disciplina nao encontrada!");
            return;
        }
        List<Turma> turmas_disciplina = controlador.turmas_vinculadas_disciplina(codigo);
        System.out.println("--Disciplina--");
        System.out.println("Nome: "+disc.getNome());
        System.out.println("Codigo: "+disc.getCodigo());
        System.out.println("Carga Horaria: "+disc.getCarga_horaria());
        if(turmas_disciplina==null){
            System.out.println("Disciplina nao possui turmas vinculadas");
            return;
        }
        for(Turma turmas: turmas_disciplina){
            System.out.println("--Turmas da Disciplina--");
            System.out.println("Turma: "+turmas.getNomeTurma()+ "Codigo: "+turmas.getCodigoTurma());
        }
        return;
    }
    
    private static void buscarTurma(){
        System.out.println("Codigo da Turma: ");
        String codigoTurma = scanner.nextLine();
        Turma turma = controlador.buscarTurmaPorCodigo(codigoTurma);
        if(turma == null){
            System.out.println("Turma nao encontrada!");   
            return;
        }
        List<Aluno> alunos_matriculados = controlador.buscarAlunosTurma(codigoTurma);               
        System.out.println("Nome: "+turma.getNomeTurma());
        System.out.println("Codigo: "+turma.getCodigoTurma());
        System.out.println("Disciplina: "+turma.getDisciplina());
        if(alunos_matriculados==null){
            System.out.println("Nao possui alunos matriculados na turma");
            return;
        }
        for(Aluno alunos : alunos_matriculados){
            System.out.println("Alunos matriculados na turma:");
            System.out.println("Nome: "+alunos.getNome_Aluno()+" Matricula: "+alunos.getMatricula());
        }        
    }
}


