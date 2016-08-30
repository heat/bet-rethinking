package domain.esporte.resultado;

import org.immutables.value.Value;

public interface Score <T> extends Comparable {

    @Value.Parameter
    T result();
}
