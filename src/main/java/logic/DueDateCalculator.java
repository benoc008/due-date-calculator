package logic;

import exceptions.SubmitDateException;
import exceptions.TurnaroundTimeNotPositiveException;
import validators.SubmitDateValidator;
import validators.TurnaroundTimeValidator;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;
import static utils.WorkDayHelper.calculateBeginOfNextWorkDay;
import static utils.WorkDayHelper.calculateEndOfWorkDay;

public class DueDateCalculator {

    public static LocalDateTime calculateDueDate(LocalDateTime submitDate, int turnaroundTimeInHours) throws SubmitDateException, TurnaroundTimeNotPositiveException {
        SubmitDateValidator.validate(submitDate);
        TurnaroundTimeValidator.validate(turnaroundTimeInHours);
        return DueDateCalculator.calculate(submitDate, Duration.of(turnaroundTimeInHours, HOURS).toMinutes());
    }

    private static LocalDateTime calculate(LocalDateTime currentDate, long turnaroundTimeInMinutes) {
        LocalDateTime currentDatePlusAllRemainingTime = currentDate.plusMinutes(turnaroundTimeInMinutes);
        LocalDateTime endOfWorkDay = calculateEndOfWorkDay(currentDate);
        if (currentDatePlusAllRemainingTime.isBefore(endOfWorkDay)) {
            return currentDatePlusAllRemainingTime;
        }

        LocalDateTime beginOfNextWorkDay = calculateBeginOfNextWorkDay(currentDate);
        long remainingMinutes = turnaroundTimeInMinutes - Duration.between(currentDate, endOfWorkDay).toMinutes();
        return calculate(beginOfNextWorkDay, remainingMinutes);
    }

}
