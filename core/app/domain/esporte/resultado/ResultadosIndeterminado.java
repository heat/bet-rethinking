package domain.esporte.resultado;

import java.util.Optional;

public class ResultadosIndeterminado implements Resultados {

    @Override
    public Situacao getSituacao() {
        return Situacao.INDETERMINADO;
    }

    @Override
    public Optional<Classificacao> getVencedor() {
        return Optional.empty();
    }
}
