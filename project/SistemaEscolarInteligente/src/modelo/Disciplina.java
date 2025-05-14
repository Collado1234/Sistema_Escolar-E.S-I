/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
/**
 *  Classe que define disciplina
 * @author renna
 */
public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private String codigo;
    private int carga_horaria;

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }
    
    public Disciplina(String nome, String codigo, int carga_horaria) {
        this.nome = nome;
        this.codigo = codigo;
        this.carga_horaria = carga_horaria;                           
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCarga_horaria() {
        return carga_horaria;
    }

    public void setCarga_horaria(int carga_horaria) {
        this.carga_horaria = carga_horaria;
    }
    
    
    
}

