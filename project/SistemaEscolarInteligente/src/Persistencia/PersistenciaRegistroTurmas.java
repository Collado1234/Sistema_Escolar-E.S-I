package Persistencia;

import modelo.RegistroTurmas;

import java.io.*;

public class PersistenciaRegistroTurmas {

    private static final String ARQUIVO = "registro_turmas.dat";

    public static void salvar(RegistroTurmas registro) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RegistroTurmas carregar() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (RegistroTurmas) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new RegistroTurmas(); // Se não conseguir carregar, retorna novo
        }
    }
}
