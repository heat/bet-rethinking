package domain.esporte.modalidade;

import domain.esporte.resultado.Resultados;
import domain.esporte.resultado.TimeScore;
import org.immutables.value.Value;

import javax.annotation.Nullable;

@Value.Style(
        visibility = Value.Style.ImplementationVisibility.PACKAGE
)
@Value.Immutable(
        builder = false
)
abstract class ModalidadeTempo implements Modalidade<TimeScore> {

    public abstract static class Builder implements ModalidadeBuilder {

    }
}
