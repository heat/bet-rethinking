package api;

import api.resource.esporte.EventoResource;
import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;
import domain.equipe.Equipe;
import domain.equipe.ImmutableEquipe;
import domain.esporte.Evento;
import domain.esporte.ImmutableEvento;
import domain.esporte.ImmutableParticipante;
import domain.esporte.Participante;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.*;
import org.junit.Test;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;


public class EventoResourceTest {

    final LocalDateTime data = LocalDateTime.now();
    final Participante michaelPhelpsParticipante = ImmutableParticipante.of(ImmutableEquipe.of("Michael Phelps"));
    final Evento evento = ImmutableEvento.builder()
            .modalidade(Modalidade.deTempo("Natação"))
            .dataEvento(data)
            .classificador(mock(Classificador.class))
            .regra(mock(Regra.class))
            .situacao(Evento.Situacao.ABERTO)
            .resultados(mock(Resultados.class))
            .addParticipantes(michaelPhelpsParticipante)
            .build();
    @Test
    public void resourceDeveAprensentarDataEvento() {
        LocalDateTime data = LocalDateTime.now();
        Equipe michaelPhelps = ImmutableEquipe.of("Michel Phelps");
        Participante michaelPhelpsParticipante = ImmutableParticipante.of(michaelPhelps);
        Evento evento = ImmutableEvento.builder()
                .modalidade(Modalidade.deTempo("Natação"))
                .dataEvento(data)
                .classificador(mock(Classificador.class))
                .regra(mock(Regra.class))
                .situacao(Evento.Situacao.ABERTO)
                .resultados(mock(Resultados.class))
                .addParticipantes(michaelPhelpsParticipante)
                .build();

        HalRepresentation resource = EventoResource.of(evento)
                .representationBuilder().build();

        assertThat("O recurso deve ter o campo de dataEvento", resource.getProperties().containsKey("dataEvento"), is(true));

        String result = resource.getProperties().get("dataEvento").toString();

        assertThat("O formato da string deve esta em isso", result, is(data.format(DateTimeFormatter.ISO_DATE_TIME)));
    }

    @Test
    public void atributoModalidade() {

        String expected = "Natação";
        HalRepresentation resource = EventoResource.of(evento)
                .representationBuilder().build();

        assertThat("Deve estar presente a modalidade", resource.getProperties().containsKey("modalidade"), is(true));

        String result = resource.getProperties().get("modalidade").toString();
        assertThat("O valor da modalidade deve ser correto", result, is(expected));
    }

    @Test
    public void DeveEstarComParticipantesEmbedded() {

        HalRepresentation resource = EventoResource.of(evento)
                .representationBuilder().build();

        Boolean resultHasAttribute  = resource.getMultiEmbedded().containsKey("participantes");

        assertThat("Deve estar presente os participantes", resultHasAttribute, is(true));

        List<HalResource> participantesEmbedded = resource.getMultiEmbedded().get("participantes");

        long resultTotalParticipantes = participantesEmbedded.size();
        assertThat("tem um resource para participante", resultTotalParticipantes, is(1L));

        URI expectedHalLocation = URI.create("participantes");

        HalResource participanteRepresentation = participantesEmbedded.get(0);

        URI resultHalLocation = participanteRepresentation.location();

        assertThat("a uri principal do participante deve ser participantes",
                resultHalLocation, is(expectedHalLocation));
    }
}
