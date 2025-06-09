package modelo;

import modelo.*;
import Persistencia.*;
import java.util.ArrayList;
import java.util.List;

public class Controlador{

    private Catalogo_CorpoDocente corpoDocente;
    private Catalogo_RegistroDisciplinas registroDisciplinas;
    private Catalogo_RegistroTurmas registroTurmas;
    private Catalogo_RegistroAvaliacoes registroAvaliacoes;

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

    public Aluno buscarAlunoPorMatricula(String matricula){
        return corpoDocente.buscarAlunoPorMatricula(matricula);
    }
  
    public Aluno buscarAlunoNome(String nome){
        return corpoDocente.buscarAlunoPorNome(nome);
    }
    
    public List<Turma> turmasAlunoCadastrado(String matricula){
        return registroTurmas.lista_Turmas_Aluno_Matriculado(matricula);
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
    
    public Disciplina buscarDisciplina(String codigo){
        return registroDisciplinas.buscarPorCodigo(codigo);        
    }
    
    public List<Turma> turmas_vinculadas_disciplina(String codigoDisciplina){
        return registroTurmas.buscarPorDisciplina(codigoDisciplina);
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
    
    public Turma buscarTurmaPorCodigo(String codigoTurma){
        return registroTurmas.buscarPorCodigo(codigoTurma);
    }
    
    public List<Aluno> buscarAlunosTurma(String codigo){
        return registroTurmas.buscarPorCodigo(codigo).getAlunos();
    }

    // ------------------- Avaliação -------------------
    public boolean lancarAvaliacao(Aluno aluno, Turma turma, float nota1, float nota2, int faltas) {
        // Verifica se já existe uma avaliação para esse aluno na turma
        boolean existe = registroAvaliacoes.listarAvaliacoes().stream()
            .anyMatch(a -> a.getAluno().equals(aluno) && a.getTurma().equals(turma));

        if (existe) return false;
        
        if(nota1 < 0 || nota1 > 10 || nota2 < 0 || nota2 > 10 ){
            System.out.println("Erro: Valor de Notas invalidas");
            return false;
        }
        if(faltas < 0 || faltas > turma.getDisciplina().getCarga_horaria()){  //violacao principio de demeter, estudar melhoria
            System.out.println("Erro: Quantidade de Faltas Invalidas");
            return false;
        }
        Avaliacao avaliacao = new Avaliacao(aluno, turma, nota1, nota2, faltas);
        registroAvaliacoes.adicionarAvaliacao(avaliacao);
        PersistenciaRegistroAvaliacoes.salvar(registroAvaliacoes);
        return true;
    }
    
    public List<Avaliacao> listarAvaliacoes(){
        return registroAvaliacoes.listarAvaliacoes();
    }
    
    
    public List<String> adicionarAlunosEmTurma(String codigoTurma, List<String> matriculas) {
        Turma turma = registroTurmas.buscarPorCodigo(codigoTurma);
        List<String> erros = new ArrayList<>();

        if (turma == null) {
            erros.add("Turma não encontrada.");
            return erros;
        }

        for (String matricula : matriculas) {
            Aluno aluno = corpoDocente.buscarAlunoPorMatricula(matricula.trim());

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

        PersistenciaRegistroTurmas.salvar(registroTurmas);
        return erros; // Retorna lista vazia se tudo ok
}

    //public void 

    public List<Avaliacao> buscarAvaliacoesPorAluno(Aluno aluno) {
        return registroAvaliacoes.buscarPorAluno(aluno);
    }

//    public List<Avaliacao> buscarAvaliacoesPorTurma(String matricula) {;;
//       // return registroAvaliacoes.buscarPorTurma(turma);
//    }

    public List<Avaliacao> buscarAvaliacoesPorAlunoETurma(Aluno aluno, Turma turma) {
        return registroAvaliacoes.buscarPorAlunoETurma(aluno, turma);
    }
                      
}
