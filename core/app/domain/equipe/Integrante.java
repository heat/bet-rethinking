package domain.equipe;

import org.immutables.value.Value;

@Value.Immutable
public interface Integrante {

    @Value.Parameter
    public String getNome();
}
