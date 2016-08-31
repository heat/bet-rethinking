package domain.esporte.resultado;

import domain.esporte.Participante;

import java.util.List;

public interface Classificador {

    /**
     * Aplica regra de classificacao de participantes
     * @param participantes
     * @return
     */
    List<Classificacao> classificar(Iterable<Participante> participantes);
}
