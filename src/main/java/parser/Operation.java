package parser;

import parser.functional.*;
import parser.syntax_tree.SyntaxRuleset;
import parser.tokeniser.tokens.Token;

public class Operation<VALUE> {

    public interface InfixOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfApplyingInfixOperationToLeftAndRightValue(VALUE givenLeftValue, VALUE givenRightValue);
    }

    public interface PrefixOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfApplyingPrefixOperationToValue(VALUE givenValue);
    }

    public interface ConstantOperationEvaluation<VALUE> {
        public Maybe<VALUE> maybeValueResultOfEvaluatingConstantToValue();
    }

    public static <VALUE> Operation<VALUE> infixOperationWithSyntaxAndBindingStrengthAndInfixOperationEvaluation(Syntax givenSyntax, int bindingStrength, InfixOperationEvaluation<VALUE> infixOperationEvaluation) {
        return new Operation<VALUE>(givenSyntax, bindingStrength, Maybe.asNothing(), Maybe.asObject(infixOperationEvaluation), Maybe.asNothing());
    }

    public static <VALUE> Operation<VALUE> prefixOperationWithSyntaxAndBindingStrengthAndPrefixOperationEvaluation(Syntax givenSyntax, int bindingStrength, PrefixOperationEvaluation<VALUE> prefixOperationEvaluation) {
        return new Operation<VALUE>(givenSyntax, bindingStrength, Maybe.asObject(prefixOperationEvaluation), Maybe.asNothing(), Maybe.asNothing());

    }

    public static <VALUE> Operation<VALUE> constantOperationWithSyntaxConstantOperationEvaluation(Syntax givenSyntax, ConstantOperationEvaluation<VALUE> constantOperationEvaluation) {
        return new Operation<VALUE>(givenSyntax, 0, Maybe.asNothing(), Maybe.asNothing(), Maybe.asObject(constantOperationEvaluation));
    }

    private final Syntax _givenSyntax;
    private final int _bindingStrength;
    private final Maybe<PrefixOperationEvaluation<VALUE>> _maybePrefixOperationEvaluation;
    private final Maybe<InfixOperationEvaluation<VALUE>> _maybeInfixOperationEvaluation;
    private final Maybe<ConstantOperationEvaluation<VALUE>> _maybeConstantOperationEvaluation;

    private Operation(Syntax givenSyntax, int bindingStrength, Maybe<PrefixOperationEvaluation<VALUE>> maybePrefixOperationEvaluation, Maybe<InfixOperationEvaluation<VALUE>> maybeInfixOperationEvaluation, Maybe<ConstantOperationEvaluation<VALUE>> maybeConstantOperationEvaluation) {
        _givenSyntax = givenSyntax;
        _bindingStrength = bindingStrength;
        _maybeInfixOperationEvaluation = maybeInfixOperationEvaluation;
        _maybePrefixOperationEvaluation = maybePrefixOperationEvaluation;
        _maybeConstantOperationEvaluation = maybeConstantOperationEvaluation;
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

    public Maybe<VALUE> maybeValueResultOfEvaluatingAsConstantToValue() {
        return _maybeConstantOperationEvaluation.applyGivenOperationOntoThisObjectMondically(new MonadicOperation<Monad<VALUE>, ConstantOperationEvaluation<VALUE>, VALUE>() {
            @Override
            public Maybe<VALUE> performMonadicOperation(ConstantOperationEvaluation<VALUE> constantOperationEvaluation) {
                return constantOperationEvaluation.maybeValueResultOfEvaluatingConstantToValue();
            }
        });
    }

    public void classifySyntaxOfOperation(SyntaxRuleset.SyntaxCategoryClassifier syntaxClassifier) {
        if (_maybeInfixOperationEvaluation.isNotNothing()) {
            syntaxClassifier.classfiyAsInfixOperationWithBindingStrength(_bindingStrength);
        } else if (_maybePrefixOperationEvaluation.isNotNothing()) {
            syntaxClassifier.classfiyAsPrefixOperationWithBindingStrength(_bindingStrength);
        } else if (_maybeConstantOperationEvaluation.isNotNothing()) {
            syntaxClassifier.classifyAsValue();
        } else {
            syntaxClassifier.classifyAsInvalid();
        }
    }
}


