package domain.esporte.resultado;

import com.google.common.collect.Ordering;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Resultados {

    private final List<Classificacao> _classificados;

    public Resultados(List<Classificacao> _classificados) {
        this._classificados = Ordering.natural().sortedCopy(_classificados);
    }

    public Resultados(Classificacao... classificados) {
        this(Arrays.asList(classificados));
    }

    public Classificacao getVencedor() {
        return _classificados.get(0);
    }

    @Override
    public String toString() {
        return "Resultados{" +
                _classificados.stream().map( c -> c.getEquipe().getNome() + ": " + c.getResultado().result() + "\n")
                        .reduce(String::concat) +
                '}';
    }
}
