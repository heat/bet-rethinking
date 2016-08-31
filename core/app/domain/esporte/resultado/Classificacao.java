package domain.esporte.resultado;

import domain.equipe.Equipe;
import domain.esporte.Participante;
import org.immutables.value.Value;

public class Classificacao<T> implements Comparable<Classificacao> {

    final Participante _participante;

    final Long _posicao;

    private Classificacao(Participante participante, Long posicao) {
        this._participante = participante;
        this._posicao = posicao;
    }

    @Value.Parameter
    public Long getPosicao() {
        return _posicao;
    }

    @Value.Parameter
    public Equipe getEquipe() {
        return _participante.getEquipe();
    }

    @Value.Parameter
    public Score getResultado() {
        return _participante
                .getResultado().get();
    }

    @Override
    public int compareTo(Classificacao o) {
        return this.getResultado().compareTo(o.getResultado());
    }


    public static <V> Classificacao<V> of(Participante participante, Long posicao){
        return new Classificacao<V>(participante, posicao);
    }
}
