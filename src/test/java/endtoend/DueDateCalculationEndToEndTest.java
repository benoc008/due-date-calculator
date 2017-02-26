package endtoend;

import exceptions.SubmitDateException;
import exceptions.SubmitDateIsNotFromWorkingHoursException;
import exceptions.SubmitDateIsNullException;
import exceptions.TurnaroundTimeNotPositiveException;
import logic.DueDateCalculator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DueDateCalculationEndToEndTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void calculateNextDay() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        int validTurnaroundTimeInHours = 8;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-02-28T10:15:00");
        LocalDateTime resultDueDate = DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);

        assertThat(resultDueDate).isEqualTo(expectedDueDate);
    }

    @Test
    public void calculateWithinDay() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        int validTurnaroundTimeInHours = 4;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-02-27T14:15:00");

        LocalDateTime resultDueDate = DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
        assertThat(resultDueDate).isEqualTo(expectedDueDate);
    }

    @Test
    public void calculateWholeDay() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T09:00:00");
        int validTurnaroundTimeInHours = 8;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-02-28T09:00:00");

        LocalDateTime resultDueDate = DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
        assertThat(resultDueDate).isEqualTo(expectedDueDate);
    }

    @Test
    public void calculateWithWeekend() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-24T10:15:00");
        int validTurnaroundTimeInHours = 8;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-02-27T10:15:00");

        LocalDateTime resultDueDate = DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
        assertThat(resultDueDate).isEqualTo(expectedDueDate);
    }

    @Test
    public void calculateMultipleDays() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        int validTurnaroundTimeInHours = 48;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-03-07T10:15:00");

        LocalDateTime resultDueDate = DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
        assertThat(resultDueDate).isEqualTo(expectedDueDate);
    }

    @Test
    public void calculateZeroHours() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        int validTurnaroundTimeInHours = 0;

        expectedException.expect(TurnaroundTimeNotPositiveException.class);
        DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
    }

    @Test
    public void calculateNegativeHours() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        int validTurnaroundTimeInHours = -100;

        expectedException.expect(TurnaroundTimeNotPositiveException.class);
        DueDateCalculator.calculateDueDate(validSubmitDate, validTurnaroundTimeInHours);
    }

    @Test
    public void calculateFromNullValue() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime invalidSubmitDate = null;
        int validTurnaroundTimeInHours = 8;

        expectedException.expect(SubmitDateIsNullException.class);
        DueDateCalculator.calculateDueDate(invalidSubmitDate, validTurnaroundTimeInHours);
    }

    @Test
    public void calculateFromOfftime() throws SubmitDateException, TurnaroundTimeNotPositiveException {
        LocalDateTime invalidSubmitDate = LocalDateTime.parse("2017-02-26T10:15:00");
        int validTurnaroundTimeInHours = 8;

        expectedException.expect(SubmitDateIsNotFromWorkingHoursException.class);
        DueDateCalculator.calculateDueDate(invalidSubmitDate, validTurnaroundTimeInHours);
    }
}
