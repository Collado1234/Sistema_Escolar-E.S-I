/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IU_Sistema;
import java.util.List;
/**
 *
 * @author renna
 */
public class ConversorTabela {
    public static String[][] parseAlunos(List<String> alunos) {
        String[][] dados = new String[alunos.size()][2]; // matrícula, nome
        for (int i = 0; i < alunos.size(); i++) {
            String linha = alunos.get(i)
                .replace("Aluno{", "")
                .replace("}", "")
                .trim();
            String[] partes = linha.split(", ");
            String matricula = partes[0].split("=")[1].replace("'", "");
            String nome = partes[1].split("=")[1].replace("'", "");
            dados[i][0] = matricula;
            dados[i][1] = nome;
        }
        return dados;
    }
    
    public static String[][] parseTurmas(List<String> turmas) {
        String[][] dados = new String[turmas.size()][4];
        for (int i = 0; i < turmas.size(); i++) {
            String linha = turmas.get(i)
                .replace("Turma{", "")
                .replace("}", "")
                .trim();
            String[] partes = linha.split(", ");
            String nome = partes[0].split("=")[1].replace("'", "");
            String codigo = partes[1].split("=")[1].replace("'", "");
            String disciplina = partes[2].split("=")[1];
            String qtdAlunos = partes[3].split("=")[1];
            dados[i][0] = nome;
            dados[i][1] = codigo;
            dados[i][2] = disciplina;
            dados[i][3] = qtdAlunos;
        }
        return dados;
    }
    
    public static String[][] parseDisciplinas(List<String> disciplinas) {
        String[][] dados = new String[disciplinas.size()][3];
        for (int i = 0; i < disciplinas.size(); i++) {
            String linha = disciplinas.get(i)
                .replace("Disciplina{", "")
                .replace("}", "")
                .trim();
            String[] partes = linha.split(", ");
            String codigo = partes[0].split("=")[1].replace("'", "");
            String nome = partes[1].split("=")[1].replace("'", "");
            String carga = partes[2].split("=")[1];
            dados[i][0] = codigo;
            dados[i][1] = nome;
            dados[i][2] = carga;
        }
        return dados;
    }
    
    public static String[][] parseAvaliacoes(List<String> avaliacoes) {
        String[][] dados = new String[avaliacoes.size()][8];
        for (int i = 0; i < avaliacoes.size(); i++) {
            String linha = avaliacoes.get(i)
                .replace("Avaliacao{", "")
                .replace("}", "")
                .trim();
            String[] partes = linha.split(", ");
            String aluno = partes[0].split("=")[1].replace("'", "");
            String turma = partes[1].split("=")[1].replace("'", "");
            String nota1 = partes[2].split("=")[1];
            String nota2 = partes[3].split("=")[1];
            String media = partes[4].split("=")[1];
            String freq = partes[5].split("=")[1];
            String faltas = partes[6].split("=")[1];
            String situacao = partes[7].split("=")[1];
            dados[i][0] = aluno;
            dados[i][1] = turma;
            dados[i][2] = nota1;
            dados[i][3] = nota2;
            dados[i][4] = media;
            dados[i][5] = freq;
            dados[i][6] = faltas;
            dados[i][7] = situacao;
        }
        return dados;
    }
}
