/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author renna
 */
public class Constantes {
    private float nota__de_corte = 5f;
    private float frequencia_minima = 75f;    
    
    private static final Constantes instancia = new Constantes();
    
    private Constantes() {}

    public float getNota__de_corte() {
        return nota__de_corte;
    }

    public void setNota__de_corte(float nota__de_corte) {
        this.nota__de_corte = nota__de_corte;
    }

    public float getFrequencia_minima() {
        return frequencia_minima;
    }

    public void setFrequencia_minima(float frequencia_minima) {
        this.frequencia_minima = frequencia_minima;
    }
    
    public static Constantes getInstancia() {
        return instancia;
    }
                
}
