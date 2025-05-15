/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;
import modelo.RegistroAvaliacoes;
import java.io.*;

/**
 *
 * @author renna
 */
public class PersistenciaRegistroAvaliacoes {

    private static final String DIRETORIO = "dados";
    private static final String NOME_ARQUIVO = "registro_avaliacoes.dat";
    private static final String CAMINHO_ARQUIVO = DIRETORIO + File.separator + NOME_ARQUIVO;

    public static void salvar(RegistroAvaliacoes registro) {
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
                oos.writeObject(registro);
                System.out.println("Registro de avaliações salvo com sucesso.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar o registro de avaliações: " + e.getMessage());
        }
    }

    public static RegistroAvaliacoes carregar() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists() || arquivo.length() == 0) {
            try {
                File dir = new File(DIRETORIO);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                arquivo.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo vazio: " + e.getMessage());
            }

            return new RegistroAvaliacoes();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (RegistroAvaliacoes) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o registro de avaliações: " + e.getMessage());
            return new RegistroAvaliacoes(); // fallback
        }
    }
}


