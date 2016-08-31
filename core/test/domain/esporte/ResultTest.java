package domain.esporte;

import domain.equipe.Equipe;
import domain.equipe.ImmutableEquipe;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.*;
import org.apache.http.annotation.Immutable;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class ResultTest {

    @Test
    public void GetVencedorEmpateTest() {

        Equipe santos = mock(Equipe.class);
        when(santos.getNome()).thenReturn("Santos");
        Participante santosParticipante = ImmutableParticipante.of(santos)
                .withResultado(PointScore.with(2));
        Equipe flamengo = mock(Equipe.class);
        when(flamengo.getNome()).thenReturn("Flamengo");
        Participante flamengoParticipante = ImmutableParticipante.of(flamengo)
                .withResultado(PointScore.with(3));

        Classificacao<PointScore> santosClassificacao = Classificacao.<PointScore>of(santosParticipante, 2L);
        Classificacao<PointScore> flamengoClassificacao = Classificacao.<PointScore>of(flamengoParticipante, 1L);

        Resultados resultados = new Resultados(santosClassificacao, flamengoClassificacao);

        Classificacao vencedor = resultados.getVencedor();
        assertThat("Flamengo deve ser o vencedor", vencedor, sameInstance(flamengoClassificacao));
    }

    @Test
    public void GetVencedorPointScoreTest() {

        Equipe santos = mock(Equipe.class);
        when(santos.getNome()).thenReturn("Santos");
        Participante santosParticipante = ImmutableParticipante.of(santos)
                .withResultado(PointScore.with(2));
        Equipe flamengo = mock(Equipe.class);
        when(flamengo.getNome()).thenReturn("Flamengo");
        Participante flamengoParticipante = ImmutableParticipante.of(flamengo)
                .withResultado(PointScore.with(3));

        Classificacao<PointScore> santosClassificacao = Classificacao.<PointScore>of(santosParticipante, 2L);
        Classificacao<PointScore> flamengoClassificacao = Classificacao.<PointScore>of(flamengoParticipante, 1L);

        Resultados resultados = new Resultados(santosClassificacao, flamengoClassificacao);

        Classificacao vencedor = resultados.getVencedor();
        assertThat("Flamengo deve ser o vencedor", vencedor, sameInstance(flamengoClassificacao));
    }

    @Test
    public void ToStringTest() {

        Equipe santos = mock(Equipe.class);
        when(santos.getNome()).thenReturn("Santos");
        Participante santosParticipante = ImmutableParticipante.of(santos)
                .withResultado(PointScore.with(2));
        Equipe flamengo = mock(Equipe.class);
        when(flamengo.getNome()).thenReturn("Flamengo");
        Participante flamengoParticipante = ImmutableParticipante.of(flamengo)
                .withResultado(PointScore.with(3));

        Classificacao<PointScore> santosClassificacao = Classificacao.<PointScore>of(santosParticipante, 2L);
        Classificacao<PointScore> flamengoClassificacao = Classificacao.<PointScore>of(flamengoParticipante, 1L);

        Resultados resultados = new Resultados(santosClassificacao, flamengoClassificacao);

        String expectedSantos = "Santos: 2";
        String expectedFlamengo = "Flamengo: 3";
        String result = resultados.toString();
        assertThat("O Resultado deve ter a saida correta", result, allOf(
                containsString(expectedSantos),
                containsString(expectedFlamengo)) );
    }
}
