package domain.esporte;

import com.google.common.collect.ImmutableSet;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.Resultados;
import domain.esporte.resultado.ResultadosBuilder;
import domain.esporte.resultado.Score;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.reflections.util.ConfigurationBuilder.build;

@Value.Immutable
public abstract class Evento<S extends Score> {

    ResultadosBuilder<S> builder = ResultadosBuilder.<S>builder(this);

    public abstract Modalidade getModalidade();

    public abstract LocalDateTime getDataEvento();

    public abstract ImmutableSet<Participante> getParticipantes();


    public abstract Situacao getSituacao();

    public boolean isPartcipantePresent(Participante p) {
        return getParticipantes().contains(p);
    }

    public boolean isAberto() {
        return this.getSituacao() == Situacao.ABERTO;
    }

    public Evento finalizarEvento() throws IllegalStateException {
        ImmutableEvento.Builder<S> builder = ImmutableEvento.builder();
        if(!this.isAberto())
            // Verifica se o estado permite ser fianlizado
            throw new IllegalStateException("Somente jogos abertos podem ser finalizados");
        if(getParticipantes().stream().anyMatch( p -> !p.getResultado().isPresent()))
            // Verifica se todos estao com resultados
            throw new IllegalStateException("Existem participantes sem resultados");

        return builder.from(this)
                .situacao(Situacao.FINALIZADO)
                .build();
    }

    public Evento withResult(Participante participante, S score) {
        Participante participanteScore = ImmutableParticipante.copyOf(participante).withResultado(score);
        Collection<Participante> participantes = getParticipantes().stream()
                .map(p -> p.equals(participante)? participanteScore : p).collect(Collectors.toList());
        return ImmutableEvento.<S>builder().from(this)
                .participantes(participantes)
                .build();
    }

    public enum Situacao {
        ABERTO,
        ADIADO,
        CANCELADO,
        FINALIZADO,
    }
}
