package unit;

import exceptions.TurnaroundTimeNotPositiveException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import validators.TurnaroundTimeValidator;

public class TurnaroundTimeValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validTurnaroundTime() throws TurnaroundTimeNotPositiveException {
        int validTurnaroundTime = 10;
        TurnaroundTimeValidator.validate(validTurnaroundTime);
    }

    @Test
    public void exceptionOnZeroTurnaroundTime() throws TurnaroundTimeNotPositiveException {
        int zeroTurnaroundTime = 0;
        expectedException.expect(TurnaroundTimeNotPositiveException.class);
        TurnaroundTimeValidator.validate(zeroTurnaroundTime);
    }

    @Test
    public void exceptionOnNegativeTurnaroundTime() throws TurnaroundTimeNotPositiveException {
        int negativeTurnaroundTime = 0;
        expectedException.expect(TurnaroundTimeNotPositiveException.class);
        TurnaroundTimeValidator.validate(negativeTurnaroundTime);
    }

}
