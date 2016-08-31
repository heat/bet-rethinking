package domain.esporte.resultado;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Resultados {

    private final List<Classificacao> _classificados;

    private Situacao situacao;
    // TODO deixar os construtores privados
    public Resultados(List<Classificacao> _classificados) {
        this.situacao = Situacao.ABERTO;
        this._classificados = Ordering.natural().sortedCopy(_classificados);
    }

    public Resultados(Classificacao... classificados) {
        this(Arrays.asList(classificados));
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public Optional<Classificacao> getVencedor() {
        return Optional.of(_classificados.get(0));
    }

    @Override
    public String toString() {
        return "Resultados{" +
                _classificados.stream().map( c -> c.getEquipe().getNome() + ": " + c.getResultado().result() + "\n")
                        .reduce(String::concat) +
                '}';
    }

    public static ResultadosBuilder of(Classificador classificador) {
        return new ResultadosBuilder(classificador);
    }

    public enum Situacao {
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
