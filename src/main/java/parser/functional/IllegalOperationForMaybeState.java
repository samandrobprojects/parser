package parser.functional;

import java.lang.RuntimeException;

public class IllegalOperationForMaybeState extends RuntimeException {

    public IllegalOperationForMaybeState(String errorMessage) {
        super(errorMessage);
    }
}