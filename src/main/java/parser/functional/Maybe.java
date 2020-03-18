package parser.functional;

public class Maybe<OBJECT> extends Monad<OBJECT> {

    private final OBJECT _object;
    private final boolean _isNothing;

    public boolean isNothing() {
        return this._isNothing;
    }

    public boolean isNotNothing() {
        return !this._isNothing;
    }

    public OBJECT object() {
        _validateMaybeObjectContainsValidObject();
        return this._object;
    }

    public boolean containsObjectEqualTo(OBJECT objectInQuestion) {
        if (this.isNotNothing()) {
            return objectInQuestion.equals(this.object());
        }
        return false;
    }

    @Override
    public boolean equals(Object objectToCompareToo) {
        if (objectToCompareToo == null || !(objectToCompareToo instanceof Maybe)) {
            return false;
        } else {
            Maybe maybeObjectToComapareToo = (Maybe) objectToCompareToo;
            return _equalsMaybeObject(maybeObjectToComapareToo);
        }
    }

    @Override
    public <OBJECT_OUTPUT> Maybe<OBJECT_OUTPUT> applyGivenOperationOntoThisObjectMondically(MonadicOperation<Monad<OBJECT_OUTPUT>, OBJECT,OBJECT_OUTPUT> monadicOperationInQuestion) {
        if (this.isNotNothing()) {
            Monad<OBJECT_OUTPUT> resultOfMonadicOperation = monadicOperationInQuestion.performMonadicOperation(this.object());
            _validateThatObjectReturnedByMonadicOperationIsAMaybeMonad(resultOfMonadicOperation);
            return (Maybe<OBJECT_OUTPUT>) resultOfMonadicOperation;
        } else {
            return (Maybe<OBJECT_OUTPUT>) Maybe.asNothing();
        }
    }

    public static Maybe asNothing() {
        return new Maybe(null, true);
    }

    public static <OBJECT> Maybe<OBJECT> asObject(OBJECT itself) {
        return new Maybe(itself, false);
    }

    private <OBJECT_OUTPUT> void _validateThatObjectReturnedByMonadicOperationIsAMaybeMonad(Monad<OBJECT_OUTPUT> objectInQuestion) {
        if(!(objectInQuestion instanceof Maybe)) {
            throw new IllegalOperationForMaybeState("Monadic operation returned the wrong type");
        }
    }

    private void _validateMaybeObjectContainsValidObject() {
        if(isNothing()) {
            throw new IllegalOperationForMaybeState("No object instance inside Maybe");
        }
    }

    private Maybe(OBJECT object, boolean isNothing) {
        this._object = object;
        this._isNothing = isNothing;
    }

    private boolean _equalsMaybeObject(Maybe maybeObjectToCompareToo) {
        if (this.isNothing() && maybeObjectToCompareToo.isNothing()) {
            return true;
        } else if (this.isNotNothing() && maybeObjectToCompareToo.isNotNothing()) {
            return (this.object().equals(maybeObjectToCompareToo.object()));
        } else {
            return false;
        }
    }
}