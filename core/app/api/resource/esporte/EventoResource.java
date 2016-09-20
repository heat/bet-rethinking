package api.resource.esporte;

import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;
import domain.esporte.Evento;
import domain.esporte.Participante;

import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class EventoResource implements HalResource {

    Evento _evento;

    private EventoResource(Evento _evento) {
        this._evento = _evento;
    }


    @Override
    public URI location() {
        return URI.create("eventos");
    }

    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        List<Participante> participantes = _evento.getParticipantes()
                .asList();

        List<ParticipanteResource> participanteResources = participantes.stream()
                .map(p -> ParticipanteResource.of((Participante)p))
                .collect(Collectors.toList());

        HalRepresentation.HalRepresentationBuilder builder = HalRepresentation.builder()
                .addProperty("dataEvento", this._evento.getDataEvento().format(DateTimeFormatter.ISO_DATE_TIME))
                .addProperty("modalidade", this._evento.getNomeModalide())
                .addProperty("situacao", this._evento.getSituacao())
                .addLink("participantes", participanteResources)
                .expand("participantes");
        return builder;
    }

    public static EventoResource of(Evento evento) {
        return new EventoResource(evento);
    }
}
