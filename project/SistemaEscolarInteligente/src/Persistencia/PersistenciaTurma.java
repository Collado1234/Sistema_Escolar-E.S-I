/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import java.io.*;
import modelo.Turma;
/**
 *
 * @author renna
 */

public class PersistenciaTurma {

    private static final String CAMINHO_ARQUIVO = "turmas.dat";

    // Salva uma turma (sobrescreve o arquivo)
    public static void salvarTurma(Turma turma) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {
            oos.writeObject(turma);
        }
    }

    // Carrega uma turma do arquivo
    public static Turma carregarTurma() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO))) {
            return (Turma) ois.readObject();
        }
    }
}
