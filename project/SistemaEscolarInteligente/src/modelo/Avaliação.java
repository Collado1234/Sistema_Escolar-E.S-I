/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renna
 */
public class Avaliação {
    private Aluno aluno;
    private Turma turma;
    private int prova1;
    private int prova2;
    private int total_faltas;

    public Avaliação(Aluno aluno, Turma turma) {
        this.aluno = aluno;
        this.turma = turma;
    }

    public int getProva1() {
        return prova1;
    }

    public void setProva1(int prova1) {
        this.prova1 = prova1;
    }

    public int getProva2() {
        return prova2;
    }

    public void setProva2(int prova2) {
        this.prova2 = prova2;
    }

    public int getTotal_faltas() {
        return total_faltas;
    }

    public void setTotal_faltas(int total_faltas) {
        this.total_faltas = total_faltas;
    }
    
    
}
