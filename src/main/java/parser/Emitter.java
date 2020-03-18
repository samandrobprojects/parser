package parser;

import parser.functional.*;

import java.util.*;

public class Emitter<OBJECT_TO_EMIT> {
    private Stack<OBJECT_TO_EMIT> _nextObjectsToEmitAsStack;
    private Maybe<OBJECT_TO_EMIT> _maybeNextObjectToEmit;

    public static <OBJECT_TO_EMIT> Emitter<OBJECT_TO_EMIT> emitterWithObjectsToEmitInOrder(Collection<OBJECT_TO_EMIT> objectsToEmit) {
        return new Emitter<>(objectsToEmit);
    }

    private Emitter(Collection<OBJECT_TO_EMIT> objectsToEmit) {
        _nextObjectsToEmitAsStack = new Stack<>();
        List<OBJECT_TO_EMIT> objectsToEmitAsArray = new ArrayList<>(objectsToEmit);
        Collections.reverse(objectsToEmitAsArray);
        _nextObjectsToEmitAsStack.addAll(objectsToEmitAsArray);
        if (_nextObjectsToEmitAsStack.size() > 0) {
            _maybeNextObjectToEmit = Maybe.asObject(_nextObjectsToEmitAsStack.pop());
        } else {
            _maybeNextObjectToEmit = Maybe.asNothing();
        }
    }

    public boolean hasMoreToEmit() {
        return _maybeNextObjectToEmit.isNotNothing();
    }

    public Maybe<OBJECT_TO_EMIT> maybeEmitNext() {
        Maybe<OBJECT_TO_EMIT> nextObjectToEmit = _maybeNextObjectToEmit;
        if (_nextObjectsToEmitAsStack.size() > 0) {
            _maybeNextObjectToEmit = Maybe.asObject(_nextObjectsToEmitAsStack.pop());
        } else {
            _maybeNextObjectToEmit = Maybe.asNothing();
        }
        return nextObjectToEmit;
    }

    public Maybe<OBJECT_TO_EMIT> maybePeekAtNext() {
        return _maybeNextObjectToEmit;
    }

    public Maybe<OBJECT_TO_EMIT> maybePeekAtNextOver() {
        if (_nextObjectsToEmitAsStack.size() > 0) {
            return Maybe.asObject(_nextObjectsToEmitAsStack.peek());
        } else {
            return Maybe.asNothing();
        }
    }

}
