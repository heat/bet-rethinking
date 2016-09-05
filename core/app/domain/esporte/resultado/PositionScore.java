package domain.esporte.resultado;

public class PositionScore implements Score<Long> {

    private final Long _position;

    public PositionScore(Long _position) {
        this._position = _position;
    }

    @Override
    public Long result() {
        return this._position;
    }

    @Override
    public int compareTo(Score o) {

        return _position.compareTo((Long) o.result());
    }
}
