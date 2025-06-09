/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
    @author rennan
*/

/**
 * Representa uma turma que contém uma lista de alunos e está associada a uma disciplina.
 * Pode ser persistida pois implementa Serializable.
 */
public class Turma implements Serializable {
    private static final long serialVersionUID = 1L; //garante integridade entre versões de salvamento

    private String nomeTurma;
    private String codigoTurma;

    private List<Aluno> alunos;
    private Disciplina disciplina;

    public Turma(String nomeTurma, String codigoTurma, Disciplina disciplina) {
        this.nomeTurma = nomeTurma;
        this.codigoTurma = codigoTurma;
        this.disciplina = disciplina;
        this.alunos = new ArrayList<>();
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    public Aluno busca_Aluno_na_Turma(String matricula){
        for(Aluno aluno : alunos){
            if(aluno.getMatricula().equals(matricula)){
                return aluno;
            }
        }
        return null;       
    }
        

    @Override
    public String toString() {
        return "Turma{" +
                "nomeTurma='" + nomeTurma + '\'' +
                ", codigoTurma='" + codigoTurma + '\'' +
                ", disciplina=" + (disciplina != null ? disciplina.getNome(): "N/A") +
                ", quantidadeAlunos=" + alunos.size() +
                '}';
    }
}
