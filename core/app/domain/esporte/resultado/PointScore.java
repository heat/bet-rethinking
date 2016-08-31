package domain.esporte.resultado;

public class PointScore implements Score<Integer> {

    private final Integer _result;

    public PointScore(Integer _result) {
        this._result = _result;
    }

    public static PointScore with(Integer result) {
            return new PointScore(result);
    }

    @Override
    public Integer result() {
        return _result;
    }

    @Override
    public int compareTo(Score o) {
        return  - result().compareTo((Integer) o.result());
    }
}
