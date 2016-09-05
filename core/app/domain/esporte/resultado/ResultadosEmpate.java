package domain.esporte.resultado;

import java.util.List;
import java.util.Optional;

public class ResultadosEmpate implements Resultados {

    protected ResultadosEmpate() {
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.EMPATADDO;
    }

    @Override
    public Optional<Classificacao> getVencedor() {
        return Optional.empty();
    }
}
