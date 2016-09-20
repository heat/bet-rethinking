package api.resource.esporte;

import black.door.hate.HalRepresentation;
import black.door.hate.HalResource;
import domain.esporte.Participante;
import domain.esporte.resultado.Score;

import java.net.URI;

public class ParticipanteResource implements HalResource {

    final Participante participante;

    private ParticipanteResource(Participante participante) {
        this.participante = participante;
    }

    public static ParticipanteResource of(Participante participante) {
        return new ParticipanteResource(participante);
    }

    @Override
    public HalRepresentation.HalRepresentationBuilder representationBuilder() {
        return HalRepresentation.builder()
                .addProperty("nome", participante.getNome())
                .addProperty("score", participante.getResultado().get());
    }

    @Override
    public URI location() {
        return URI.create("participantes");
    }
}
