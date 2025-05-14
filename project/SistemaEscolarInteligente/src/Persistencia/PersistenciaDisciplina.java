/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.List;
import modelo.Disciplina;

/**
 *
 * @author renna
 */
public class PersistenciaDisciplina{
    private static final String ARQUIVO = "disciplinas.dat";
    
    public void salvarDisciplinas(List<Disciplina> disciplinas){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))){
            oos.writeObject(disciplinas);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public List<Disciplina> carregarDisciplinas(){
        File arquivo = new File(ARQUIVO);
        if(!arquivo.exists()) return new ArrayList<>();
        
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))){
           return(List<Disciplina>) ois.readObject();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}