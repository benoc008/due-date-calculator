package unit;

import exceptions.SubmitDateException;
import exceptions.SubmitDateIsNotFromWorkingHoursException;
import exceptions.SubmitDateIsNullException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import validators.SubmitDateValidator;

import java.time.LocalDateTime;

public class SubmitDateValidatorTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void validSubmitDate() throws SubmitDateException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:00");
        SubmitDateValidator.validate(validSubmitDate);
    }

    @Test
    public void submitDateOnWeekend() throws SubmitDateException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-26T10:15:00");
        expectedException.expect(SubmitDateIsNotFromWorkingHoursException.class);
        SubmitDateValidator.validate(validSubmitDate);
    }


    @Test
    public void submitDateBeforeFirstWorkHour() throws SubmitDateException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T08:15:00");
        expectedException.expect(SubmitDateIsNotFromWorkingHoursException.class);
        SubmitDateValidator.validate(validSubmitDate);
    }

    @Test
    public void submitDateAfterLastWorkHour() throws SubmitDateException {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T19:15:00");
        expectedException.expect(SubmitDateIsNotFromWorkingHoursException.class);
        SubmitDateValidator.validate(validSubmitDate);
    }

    @Test
    public void submitDateIsNull() throws SubmitDateException {
        LocalDateTime validSubmitDate = null;
        expectedException.expect(SubmitDateIsNullException.class);
        SubmitDateValidator.validate(validSubmitDate);
    }

}
