package Persistencia;

import modelo.RegistroDisciplinas;

import java.io.*;

/**
 *
 * @author renna
 */
public class PersistenciaRegistroDisciplinas {

    private static final String ARQUIVO = "registro_disciplinas.dat";

    public static void salvar(RegistroDisciplinas registro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RegistroDisciplinas carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (RegistroDisciplinas) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new RegistroDisciplinas(); // Se não conseguir carregar, retorna novo
        }
    }
}
