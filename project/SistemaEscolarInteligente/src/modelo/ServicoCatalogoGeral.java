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
    public void adicionarAluno(Aluno aluno){
        if (alunos.buscarAlunoPorMatricula(aluno.getMatricula()) != null) {
            throw new IllegalArgumentException("Já existe um aluno com essa matrícula.");
        }

        alunos.adicionarAluno(aluno);
        PersistenciaRegistroAlunos.salvar(alunos); // ou encapsular isso dentro do catálogo, como já está
    }
    
    public void adicionarTurma(Turma turma){
        if (turmas.buscarPorCodigo(turma.getCodigoTurma()) != null) {
            throw new IllegalArgumentException("Já existe uma turma com essa matrícula.");
        }
        turmas.adicionarTurma(turma);
        PersistenciaRegistroTurmas.salvar(turmas);
    }
    
    public void adicionarAvaliacao(Avaliacao avaliacao) {
        // Verifica se já existe avaliação do aluno na turma
        boolean existe = avaliacoes.listarAvaliacoes().stream()
            .anyMatch(a -> a.getAluno().equals(avaliacao.getAluno())
                       && a.getTurma().equals(avaliacao.getTurma()));
        if (existe) {
            throw new IllegalArgumentException("Já existe uma avaliação para esse aluno na turma.");
        }

        float n1 = avaliacao.getProva1();
        float n2 = avaliacao.getProva2();
        int faltas = avaliacao.getTotal_faltas();
        int cargaHoraria = avaliacao.getTurma().getDisciplina().getCarga_horaria();

        if (n1 < 0 || n1 > 10 || n2 < 0 || n2 > 10) {
            throw new IllegalArgumentException("Notas devem estar entre 0 e 10.");
        }

        if (faltas < 0 || faltas > cargaHoraria) {
            throw new IllegalArgumentException("Faltas inválidas para a carga horária da disciplina.");
        }

        avaliacoes.adicionarAvaliacao(avaliacao);
        PersistenciaRegistroAvaliacoes.salvar(avaliacoes);
    }

    public void adicionarDisciplina(Disciplina disciplina){
        if (disciplinas.buscarPorCodigo(disciplina.getCodigo()) != null) {
            throw new IllegalArgumentException("Já existe uma disciplina com esse codigo.");
        }
        disciplinas.adicionarDisciplina(disciplina);        
        PersistenciaRegistroDisciplinas.salvar(disciplinas);
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

            if (turma.getAlunos().contains(aluno)) {
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
    
    public List<Aluno> buscarAlunosTurma(String codigo){
        return turmas.buscarPorCodigo(codigo).getAlunos();
    }
    
    public List<Avaliacao> buscarAvaliacoesPorAluno(String codigoAluno) {
        Aluno aluno = buscarAlunoPorMatricula(codigoAluno);
        return avaliacoes.buscarPorAluno(aluno);
    }

    public List<Avaliacao> buscarAvaliacoesPorTurma(String codigoTurma) {
        Turma turma = buscarTurmaPorCodigo(codigoTurma);
        return avaliacoes.buscarPorTurma(turma);
    }

    public List<Avaliacao> buscarAvaliacoesPorAlunoETurma(String codigoAluno, String codigoTurma) {
        Aluno alunoEncontrado = alunos.buscarAlunoPorMatricula(codigoAluno);
        Turma turmaEncontrada = turmas.buscarPorCodigo(codigoTurma);
        return avaliacoes.buscarPorAlunoETurma(alunoEncontrado, turmaEncontrada);
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

        AnaliseEstatisticaBase analise = new AnaliseTurma(turma, avaliacoes.listarAvaliacoes());
        return analise.gerarRelatorio();
    }

    public String gerarRelatorioDisciplina(String codigoDisciplina) {
        Disciplina disciplina = disciplinas.buscarPorCodigo(codigoDisciplina);
        if (disciplina == null) return "Disciplina não encontrada.";

        AnaliseEstatisticaBase analise = new AnaliseDIsciplina(disciplina, avaliacoes.listarAvaliacoes());
        return analise.gerarRelatorio();
    }

}
