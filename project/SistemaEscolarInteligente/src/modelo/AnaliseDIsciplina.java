/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author renna
 */
public class AnaliseDIsciplina extends AnaliseEstatisticaBase implements Interface_AnaliseEstatistica{
    private Disciplina disciplina;

    public AnaliseDIsciplina(Disciplina disciplina, List<Avaliacao> avaliacoes) {
        super(avaliacoes);
        this.disciplina = disciplina;
    }

    @Override
    protected boolean pertence(Avaliacao avaliacao) {
        return avaliacao.getTurma().getDisciplina().equals(disciplina);
    }

    @Override
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Relatório da Disciplina ").append(disciplina.getNome()).append(" ===\n");
        sb.append(String.format("Média Geral: %.2f\n", calcularMedia()));
        sb.append(String.format("Média Nota 1: %.2f\n", calcularMediaNota1()));
        sb.append(String.format("Média Nota 2: %.2f\n", calcularMediaNota2()));
        sb.append(String.format("Frequência Média: %.2f%%\n", calcularFreq()));
        sb.append(String.format("Desvio Padrão: %.2f\n", calcularDesvioPadrao()));
        sb.append(String.format("Taxa de Aprovação: %.2f%%\n", taxaAprovacao()));
        sb.append(String.format("Diferença Melhor-Pior: %.2f\n", diferencaMelhorPior()));

        sb.append("\n-- Melhores Alunos --\n");
        for (Aluno a : encontrarMelhoresAlunos()) sb.append(a.getNome_Aluno()).append("\n");

        sb.append("\n-- Piores Alunos --\n");
        for (Aluno a : encontrarPioresAlunos()) sb.append(a.getNome_Aluno()).append("\n");

        sb.append("\n-- Alunos em Risco --\n");
        for (Aluno a : listarAlunosEmRisco()) sb.append(a.getNome_Aluno()).append("\n");

        sb.append("\n-- Distribuição de Notas --\n");
        for (Map.Entry<String, Integer> e : calcularDistribuicaoNotas().entrySet()) {
            sb.append(e.getKey()).append(": ").append(e.getValue()).append("\n");
        }

        return sb.toString();
    }
}
