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

    private static final String DIRETORIO = "dados";
    private static final String NOME_ARQUIVO = "corpoDocente.dat";
    private static final String CAMINHO_ARQUIVO = DIRETORIO + File.separator + NOME_ARQUIVO;

    public static void salvar(CorpoDocente corpoDocente) {
        try {
            File dir = new File(DIRETORIO);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File arquivo = new File(CAMINHO_ARQUIVO);
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
                oos.writeObject(corpoDocente);
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar CorpoDocente:");
            e.printStackTrace();
        }
    }

    public static CorpoDocente carregar() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists() || arquivo.length() == 0) {
            try {
                File dir = new File(DIRETORIO);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo vazio:");
                e.printStackTrace();
            }

            return new CorpoDocente();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (CorpoDocente) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar CorpoDocente:");
            e.printStackTrace();
            return new CorpoDocente();
        }
    }
}
