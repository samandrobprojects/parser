package parser.functional;

import java.lang.UnsupportedOperationException;

public abstract class Monad<OBJECT> {

    public <OBJECT_OUTPUT> Monad<OBJECT_OUTPUT> applyGivenOperationOntoThisObjectMondically(MonadicOperation<Monad<OBJECT_OUTPUT>, OBJECT,OBJECT_OUTPUT> monadicOperationInQuestion) {
        throw new UnsupportedOperationException("This operation has not been implemented");
    }
}
