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

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

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
    
    @Override
    public String toString() {
        return "Aluno{" +
               "matrícula='" + matricula + '\'' +
               ", nome='" + nome_Aluno + '\'' +
               '}';
}

}
