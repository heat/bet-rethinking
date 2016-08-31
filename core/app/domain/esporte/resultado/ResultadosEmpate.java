package domain.esporte.resultado;

import java.util.Optional;

public class ResultadosEmpate extends Resultados {

    @Override
    public Situacao getSituacao() {
        return Situacao.EMPATADDO;
    }

    @Override
    public Optional<Classificacao> getVencedor() {
        return Optional.empty();
    }
}
