package domain.esporte.resultado;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A regra para definicao de resultados determinados
 * e quando se preve um empate e nesse caso nao a vitorioso
 * ou ha somente um vitorioso
 */
public class RegraDeterminado implements Regra {

    @Override
    public Resultados.Situacao apply(List<Classificacao> classificacoes) {
        Resultados.Situacao result = Resultados.Situacao.ABERTO;

        Stream<Classificacao> classificacaoStream = classificacoes.stream();

        long total = classificacaoStream.filter( c -> c.getPosicao() == 1L).count();
        if(total > 1) {
            result = Resultados.Situacao.EMPATADDO;
        } else {
            result = Resultados.Situacao.DETERMINADO;
        }
        return Resultados.Situacao.ABERTO;
    }
}
