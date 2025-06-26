/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IU_Sistema;

/**
 *
 * @author renna
 */
public class Main {   
    public static void main(String[] args) {
//        NewIU sistema = new NewIU();
//        sistema.executar();
        
        InterfaceGrafica principal = new InterfaceGrafica();
        principal.setVisible(true);
        principal.setTitle("Sistema de Avaliação de Desempenho");
        principal.toFront();
    }
}