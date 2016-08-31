package domain.esporte;

import domain.equipe.Equipe;
import domain.esporte.resultado.Score;
import org.immutables.value.Value;

import java.util.Optional;

@Value.Immutable
public interface Participante {

    @Value.Parameter
    Equipe getEquipe();

    @Value.Auxiliary
    abstract Optional<Score> getResultado();
}
