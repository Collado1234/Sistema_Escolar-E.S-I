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

public class Catalogo_RegistroTurmas implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Turma> turmas;

    public Catalogo_RegistroTurmas() {
        this.turmas = new ArrayList<>();
    }

    public void adicionarTurma(Turma turma) {
        this.turmas.add(turma);
    }

    public List<Turma> listarTurmas() {
        return new ArrayList<>(turmas);
    }

    public List<Turma> buscarPorDisciplina(String codigoDisciplina) {
        List<Turma> lista_turmas = new ArrayList<>();
        for(Turma turma:turmas){
            if(turma.getDisciplina().getCodigo().equals(codigoDisciplina)){
                lista_turmas.add(turma);
            }
        }
        return lista_turmas;
    }
    
    public List<Turma> lista_Turmas_Aluno_Matriculado(String matricula){
        List<Turma> lista_turmas = new ArrayList<>();
        for(Turma turma:turmas){
            if(turma.busca_Aluno_na_Turma(matricula)!=null){
                lista_turmas.add(turma);
            }
        }
        return lista_turmas;
    }

    public Turma buscarPorCodigo(String codigoTurma) {
        return turmas.stream()
                .filter(t -> t.getCodigoTurma().equalsIgnoreCase(codigoTurma))
                .findFirst()
                .orElse(null);
    }

    public void limparTurmas() {
        this.turmas.clear();
    }
}
