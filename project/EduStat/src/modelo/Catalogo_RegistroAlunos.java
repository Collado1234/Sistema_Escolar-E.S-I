/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author renna
 */


public class Catalogo_RegistroAlunos implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Aluno> alunos;

    public Catalogo_RegistroAlunos() {
        this.alunos = new ArrayList<>();
    }

    public boolean adicionarAluno(String nome, String matricula) {
        Aluno a = criarAluno(nome, matricula);
        if(a == null) return false;
        else alunos.add(a); 
        return true;
        
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public Aluno buscarAlunoPorMatricula(String matricula) {
        for (Aluno aluno : alunos) {
            if (aluno.getMatricula().equals(matricula)) {
                return aluno;
            }
        }
        return null;
    }
    
    public Aluno buscarAlunoPorNome(String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.getNome_Aluno().equals(nome)) {
                return aluno;
            }
        }
        return null;
    }
    
    private Aluno criarAluno(String nome, String matricula){
        Aluno a = buscarAlunoPorMatricula(matricula);
        if(a != null){
            return null;
        }else{
            Aluno novoAluno = new Aluno (nome,matricula);         
            return novoAluno;
        }
    }
}
