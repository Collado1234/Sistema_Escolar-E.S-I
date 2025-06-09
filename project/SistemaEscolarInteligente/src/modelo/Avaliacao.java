/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
/**
 *
 * @author renna
 */
public class Avaliacao implements Serializable{
    private Aluno aluno;
    private Turma turma;
    private float prova1;
    private float prova2;
    private int total_faltas;

    public Avaliacao(Aluno aluno, Turma turma,float nota1,float nota2,int total_faltas) {
        this.aluno = aluno;
        this.turma = turma;
        this.prova1 = nota1;
        this.prova2 = nota2;
        this.total_faltas = total_faltas;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public Turma getTurma() {
        return turma;
    }
    
    public float calcFrequencia(){
        float freq = 1 - (total_faltas/ turma.getDisciplina().getCarga_horaria());
        freq = freq*100;
        return freq;
    }

    public float getProva1() {
        return prova1;
    }
    
    public float getProva2() {
        return prova2;
    }

    public void setProva1(float prova1) {
        this.prova1 = prova1;
    }

    
    public void setProva2(float prova2) {
        this.prova2 = prova2;
    }

    public int getTotal_faltas() {
        return total_faltas;
    }

    public void setTotal_faltas(int total_faltas) {
        this.total_faltas = total_faltas;
    }
    
    
}
