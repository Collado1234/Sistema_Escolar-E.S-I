/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Persistencia.*;
import java.util.List;
import java.util.ArrayList;



/**
 *
 * @author renna
 */
public class ServicoCatalogoGeral {
    private Catalogo_RegistroAlunos alunos;
    private Catalogo_RegistroTurmas turmas;
    private Catalogo_RegistroDisciplinas disciplinas;
    private Catalogo_RegistroAvaliacoes avaliacoes;
    
    public ServicoCatalogoGeral(){
        this.alunos = PersistenciaRegistroAlunos.carregar();
        this.disciplinas = PersistenciaRegistroDisciplinas.carregar();
        this.turmas = PersistenciaRegistroTurmas.carregar();
        this.avaliacoes = PersistenciaRegistroAvaliacoes.carregar();
    }
    
    //---------Cadastros---------
    public boolean adicionarAluno(String nome, String matricula){
        boolean flag = alunos.adicionarAluno(nome,matricula);     
        PersistenciaRegistroAlunos.salvar(alunos); // ou encapsular isso dentro do catálogo, como já está
        return flag;
    }
    
    public boolean adicionarTurma(String nomeTurma, String codigoTurma, String codigoDisciplina){
        Disciplina disc = buscarDisciplina(codigoDisciplina);
        if(disc == null){
            throw new IllegalArgumentException("Disciplina nao encontrada");
        }
        
        if(!turmas.adicionarTurma(nomeTurma, codigoTurma, disc)){
            return false;
        }    
        
        PersistenciaRegistroTurmas.salvar(turmas);
        return true;
    }
    
    public boolean adicionarAvaliacao(String codAluno, String codTurma, float n1, float n2, int faltas) {
        try {
            // Busca entidades pelos códigos
            Aluno aluno = buscarAlunoPorMatricula(codAluno);
            Turma turma = buscarTurmaPorCodigo(codTurma);

            // Validação de existência
            if (aluno == null || turma == null) {
                System.out.println("Aluno ou turma não encontrados.");
                return false;
            }

            // Validação de notas
            if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10) {
                throw new IllegalArgumentException("Notas devem estar entre 0 e 10.");
            }

            // Validação de faltas
            int cargaHoraria = turma.getDisciplina().getCarga_horaria();
            if (faltas < 0 || faltas > cargaHoraria) {
                throw new IllegalArgumentException("Faltas inválidas para a carga horária da disciplina.");
            }

            // Tenta adicionar a avaliação. O catálogo decide se já existe ou não.
            boolean sucesso = avaliacoes.adicionarAvaliacao(aluno, turma, n1, n2, faltas);
            if (!sucesso) {
                throw new IllegalArgumentException("Já existe uma avaliação para esse aluno na turma.");
            }

            // Persistência
            PersistenciaRegistroAvaliacoes.salvar(avaliacoes);
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar avaliação: " + e.getMessage());
            return false;
        }
    }

        

    public boolean adicionarDisciplina(String nome, String codigo, int cargaHoraria) {
        try {
            // Validação de carga horária
            if (cargaHoraria <= 0) {
                throw new IllegalArgumentException("Carga horária inválida. Deve ser maior que zero.");
            }

            // Validação de código duplicado no catálogo
            if (!disciplinas.adicionarDisciplina(nome, codigo, cargaHoraria)) {
                throw new IllegalArgumentException("Já existe uma disciplina com esse código.");
            }

            // Persistência
            PersistenciaRegistroDisciplinas.salvar(disciplinas);
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
            return false;
        }
    }

           
    public List<String> adicionarAlunosEmTurma(String codigoTurma, List<String> matriculas) {
        List<String> erros = new ArrayList<>();
        Turma turma = turmas.buscarPorCodigo(codigoTurma);

        if (turma == null) {
            erros.add("Turma não encontrada.");
            return erros;
        }

        for (String matricula : matriculas) {
            Aluno aluno = alunos.buscarAlunoPorMatricula(matricula.trim());

            if (aluno == null) {
                erros.add("Aluno com matrícula " + matricula + " não encontrado.");
                continue;
            }
            
            boolean jaMatriculado = turma.getAlunos().stream()
            .anyMatch(a -> a.getMatricula().equals(matricula.trim()));

            if (jaMatriculado) {
                erros.add("Aluno com matrícula " + matricula + " já está na turma.");
                continue;
            }

            turma.adicionarAluno(aluno);
        }

        PersistenciaRegistroTurmas.salvar(turmas);
        return erros;
    }

    //---------Buscas-----------    
    public Aluno buscarAlunoPorMatricula(String matricula){
        return alunos.buscarAlunoPorMatricula(matricula);
    }
    
    public Aluno buscarAlunoNome(String nome){
        return alunos.buscarAlunoPorNome(nome);
    }
    
    public Disciplina buscarDisciplina(String codigo){
        return disciplinas.buscarPorCodigo(codigo);        
    }
    
    public Turma buscarTurmaPorCodigo(String codigoTurma){
        return turmas.buscarPorCodigo(codigoTurma);
    }
    
    public List<Aluno> buscarAlunosTurma(String codigoTurma){
        Turma turma = turmas.buscarPorCodigo(codigoTurma);
        if (turma == null) {
            throw new IllegalArgumentException("Turma não encontrada: " + codigoTurma);
        }
        return turma.getAlunos();
    }
    
    public List<Avaliacao> buscarAvaliacoesPorAluno(String codigoAluno) {
        return avaliacoes.buscarPorAluno(codigoAluno);
    }

    public List<Avaliacao> buscarAvaliacoesPorTurma(String codigoTurma) {
        //Turma turma = buscarTurmaPorCodigo(codigoTurma);
        return avaliacoes.buscarPorTurma(codigoTurma);
    }

    public List<Avaliacao> buscarAvaliacoesPorAlunoETurma(String codigoAluno, String codigoTurma) {
        // Aluno alunoEncontrado = alunos.buscarAlunoPorMatricula(codigoAluno);
        // Turma turmaEncontrada = turmas.buscarPorCodigo(codigoTurma);
        return avaliacoes.buscarPorAlunoETurma(codigoAluno, codigoTurma);
    }

    // ----------- Listagem --------------
    public List<Aluno> listarAlunos() {
        return alunos.getAlunos();
    }
    
    public List<Disciplina> listarDisciplinas() {
        return disciplinas.listarDisciplinas();
    }
    
    public List<Turma> listarTurmas() {
        return turmas.listarTurmas();
    }
    
    public List<Avaliacao> listarAvaliacoes(){
        return avaliacoes.listarAvaliacoes();
    }     
    
    public List<Turma> listarTurmasAluno(String matricula){
        return turmas.lista_Turmas_Aluno_Matriculado(matricula);
    }
    
    public List<Turma> listarTurmasPorDisciplina(String codigoDisciplina){
        return turmas.buscarPorDisciplina(codigoDisciplina);
    }
    
    //--------------- Avaliacao ----------------
    public String gerarRelatorioTurma(String codigoTurma) {
        Turma turma = turmas.buscarPorCodigo(codigoTurma);
        if (turma == null) return "Turma não encontrada.";
        List<Avaliacao> avaliacoesTurma = avaliacoes.buscarPorTurma(codigoTurma);
        if(avaliacoesTurma == null || avaliacoesTurma.isEmpty()) return "Nenhuma avaliação encontrada para a turma.";
        AnaliseEstatisticaBase analise = new AnaliseTurma(turma, avaliacoesTurma);
        return analise.gerarRelatorio();
    }

    public String gerarRelatorioDisciplina(String codigoDisciplina) {
        Disciplina disciplina = disciplinas.buscarPorCodigo(codigoDisciplina);
        if (disciplina == null) return "Disciplina não encontrada.";

        AnaliseEstatisticaBase analise = new AnaliseDIsciplina(disciplina, avaliacoes.listarAvaliacoes());
        return analise.gerarRelatorio();
    }

}
