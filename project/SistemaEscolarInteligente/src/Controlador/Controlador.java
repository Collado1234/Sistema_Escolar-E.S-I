package controlador;

import modelo.*;
import Persistencia.*;

import java.util.List;

public class Controlador{

    private CorpoDocente corpoDocente;
    private RegistroDisciplinas registroDisciplinas;
    private RegistroTurmas registroTurmas;
    private RegistroAvaliacoes registroAvaliacoes;

    public Controlador() {
        this.corpoDocente = PersistenciaCorpoDocente.carregar();
        this.registroDisciplinas = PersistenciaRegistroDisciplinas.carregar();
        this.registroTurmas = PersistenciaRegistroTurmas.carregar();
        this.registroAvaliacoes = PersistenciaRegistroAvaliacoes.carregar();
    }

    // ------------------- Aluno -------------------
    public boolean cadastrarAluno(String nome, String matricula) {
       // Verifica se já existe um aluno com a mesma matrícula
        boolean existe = corpoDocente.getAlunos().stream().anyMatch(a -> a.getMatricula().equals(matricula));

        if (existe) return false;

        // Só cria e adiciona o aluno se não existir
        Aluno aluno = new Aluno(nome, matricula);
        corpoDocente.adicionarAluno(aluno);
        PersistenciaCorpoDocente.salvar(corpoDocente);
        return true;
    }

    public List<Aluno> listarAlunos() {
        return corpoDocente.getAlunos();
    }

    // ------------------- Disciplina -------------------
    public boolean cadastrarDisciplina(String nome, String codigoDisciplina, int cargaHoraria) {
        // Verifica se já existe uma disciplina com o mesmo código
        boolean existe = registroDisciplinas.listarDisciplinas().stream()
            .anyMatch(d -> d.getCodigo().equals(codigoDisciplina));

        if (existe) return false;

        // Só cria e adiciona se não existir
        Disciplina disciplina = new Disciplina(nome, codigoDisciplina, cargaHoraria);
        registroDisciplinas.adicionarDisciplina(disciplina);
        PersistenciaRegistroDisciplinas.salvar(registroDisciplinas);
        return true;
    }

    public List<Disciplina> listarDisciplinas() {
        return registroDisciplinas.listarDisciplinas();
    }

    // ------------------- Turma -------------------
   public boolean cadastrarTurma(String nomeTurma, String codigoTurma, String codigoDisciplina) {
        // Verifica se já existe turma com esse código
        boolean existe = registroTurmas.listarTurmas().stream()
            .anyMatch(t -> t.getCodigoTurma().equals(codigoTurma));

        if (existe) return false;

        // Busca a disciplina correspondente
        Disciplina disciplina = registroDisciplinas.listarDisciplinas().stream()
            .filter(d -> d.getCodigo().equals(codigoDisciplina))
            .findFirst()
            .orElse(null);

        if (disciplina == null) return false; // Disciplina não encontrada

        // Cria e persiste a nova turma
        Turma turma = new Turma(nomeTurma,codigoTurma, disciplina);
        registroTurmas.adicionarTurma(turma);
        PersistenciaRegistroTurmas.salvar(registroTurmas);
        return true;
}


    public List<Turma> listarTurmas() {
        return registroTurmas.listarTurmas();
    }

    // ------------------- Avaliação -------------------
    public boolean lancarAvaliacao(Aluno aluno, Turma turma, int nota1, int nota2, int faltas) {
        // Verifica se já existe uma avaliação para esse aluno na turma
        boolean existe = registroAvaliacoes.listarAvaliacoes().stream()
            .anyMatch(a -> a.getAluno().equals(aluno) && a.getTurma().equals(turma));

        if (existe) return false;

        Avaliacao avaliacao = new Avaliacao(aluno, turma, nota1, nota2, faltas);
        registroAvaliacoes.adicionarAvaliacao(avaliacao);
        PersistenciaRegistroAvaliacoes.salvar(registroAvaliacoes);
        return true;
    }

    //public void 

    public List<Avaliacao> buscarAvaliacoesPorAluno(Aluno aluno) {
        return registroAvaliacoes.buscarPorAluno(aluno);
    }

    public List<Avaliacao> buscarAvaliacoesPorTurma(Turma turma) {
        return registroAvaliacoes.buscarPorTurma(turma);
    }

    public List<Avaliacao> buscarAvaliacoesPorAlunoETurma(Aluno aluno, Turma turma) {
        return registroAvaliacoes.buscarPorAlunoETurma(aluno, turma);
    }
    
    public Aluno buscarAlunoPorMatricula(String matricula){
        return corpoDocente.buscarAlunoPorMatricula(matricula);
    }
    
    public Turma buscarTurmaPorCodigo(String codigoTurma){
        return registroTurmas.buscarPorCodigo(codigoTurma);
    }
    
    
}
