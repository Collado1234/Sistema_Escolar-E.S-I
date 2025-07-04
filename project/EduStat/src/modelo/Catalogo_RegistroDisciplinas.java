/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
//import java.util.stream.Collectors;
/**
 *
 * @author renna
 */
public class Catalogo_RegistroDisciplinas implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Disciplina> disciplinas;

    public Catalogo_RegistroDisciplinas() {
        this.disciplinas = new ArrayList<>();
    }

    public boolean adicionarDisciplina(String nome, String codigo, int carga_horaria) {
        Disciplina disc = criarDisciplina(nome, codigo, carga_horaria);
        try{
            if(disc == null){
                throw new IllegalArgumentException("Disciplina já existe no registro");
            }     
            this.disciplinas.add(disc);
            return true;
        }catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar disciplina: " + e.getMessage());
            return false;
        }                    
    }

    public List<Disciplina> listarDisciplinas() {
        return new ArrayList<>(disciplinas);
    }

    public Disciplina buscarPorCodigo(String codigo) {
        return disciplinas.stream()
                .filter(d -> d.getCodigo().equalsIgnoreCase(codigo))
                .findFirst()
                .orElse(null);
    }

    public void limparDisciplinas() {
        this.disciplinas.clear();
    }
    
    private Disciplina criarDisciplina(String nome, String codigo, int carga_horaria){
        if(buscarPorCodigo(codigo)!=null) return null;
        
        return new Disciplina(nome, codigo);                 
    }
}

