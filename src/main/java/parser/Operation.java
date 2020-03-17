package parser;

import functional.Maybe;
import functional.Monad;
import functional.MonadicOperation;
import parser.syntax_tree.SyntaxRuleset;
import parser.tokeniser.tokens.Token;

public class Operation<VALUE> {

    public interface InfixOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(VALUE givenLeftValue, VALUE givenRightValue);
    }

    public interface PrefixOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfApplyingPrefixOperationToValue(VALUE givenValue);
    }

    public static <VALUE> Operation<VALUE> infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax givenSyntax, int bindingStrength, InfixOperationEvaluation<VALUE> infixOperationEvaluation) {
        return new Operation<>(givenSyntax, bindingStrength, Maybe.asNothing(), Maybe.asObject(infixOperationEvaluation));
    }

    public static <VALUE> Operation<VALUE> prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax givenSyntax, int bindingStrength, PrefixOperationEvaluation<VALUE> prefixOperationEvaluation) {
        return new Operation<>(givenSyntax, bindingStrength, Maybe.asObject(prefixOperationEvaluation), Maybe.asNothing());

    }

    private final Syntax _givenSyntax;
    private final int _bindingStrength;
    private final Maybe<PrefixOperationEvaluation<VALUE>> _maybePrefixOperationEvaluation;
    private final Maybe<InfixOperationEvaluation<VALUE>> _maybeInfixOperationEvaluation;

    private Operation(Syntax givenSyntax, int bindingStrength, Maybe<PrefixOperationEvaluation<VALUE>> maybePrefixOperationEvaluation, Maybe<InfixOperationEvaluation<VALUE>> maybeInfixOperationEvaluation) {
        _givenSyntax = givenSyntax;
        _bindingStrength = bindingStrength;
        _maybeInfixOperationEvaluation = maybeInfixOperationEvaluation;
        _maybePrefixOperationEvaluation = maybePrefixOperationEvaluation;
    }

    public Syntax getSyntaxOfOperation() {
        return _givenSyntax;
    }

    public Maybe<VALUE> maybeValueResultOfApplyingAsPrefixOperationToValue(final VALUE givenValue) {
        return _maybePrefixOperationEvaluation.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<VALUE>, PrefixOperationEvaluation<VALUE>, VALUE>() {
            @Override
            public Maybe<VALUE> performMonadicOperation(PrefixOperationEvaluation<VALUE> prefixOperationEvaluation) {
                return prefixOperationEvaluation.maybeValueResultOfApplyingPrefixOperationToValue(givenValue);
            }
        });
    }

    public Maybe<VALUE> maybeValueResultOfApplyingAsInfixOperationToLeftAndRightValue(final VALUE givenLeftValue, final VALUE givenRightValue) {
        return _maybeInfixOperationEvaluation.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<VALUE>, InfixOperationEvaluation<VALUE>, VALUE>() {
            @Override
            public Maybe<VALUE> performMonadicOperation(InfixOperationEvaluation<VALUE> infixOperationEvaluation) {
                return infixOperationEvaluation.maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(givenLeftValue, givenRightValue);
            }
        });
    }

    public void classifySyntaxOfOperation(SyntaxRuleset.SyntaxCategoryClassifier syntaxClassifier) {
        if (_maybeInfixOperationEvaluation.isNotNothing()) {
            syntaxClassifier.classfiyAsInfixOperationWithBindingStrength(_bindingStrength);
        } else if (_maybePrefixOperationEvaluation.isNotNothing()) {
            syntaxClassifier.classfiyAsPrefixOperationWithBindingStrength(_bindingStrength);
        } else {
            syntaxClassifier.classifyAsInvalid();
        }
    }
}


