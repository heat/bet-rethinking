package domain.esporte.modalidade;

import domain.esporte.resultado.Resultados;
import org.immutables.value.Value;

import javax.annotation.Nullable;

public interface Modalidade<S> {

    @Value.Parameter
    String getNome();

    @Value.Parameter
    TipoModalidade getTipoModalidade();

    static ModalidadeTempo deTempo(String nome) {
        return ImmutableModalidadeTempo.of(nome, TipoModalidade.TEMPO);
    }

    enum TipoModalidade {
        TEMPO, PONTO
    }

    interface ModalidadeBuilder {

        ModalidadeBuilder nome(String nome);

        Modalidade build();
    }
}
