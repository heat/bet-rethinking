package domain.esporte.resultado;

import com.google.common.collect.ImmutableMap;
import domain.esporte.Evento;
import domain.esporte.Participante;

public class ResultadosBuilder <S extends Score> {

    Evento _evento;

    ImmutableMap.Builder<Participante, S> scoresBuilder;
    public ResultadosBuilder(Evento _evento) {
        this._evento = _evento;
        this.scoresBuilder = ImmutableMap.builder();
    }

    public Resultados build(){
        return null;
    }

    public ResultadosBuilder add(Participante participante, S score) {
        if(_evento.isPartcipantePresent(participante))
        {
            scoresBuilder.put(participante, score);
        }
        return this;
    }

    public static <S extends Score> ResultadosBuilder<S> builder(Evento<S> evento) {
        return new ResultadosBuilder<S>(evento);
    }
}
