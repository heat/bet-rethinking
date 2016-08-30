package domain.equipe;

import com.google.common.collect.ImmutableList;
import org.immutables.value.Value;

@Value.Immutable
public abstract class Equipe {

    @Value.Parameter
    public abstract String getNome();

    public abstract ImmutableList<Integrante> getIntegrantes();

    public Equipe incluir(Integrante integrante) {
        return ImmutableEquipe.copyOf(this)
                .withIntegrantes(integrante);
    }
}
