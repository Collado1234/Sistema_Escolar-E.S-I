/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;

/**
 *
 * @author renna
 */
public class Catalogo_RegistroAvaliacoes implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Avaliacao> avaliacoes;

    public Catalogo_RegistroAvaliacoes() {
        this.avaliacoes = new ArrayList<>();
    }

    public boolean adicionarAvaliacao(Aluno aluno, Turma turma,float nota1,float nota2,int total_faltas) {
        Avaliacao av = criarAvaliacao(aluno, turma, nota1, nota2, total_faltas);
        if(av == null){
            return false;
        }else{
            this.avaliacoes.add(av);
            return true;
        }
    }

    public List<Avaliacao> listarAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

    public List<Avaliacao> buscarPorAluno(String codigoAluno) {
        List<Avaliacao> lista_avaliacao = new ArrayList<>();
        for(Avaliacao  avaliacao : this.avaliacoes){
            if(avaliacao.getAluno().getMatricula().equals(codigoAluno)){
                lista_avaliacao.add(avaliacao);
            }
        }
        return lista_avaliacao;
    }    

    public List<Avaliacao> buscarPorTurma(String codigoTurma) { ///////////////////////// aqui, testar mudança
        List<Avaliacao> lista_avaliacao = new ArrayList<>();
        for(Avaliacao  avaliacao : this.avaliacoes){
            if(avaliacao.getTurma().getCodigoTurma().equals(codigoTurma)){
                lista_avaliacao.add(avaliacao);
            }
        }
        return lista_avaliacao;
    }


    public List<Avaliacao> buscarPorAlunoETurma(String codigoAluno, String codigoTurma) {
        List<Avaliacao> lista_avaliacao = new ArrayList<>();
        for(Avaliacao  avaliacao : this.avaliacoes){
            if(avaliacao.getTurma().getCodigoTurma().equals(codigoTurma) && avaliacao.getAluno().getMatricula().equals(codigoAluno)){
                lista_avaliacao.add(avaliacao);
            }
        }
        return lista_avaliacao;
    }

    public void limparAvaliacoes() {
        this.avaliacoes.clear();
    }
    
    private Avaliacao criarAvaliacao(Aluno aluno, Turma turma,float nota1,float nota2,int total_faltas){
        if(buscarPorAlunoETurma(aluno.getMatricula(), turma.getCodigoTurma()).isEmpty()){
            return new Avaliacao(aluno,turma,nota1,nota2,total_faltas);
        }else{
            return null;
        }
    }
}
