package domain.esporte;

import domain.equipe.Equipe;
import domain.equipe.ImmutableEquipe;
import domain.esporte.modalidade.Modalidade;
import domain.esporte.resultado.*;
import org.apache.http.annotation.Immutable;
import org.junit.Test;
import org.mockito.internal.matchers.Any;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

public class ResultTest {

    @Test
    public void GetVencedorEmpateTest() {


        Classificador classificador = mock(Classificador.class);
        when(classificador.classificar(any()))
                .thenReturn(Collections.emptyList());

        Regra regra = mock(Regra.class);
        when(regra.apply(any()))
                .thenReturn(Resultados.Situacao.EMPATADDO);

        Participante santosParticipante = mock(Participante.class);
        Participante flamengoParticipante = mock(Participante.class);

        Resultados resultados = Resultados.of(classificador)
                .with(regra)
                .with(santosParticipante, flamengoParticipante)
                .build();


        Optional<Classificacao> vencedor = resultados.getVencedor();
        assertThat("O empate não tem vencedor", vencedor.isPresent(), is(false));

        Resultados.Situacao situacao = resultados.getSituacao();

        assertThat("Situacao deve ser empatada", situacao, is(Resultados.Situacao.EMPATADDO));
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

        Classificacao<PointScore> santosClassificacao = Classificacao.of(santosParticipante, 2L);
        Classificacao<PointScore> flamengoClassificacao = Classificacao.of(flamengoParticipante, 1L);

        Classificador classificador = mock(Classificador.class);
        when(classificador.classificar(any()))
                .thenReturn(Arrays.asList(flamengoClassificacao, santosClassificacao));
        Regra regra = mock(Regra.class);
        when(regra.apply(any()))
                .thenReturn(Resultados.Situacao.DETERMINADO);

        Resultados resultados = Resultados.of(classificador)
                .with(flamengoParticipante, santosParticipante)
                .with(regra)
                .build();

        assertTrue("Deve haver um vencedor", resultados.getVencedor().isPresent());

        Classificacao vencedor = resultados.getVencedor().get();
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

        Resultados resultados = mock(Resultados.class);
        StringBuilder toStringBuilder = new StringBuilder();
        toStringBuilder.append("Resultados {")
                .append(" Santos: 2 ")
                .append(" Flamengo: 3")
                .append("}");
        when(resultados.toString())
                .thenReturn(toStringBuilder.toString());

        String expectedSantos = "Santos: 2";
        String expectedFlamengo = "Flamengo: 3";
        String result = resultados.toString();
        assertThat("O Resultado deve ter a saida correta", result, allOf(
                containsString(expectedSantos),
                containsString(expectedFlamengo)) );
    }
}
