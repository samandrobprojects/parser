package parser.functional;

public interface MonadicOperation<MONADIC_OBJECT extends Monad<OBJECT_OUTPUT>, OBJECT_INPUT,OBJECT_OUTPUT> {

    MONADIC_OBJECT performMonadicOperation(OBJECT_INPUT input);
}