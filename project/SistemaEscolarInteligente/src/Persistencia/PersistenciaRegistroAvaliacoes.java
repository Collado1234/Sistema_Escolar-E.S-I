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

    private static final String CAMINHO_ARQUIVO = "registro_avaliacoes.dat";

    public static void salvarRegistro(RegistroAvaliacoes registro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CAMINHO_ARQUIVO))) {
            oos.writeObject(registro);
            System.out.println("Registro de avaliações salvo com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao salvar o registro de avaliações: " + e.getMessage());
        }
    }

    public static RegistroAvaliacoes carregarRegistro() {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            return new RegistroAvaliacoes(); // retorna novo se não existir
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CAMINHO_ARQUIVO))) {
            return (RegistroAvaliacoes) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o registro de avaliações: " + e.getMessage());
            return new RegistroAvaliacoes(); // retorna novo se falhar
        }
    }
}

