package unit;

import org.junit.Test;
import utils.WorkDayHelper;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkDayHelperTest {

    @Test
    public void calculateEndOfWorkDay(){
        LocalDateTime inputDateTime = LocalDateTime.parse("2017-02-27T10:15:00");
        LocalDateTime expectedEndOfWorkDay = LocalDateTime.parse("2017-02-27T17:00:00");
        LocalDateTime resultEndOfWorkDay = WorkDayHelper.calculateEndOfWorkDay(inputDateTime);
        assertThat(resultEndOfWorkDay).isEqualTo(expectedEndOfWorkDay);
    }

    @Test
    public void calculateBeginOfWorkDay(){
        LocalDateTime inputDateTime = LocalDateTime.parse("2017-02-27T10:15:00");
        LocalDateTime expectedBeginOfNextWorkDay = LocalDateTime.parse("2017-02-28T09:00:00");
        LocalDateTime resultBeginOfNextWorkDay = WorkDayHelper.calculateBeginOfNextWorkDay(inputDateTime);
        assertThat(resultBeginOfNextWorkDay).isEqualTo(expectedBeginOfNextWorkDay);
    }

    @Test
    public void duringWorkingHours(){
        LocalDateTime validDateTime = LocalDateTime.parse("2017-02-27T10:15:00");
        boolean duringWorkingHours = WorkDayHelper.isDuringWorkingHours(validDateTime);
        assertThat(duringWorkingHours);
    }

    @Test
    public void beforeWorkingHours(){
        LocalDateTime beforeWorkingHours = LocalDateTime.parse("2017-02-27T07:15:00");
        boolean duringWorkingHours = WorkDayHelper.isDuringWorkingHours(beforeWorkingHours);
        assertThat(duringWorkingHours).isFalse();
    }

    @Test
    public void afterWorkingHours(){
        LocalDateTime afterWorkingHours = LocalDateTime.parse("2017-02-27T17:15:00");
        boolean duringWorkingHours = WorkDayHelper.isDuringWorkingHours(afterWorkingHours);
        assertThat(duringWorkingHours).isFalse();
    }

    @Test
    public void onWeekend(){
        LocalDateTime onWeekend = LocalDateTime.parse("2017-02-26T10:15:00");
        boolean duringWorkingHours = WorkDayHelper.isDuringWorkingHours(onWeekend);
        assertThat(duringWorkingHours).isFalse();
    }

}
