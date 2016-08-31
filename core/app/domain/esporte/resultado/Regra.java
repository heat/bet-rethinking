package domain.esporte.resultado;

import java.util.List;

public interface Regra {

    Resultados.Situacao apply(List<Classificacao> classificacoes);
}
