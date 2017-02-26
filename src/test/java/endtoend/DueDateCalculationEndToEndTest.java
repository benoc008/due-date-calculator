package endtoend;

import logic.DueDateCalculator;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class DueDateCalculationEndToEndTest {

    @Test
    public void happyPath() {
        LocalDateTime validSubmitDate = LocalDateTime.parse("2017-02-27T10:15:30");
        int validTurnaroundTimeInHours = 8;

        LocalDateTime expectedDueDate = LocalDateTime.parse("2017-02-28T10:15:30");

        assertThat(DueDateCalculator.calculate(validSubmitDate, validTurnaroundTimeInHours)).isEqualTo(expectedDueDate);
    }

}
