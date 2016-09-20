package domain.esporte;

import com.google.common.collect.ImmutableSet;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.*;
import org.immutables.value.Value;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static org.reflections.util.ConfigurationBuilder.build;

@Value.Immutable
public abstract class Evento<S extends Score> {

    private Classificador classificador;
    private Regra regra;

    public abstract Modalidade getModalidade();

    public abstract LocalDateTime getDataEvento();

    public abstract ImmutableSet<Participante> getParticipantes();

    public abstract Situacao getSituacao();

    public abstract Resultados getResultados();

    public abstract Classificador getClassificador();

    public abstract Regra getRegra();

    public boolean isPartcipantePresent(Participante p) {
        return getParticipantes().contains(p);
    }

    public boolean isAberto() {
        return this.getSituacao() == Situacao.ABERTO;
    }

    public String getNomeModalide() {
        return getModalidade().getNome();
    }

    /**
     * Finaliza o evento calculando o resultado
     *
     * @return
     * @throws IllegalStateException
     */
    public Evento finalizarEvento() throws IllegalStateException {
        ImmutableEvento.Builder<S> builder = ImmutableEvento.<S>builder().from(this);
        if (!this.isAberto())
            // Verifica se o estado permite ser fianlizado
            throw new IllegalStateException("Somente jogos abertos podem ser finalizados");
        if (getParticipantes().stream().anyMatch(p -> !p.getResultado().isPresent()))
            // Verifica se todos estao com resultados
            throw new IllegalStateException("Existem participantes sem resultados");

        Resultados.of(this.getClassificador())
                .with(this.getRegra())
                .with(this.getParticipantes())
                .build();
        return builder.from(this)
                .situacao(Situacao.FINALIZADO)
                .build();
    }

    /**
     * Adiciona a pontuacao de um participante ao evento
     * @param participante
     * @param score
     * @return this
     */
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
