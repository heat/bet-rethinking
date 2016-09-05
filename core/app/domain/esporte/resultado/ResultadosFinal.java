package domain.esporte.resultado;

import java.util.List;
import java.util.Optional;

public class ResultadosFinal implements Resultados {

    final List<Classificacao> classificacaos;

    public ResultadosFinal(List<Classificacao> classificacaos) {
        this.classificacaos = classificacaos;
    }

    @Override
    public Situacao getSituacao() {
        return Situacao.DETERMINADO;
    }

    @Override
    public Optional<Classificacao> getVencedor() {
        return
                classificacaos.stream()
                        .filter( c -> c.getPosicao() == Classificacao.PRIMEIRO)
                        .findFirst();
    }
}
