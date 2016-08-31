package domain.esporte.resultado;

import org.immutables.value.Value;

public interface Score <T> extends Comparable<Score> {

    @Value.Parameter
    T result();
}
