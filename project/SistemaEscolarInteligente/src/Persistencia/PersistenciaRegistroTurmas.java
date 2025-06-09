package Persistencia;

import modelo.Catalogo_RegistroTurmas;

import java.io.*;

public class PersistenciaRegistroTurmas {

    private static final String DIRETORIO = "dados";
    private static final String NOME_ARQUIVO = "registro_turmas.dat";
    private static final String CAMINHO_ARQUIVO = DIRETORIO + File.separator + NOME_ARQUIVO;

    public static void salvar(Catalogo_RegistroTurmas registro) {
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
                System.out.println("Registro de turmas salvo com sucesso.");
            }

        } catch (IOException e) {
            System.err.println("Erro ao salvar o registro de turmas: " + e.getMessage());
        }
    }

    public static Catalogo_RegistroTurmas carregar() {
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

            return new Catalogo_RegistroTurmas();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (Catalogo_RegistroTurmas) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o registro de turmas: " + e.getMessage());
            return new Catalogo_RegistroTurmas();
        }
    }
}

