package domain.esporte.resultado;

import org.immutables.value.Value;

import java.time.Duration;

@Value.Style(
        visibility = Value.Style.ImplementationVisibility.PACKAGE
)
@Value.Immutable(builder = false)
public abstract class TimeScore implements Score<Duration> {

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public static TimeScore with(Duration duration) {
        return ImmutableTimeScore.of(duration);
    }
}
