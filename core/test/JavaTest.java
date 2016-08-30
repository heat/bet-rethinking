import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class JavaTest {

    @Test
    public void NomeCanincoClasse() {

        Class<MyClass> clazz = MyClass.class;

        String expected = "JavaTest.MyClass";

        assertThat("Nome canonico deve ser igual", clazz.getCanonicalName(), is(expected) );
    }

    @Test
    public void SetNaoDeveRepetirValores() {

    }

    class MyClass {

    }
}
