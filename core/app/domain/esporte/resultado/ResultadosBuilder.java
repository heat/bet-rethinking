package domain.esporte.resultado;

import com.google.common.collect.ImmutableSet;
import domain.esporte.Participante;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ResultadosBuilder{

    private final Classificador _classificador;
    private Regra _regra;
    private ImmutableSet<Participante> _participantes;

    public ResultadosBuilder(Classificador classificador) {
        this._participantes = ImmutableSet.of();
        this._classificador = classificador;
    }

    public ResultadosBuilder with(Set<Participante> participantes) {
        return  with(participantes.toArray(new Participante[]{}));
    }

    public ResultadosBuilder with(Participante... participantes) {
        this._participantes = ImmutableSet.<Participante>builder()
                .addAll(this._participantes)
                .add(participantes)
                .build();
        return this;
    }

    public ResultadosBuilder with(Regra regra) {
        this._regra = regra;
        return this;
    }

    public Resultados build() {
        if(_regra == null || _participantes == null || _participantes.isEmpty())
            throw new IllegalStateException("Sem os parametros necessarios");

        List<Classificacao> classificacao = _classificador.classificar(_participantes);
        Resultados.Situacao situacao = _regra.apply(classificacao);

        switch (situacao) {

            case ABERTO:
                break;
            case DETERMINADO:
                return new ResultadosFinal(classificacao);
            case EMPATADDO:
                return new ResultadosEmpate();
            case INDETERMINADO:
                return new ResultadosIndeterminado();
        }
        return null;
    }
}
