package validators;

import exceptions.TurnaroundTimeNotPositiveException;

public class TurnaroundTimeValidator {
    public static void validate(int turnaroundTime) throws TurnaroundTimeNotPositiveException {
        if(turnaroundTime <= 0){
            throw new TurnaroundTimeNotPositiveException();
        }
    }
}
