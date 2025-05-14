/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renna
 */
public class Aluno {
    private String nome_Aluno;
    private String matricula;

    public Aluno(String nome_Aluno, String matricula) {
        this.nome_Aluno = nome_Aluno;
        this.matricula = matricula;
    }

    public String getNome_Aluno() {
        return nome_Aluno;
    }

    public void setNome_Aluno(String nome_Aluno) {
        this.nome_Aluno = nome_Aluno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    
}
