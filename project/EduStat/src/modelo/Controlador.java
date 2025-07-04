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
        return servico.adicionarAluno(nome,matricula);
    }
    
    public boolean cadastrarDisciplina(String nome, String codigoDisciplina, int cargaHoraria) {                
        return servico.adicionarDisciplina(nome,codigoDisciplina, cargaHoraria);           
    }
    
    public boolean cadastrarTurma(String nomeTurma, String codigoTurma, String codigoDisciplina) {        
        return servico.adicionarTurma(nomeTurma, codigoTurma, codigoDisciplina);
    }
    
    public boolean cadastrarAvaliacao(String codAluno, String codTurma, float nota1, float nota2, int faltas) {
        return servico.adicionarAvaliacao(codAluno, codTurma, nota1, nota2, faltas);
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
