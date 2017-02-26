package validators;

import exceptions.SubmitDateException;
import exceptions.SubmitDateIsNotFromWorkingHoursException;
import exceptions.SubmitDateIsNullException;

import java.time.LocalDateTime;

import static utils.WorkDayHelper.isDuringWorkingHours;

public class SubmitDateValidator {
    public static void validate(LocalDateTime submitDate) throws SubmitDateException {
        validateIsNull(submitDate);
        validateWorkingHours(submitDate);
    }

    private static void validateWorkingHours(LocalDateTime submitDate) throws SubmitDateIsNotFromWorkingHoursException {
        if (!isDuringWorkingHours(submitDate)) {
            throw new SubmitDateIsNotFromWorkingHoursException();
        }
    }

    private static void validateIsNull(LocalDateTime submitDate) throws SubmitDateIsNullException {
        if (null == submitDate) {
            throw new SubmitDateIsNullException();
        }
    }
}
