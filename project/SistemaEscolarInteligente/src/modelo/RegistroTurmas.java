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

public class RegistroTurmas implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Turma> turmas;

    public RegistroTurmas() {
        this.turmas = new ArrayList<>();
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public List<Turma> listarTurmas() {
        return new ArrayList<>(turmas);
    }

    public List<Turma> buscarPorDisciplina(Disciplina disciplina) {
        return turmas.stream()
                .filter(t -> t.getDisciplina().equals(disciplina))
                .collect(Collectors.toList());
    }

    public Turma buscarPorCodigo(String codigoTurma) {
        return turmas.stream()
                .filter(t -> t.getCodigoTurma().equalsIgnoreCase(codigoTurma))
                .findFirst()
                .orElse(null);
    }

    public void limparTurmas() {
        this.turmas.clear();
    }
}
