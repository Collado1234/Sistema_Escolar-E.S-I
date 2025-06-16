/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author renna
 */
public abstract class AnaliseEstatisticaBase {
    protected List<Avaliacao> avaliacoes;

    public AnaliseEstatisticaBase(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    protected abstract boolean pertence(Avaliacao avaliacao);

    protected List<Avaliacao> filtrarAvaliacoes() {
        List<Avaliacao> filtradas = new ArrayList<>();
        for (Avaliacao avaliacao : avaliacoes) {
            if (pertence(avaliacao)) {
                filtradas.add(avaliacao);
            }
        }
        return filtradas;
    }

    protected float calcularMedia() {
        float soma = 0f;
        List<Avaliacao> lista = filtrarAvaliacoes();
        for (Avaliacao a : lista) soma += a.getMedia();
        return lista.isEmpty() ? 0f : soma / lista.size();
    }

    protected float calcularFreq() {
        float soma = 0f;
        List<Avaliacao> lista = filtrarAvaliacoes();
        for (Avaliacao a : lista) soma += a.calcFrequencia();
        return lista.isEmpty() ? 0f : soma / lista.size();
    }

    protected float calcularDesvioPadrao() {
        float media = calcularMedia();
        List<Avaliacao> lista = filtrarAvaliacoes();
        float somaQuadrados = 0f;
        for (Avaliacao a : lista) {
            float diff = a.getMedia() - media;
            somaQuadrados += diff * diff;
        }
        return lista.isEmpty() ? 0f : (float) Math.sqrt(somaQuadrados / lista.size());
    }

    protected float taxaAprovacao() {
        List<Avaliacao> lista = filtrarAvaliacoes();
        int aprovados = 0;
        for (Avaliacao a : lista) {
            if (a.estaAprovado()) aprovados++;
        }
        return lista.isEmpty() ? 0f : ((float) aprovados / lista.size()) * 100;
    }

    protected float calcularMediaNota1() {
        float soma = 0f;
        List<Avaliacao> lista = filtrarAvaliacoes();
        for (Avaliacao a : lista) soma += a.getProva1();
        return lista.isEmpty() ? 0f : soma / lista.size();
    }

    protected float calcularMediaNota2() {
        float soma = 0f;
        List<Avaliacao> lista = filtrarAvaliacoes();
        for (Avaliacao a : lista) soma += a.getProva2();
        return lista.isEmpty() ? 0f : soma / lista.size();
    }

    protected List<Aluno> encontrarMelhoresAlunos() {
        List<Aluno> melhores = new ArrayList<>();
        float maiorMedia = Float.MIN_VALUE;

        for (Avaliacao a : filtrarAvaliacoes()) {
            float media = a.getMedia();
            if (media > maiorMedia) {
                melhores.clear();
                melhores.add(a.getAluno());
                maiorMedia = media;
            } else if (media == maiorMedia) {
                melhores.add(a.getAluno());
            }
        }

        return melhores;
    }

    protected List<Aluno> encontrarPioresAlunos() {
        List<Aluno> piores = new ArrayList<>();
        float menorMedia = Float.MAX_VALUE;

        for (Avaliacao a : filtrarAvaliacoes()) {
            float media = a.getMedia();
            if (media < menorMedia) {
                piores.clear();
                piores.add(a.getAluno());
                menorMedia = media;
            } else if (media == menorMedia) {
                piores.add(a.getAluno());
            }
        }

        return piores;
    }

    protected List<Aluno> listarAlunosEmRisco() {
        List<Aluno> emRisco = new ArrayList<>();
        for (Avaliacao a : filtrarAvaliacoes()) {
            if (!a.estaAprovado() && !emRisco.contains(a.getAluno())) {
                emRisco.add(a.getAluno());
            }
        }
        return emRisco;
    }

    protected Map<String, Integer> calcularDistribuicaoNotas() {
        Map<String, Integer> distribuicao = new LinkedHashMap<>();
        distribuicao.put("0–2", 0);
        distribuicao.put("2–4", 0);
        distribuicao.put("4–6", 0);
        distribuicao.put("6–8", 0);
        distribuicao.put("8–10", 0);

        for (Avaliacao a : filtrarAvaliacoes()) {
            float nota = a.getMedia();
            if (nota < 2) distribuicao.put("0–2", distribuicao.get("0–2") + 1);
            else if (nota < 4) distribuicao.put("2–4", distribuicao.get("2–4") + 1);
            else if (nota < 6) distribuicao.put("4–6", distribuicao.get("4–6") + 1);
            else if (nota < 8) distribuicao.put("6–8", distribuicao.get("6–8") + 1);
            else distribuicao.put("8–10", distribuicao.get("8–10") + 1);
        }

        return distribuicao;
    }

    protected float diferencaMelhorPior() {
        float melhor = Float.MIN_VALUE;
        float pior = Float.MAX_VALUE;

        for (Avaliacao a : filtrarAvaliacoes()) {
            float media = a.getMedia();
            if (media > melhor) melhor = media;
            if (media < pior) pior = media;
        }

        return (melhor == Float.MIN_VALUE || pior == Float.MAX_VALUE) ? 0f : melhor - pior;
    }

    public abstract String gerarRelatorio();
}
