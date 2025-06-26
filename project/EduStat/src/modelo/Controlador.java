package modelo;
import java.util.List;

public class Controlador{

    private ServicoCatalogoGeral servico;
    private static Controlador instancia;

    public Controlador() {
        this.servico = new ServicoCatalogoGeral(); //carrega os dados
    }        
    
     // Método público para obter a instância única
    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }
    
    // ------------------- Cadastros -------------------
    public boolean cadastrarAluno(String nome, String matricula) {
        try {
            Aluno aluno = new Aluno(nome, matricula);
            servico.adicionarAluno(aluno);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }
    }
    
    public boolean cadastrarDisciplina(String nome, String codigoDisciplina, int cargaHoraria) {
        try{
            Disciplina disciplina = new Disciplina(nome,codigoDisciplina,cargaHoraria);
            servico.adicionarDisciplina(disciplina);
            return true;
        }catch(IllegalArgumentException e){
            return false;
        }       
    }
    
    public boolean cadastrarTurma(String nomeTurma, String codigoTurma, String codigoDisciplina) {
        try {
            // Verifica se a disciplina existe usando o serviço
            Disciplina disciplina = servico.buscarDisciplina(codigoDisciplina);
            if (disciplina == null) return false;

            // Cria a turma
            Turma turma = new Turma(nomeTurma, codigoTurma, disciplina);

            // Tenta adicionar via serviço
            servico.adicionarTurma(turma);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    public boolean cadastrarAvaliacao(String codAluno, String codTurma, float nota1, float nota2, int faltas) {
        try {
            Aluno aluno = servico.buscarAlunoPorMatricula(codAluno);
            Turma turma = servico.buscarTurmaPorCodigo(codTurma);

            if (aluno == null || turma == null) {
                System.out.println("Aluno ou turma não encontrados.");
                return false;
            }

            Avaliacao avaliacao = new Avaliacao(aluno, turma, nota1, nota2, faltas);
            servico.adicionarAvaliacao(avaliacao);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar avaliação: " + e.getMessage());
            return false;
        }
    }
    
    public List<String> cadastrarAlunosEmTurma(String codigoTurma, List<String> matriculas) {
        return servico.adicionarAlunosEmTurma(codigoTurma, matriculas);
    }

    //------------------ Buscas ---------------
    
    public String buscarAlunoPorMatricula(String matricula) {
        Aluno aluno = servico.buscarAlunoPorMatricula(matricula);
        return aluno != null ? aluno.toString() : "Aluno não encontrado.";
    }


    public String buscarAlunoNome(String nomeAluno) {
        Aluno aluno = servico.buscarAlunoNome(nomeAluno);
        return aluno != null ? aluno.toString() : "Aluno não encontrado.";
    }

    public String buscarDisciplina(String codigoDisciplina) {
        Disciplina d = servico.buscarDisciplina(codigoDisciplina);
        return d != null ? d.toString() : "Disciplina não encontrada.";
    }

    public String buscarTurmaPorCodigo(String codigoTurma) {
        Turma turma = servico.buscarTurmaPorCodigo(codigoTurma);
        return turma != null ? turma.toString() : "Turma não encontrada.";
    }

    public List<String> buscarAlunosTurma(String codigoTurma) {
        try {
            return servico.buscarAlunosTurma(codigoTurma).stream()
                          .map(Aluno::toString)
                          .toList();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return List.of();
        }
    }

    public List<String> buscarAvaliacoesPorAluno(String codigoAluno) {
        return servico.buscarAvaliacoesPorAluno(codigoAluno).stream()
                      .map(Avaliacao::toString)
                      .toList();
    }

    public List<String> buscarAvaliacoesPorTurma(String codigoTurma) {
        return servico.buscarAvaliacoesPorTurma(codigoTurma).stream()
                      .map(Avaliacao::toString)
                      .toList();
    }

    public List<String> buscarAvaliacoesPorAlunoETurma(String codigo_aluno, String codigo_turma) {
        return servico.buscarAvaliacoesPorAlunoETurma(codigo_aluno,codigo_turma).stream()
                      .map(Avaliacao::toString)
                      .toList();
    }
    
    
    //------------------------- Listagem ---------------------------
    
    public List<String> listarAlunosFormatados() {
        return servico.listarAlunos().stream()
                      .map(Aluno::toString)  // ou um formato mais específico
                      .toList();
    }
        
    public List<String> listarDisciplinasFormatadas() {
        return servico.listarDisciplinas().stream()
                      .map(Disciplina::toString)  // ou um formato mais específico
                      .toList();
    }
    
    public List<String> listarTurmasFormatadas() {
        return servico.listarTurmas().stream()
                      .map(Turma::toString)  // ou um formato mais específico
                      .toList();
    }            

    public List<String> listarAvaliacoesFormatadas() {
        return servico.listarAvaliacoes().stream()
                      .map(Avaliacao::toString)  // ou um formato mais específico
                      .toList();
    }
    
    public List<String> listarTurmasAlunoCadastrado(String matricula){
        return servico.listarTurmasAluno(matricula).stream()
                .map(Turma::toString).toList();
    }
    
    public List<String> listarTurmasPorDisciplina(String codigoDisciplina){
        return servico.listarTurmasPorDisciplina(codigoDisciplina).stream()
                .map(Turma::toString).toList();
    }
    
    //----------------- Relatorio ---------------------
    
    public String gerarRelatorioTurma(String codigoTurma) {
        return servico.gerarRelatorioTurma(codigoTurma);
    }

    public String gerarRelatorioDisciplina(String codigoDisciplina) {
        return servico.gerarRelatorioDisciplina(codigoDisciplina);
    }        
                
}
