/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import modelo.CorpoDocente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author renna
 */
public class PersistenciaCorpoDocente {

    private static final String CAMINHO_ARQUIVO = "dados/corpoDocente.dat";

    public static void salvar(CorpoDocente corpoDocente) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {
            oos.writeObject(corpoDocente);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CorpoDocente carregar() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            return new CorpoDocente(); // retorna um novo se não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO))) {
            return (CorpoDocente) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new CorpoDocente(); // fallback
        }
    }
}