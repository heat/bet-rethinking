package domain.esporte.resultado;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Resultados {


     Situacao getSituacao();

    Optional<Classificacao> getVencedor();

    static ResultadosBuilder of(Classificador classificador) {
        return new ResultadosBuilder(classificador);
    }

    enum Situacao {
        /**
         * Quando um resultado esta aberto nao ha definicao do resultado ainda
         */
        ABERTO,
        /**
         * Um resutlado determinado e quando se tem uma equipe vitoriosa
         */
        DETERMINADO,
        /**
         * O empate e um estado determinado que define que nenhum participante foi vitorioso
         */
        EMPATADDO,
        /**
         * o estado indetermiado e quando um resultado nao preve um empate(determinado) e a mais de um
         * vitorioso
         */
        INDETERMINADO
    }
}
