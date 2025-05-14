/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author renna
 */
public class RegistroAvaliacoes implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Avaliacao> avaliacoes;

    public RegistroAvaliacoes() {
        this.avaliacoes = new ArrayList<>();
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes() {
        return new ArrayList<>(avaliacoes);
    }

    public List<Avaliacao> buscarPorAluno(Aluno aluno) {
        return avaliacoes.stream()
                .filter(av -> av.getAluno().equals(aluno))
                .collect(Collectors.toList());
    }

    public List<Avaliacao> buscarPorTurma(Turma turma) {
        return avaliacoes.stream()
                .filter(av -> av.getTurma().equals(turma))
                .collect(Collectors.toList());
    }

    public List<Avaliacao> buscarPorAlunoETurma(Aluno aluno, Turma turma) {
        return avaliacoes.stream()
                .filter(av -> av.getAluno().equals(aluno) && av.getTurma().equals(turma))
                .collect(Collectors.toList());
    }

    public void limparAvaliacoes() {
        this.avaliacoes.clear();
    }
}
