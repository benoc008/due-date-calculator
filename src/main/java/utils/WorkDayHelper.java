package utils;

import java.time.LocalDateTime;

import static java.time.DayOfWeek.*;

public class WorkDayHelper {

    private static final int FIRST_WORKING_HOUR = 9;
    private static final int LAST_WORKING_HOUR = 17;
    private static final int WEEKEND_OFFSET = 2;

    public static LocalDateTime calculateEndOfWorkDay(LocalDateTime dateTime) {
        return dateTime.withHour(LAST_WORKING_HOUR).withMinute(0);
    }

    public static LocalDateTime calculateBeginOfNextWorkDay(LocalDateTime dateTime) {
        LocalDateTime nextWorkDay = calculateNextWorkDay(dateTime);
        return calculateBeginOfWorkDay(nextWorkDay);
    }

    private static LocalDateTime calculateBeginOfWorkDay(LocalDateTime dateTime) {
        return dateTime.withHour(FIRST_WORKING_HOUR).withMinute(0);
    }

    private static LocalDateTime calculateNextWorkDay(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek().equals(FRIDAY)) {
            return dateTime.plusDays(1 + WEEKEND_OFFSET);
        }
        return dateTime.plusDays(1);
    }

    public static boolean isDuringWorkingHours(LocalDateTime submitDate) {
        boolean isWorkday = !(submitDate.getDayOfWeek().equals(SATURDAY) || submitDate.getDayOfWeek().equals(SUNDAY));

        LocalDateTime dayBegin = WorkDayHelper.calculateBeginOfWorkDay(submitDate);
        LocalDateTime dayEnd = WorkDayHelper.calculateEndOfWorkDay(submitDate);

        boolean afterBegin = dayBegin.isBefore(submitDate) || dayBegin.isEqual(submitDate);
        boolean beforeEnd = dayEnd.isAfter(submitDate) || dayEnd.isEqual(submitDate);

        return isWorkday && afterBegin && beforeEnd;
    }

}
