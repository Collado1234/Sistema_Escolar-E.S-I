/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *  Classe que define disciplina
 * @author renna
 */
public class Disciplina {
    private String nome_disciplina;
    private String COD_disciplina;
    private int carga_horaria;

    public Disciplina(String nome_disciplina, String COD_disciplina, int carga_horaria) {
        this.nome_disciplina = nome_disciplina;
        this.COD_disciplina = COD_disciplina;
        this.carga_horaria = carga_horaria;
    }    

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public String getCOD_disciplina() {
        return COD_disciplina;
    }

//    public void setCOD_disciplina(String COD_disciplina) {;;
//        this.COD_disciplina = COD_disciplina;
//    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    
    
}
