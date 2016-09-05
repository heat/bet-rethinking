package domain.esporte;

import domain.equipe.Equipe;
import domain.equipe.ImmutableEquipe;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.*;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class EventoTest {

    @Test
    public void EventoDeveSerCriadoAberto() {
        Modalidade<TimeScore> natacao = mock(Modalidade.class);

        Evento evento = ImmutableEvento.builder()
                .situacao(Evento.Situacao.ABERTO)
                .modalidade(natacao)
                .dataEvento(LocalDateTime.now())
                .build();

        Evento.Situacao expected = Evento.Situacao.ABERTO;
        assertThat("Evento deve ser criado em aberto", evento.getSituacao(), is(expected));
    }

    @Test
    public void EventoNaoDeveRepetirParticipante() {

        ImmutableParticipante p = ImmutableParticipante.of(
                ImmutableEquipe.of("Equipe campea"));
        Modalidade m = mock(Modalidade.class);

        ImmutableEvento<Score> evento = ImmutableEvento.<Score>builder()
                .situacao(Evento.Situacao.ABERTO)
                .modalidade(m)
                .dataEvento(LocalDateTime.now())
                .addParticipantes(p)
                .build();

        assertThat("Participante esta no evento", evento.isPartcipantePresent(p), is(true));

        p = ImmutableParticipante.builder().from(p)
                .resultado(TimeScore.with(Duration.ofMillis(123123)))
                .build();
        // participante com ou sem resultado é somente um participante
        evento = evento.withParticipantes(p);

        assertThat("Participante ainda está presente", evento.isPartcipantePresent(p), is(true));
        assertThat("Existe somente um participante" , evento.getParticipantes().size(), is(1));
    }

    @Test(expected = IllegalStateException.class)
    public void NaoPermitirFinalizarSemResultado() {
        Participante p = mock(Participante.class);
        Modalidade m = mock(Modalidade.class);
        Optional<Score> score = Optional.empty();
        when(p.getResultado()).thenReturn(score);
        ImmutableEvento<Score> evento = ImmutableEvento.<Score>builder()
                .modalidade(m)
                .dataEvento(LocalDateTime.now())
                .addParticipantes(p)
                .build();
        evento.finalizarEvento();
    }

    @Test
    public void EventoDeveSerFinalizado() {
        Modalidade natacao = Modalidade.deTempo("Natação");

        Participante michaelPhelps = ImmutableParticipante.of(mock(Equipe.class));
        Participante cesarCielo = ImmutableParticipante.of(mock(Equipe.class));
        Participante gustavoBorges = ImmutableParticipante.of(mock(Equipe.class));

        Evento<TimeScore> evento = ImmutableEvento.<TimeScore>builder()
                .modalidade(natacao)
                .situacao(Evento.Situacao.ABERTO)
                .dataEvento(LocalDateTime.now())
                .addParticipantes(michaelPhelps)
                .addParticipantes(cesarCielo)
                .addParticipantes(gustavoBorges)
                .build();
        evento = evento
                .withResult(michaelPhelps, TimeScore.with(Duration.ofMillis(12321)))
                .withResult(cesarCielo, TimeScore.with(Duration.ofMillis(13211)))
                .withResult(gustavoBorges, TimeScore.with(Duration.ofMillis(11441)))
        .finalizarEvento();

        Evento.Situacao expected = Evento.Situacao.FINALIZADO;

        Evento.Situacao result = evento.getSituacao();
        assertThat("Evento deve esta finalizado após cadastrar resultado", expected, is(result) );
    }
}
