package domain.esporte.resultado;

import com.google.common.collect.ImmutableSet;
import domain.esporte.Participante;

public class ResultadosBuilder{

    private final Classificador _classificador;
    private Regra _regra;
    private ImmutableSet<Participante> _participantes;

    public ResultadosBuilder(Classificador classificador) {
        this._participantes = ImmutableSet.of();
        this._classificador = classificador;
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

        Resultados.Situacao situacao = _regra.apply(_classificador.classificar(_participantes));

        switch (situacao) {

            case ABERTO:
                break;
            case DETERMINADO:
                break;
            case EMPATADDO:
                return new ResultadosEmpate();
            case INDETERMINADO:
                break;
        }
        return null;
    }
}
