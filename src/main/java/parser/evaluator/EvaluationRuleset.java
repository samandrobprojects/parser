package parser.evaluator;

import functional.Maybe;
import parser.tokeniser.AcceptedAtomicToken;
import parser.tokeniser.tokens.Token;

import java.util.List;

public interface EvaluationRuleset<EVALUATED_VALUE> {
    public Maybe<EVALUATED_VALUE> maybeValueRepresentationOfValueToken(Token givenToken);
    public Maybe<EVALUATED_VALUE> maybeValueResultOfApplyingPrefixTokenToValue(Token givenPrefixToken, EVALUATED_VALUE givenValue);
    public Maybe<EVALUATED_VALUE> maybeValueResultOfApplyingInfixTokenToLeftAndRightValue(Token givenInfixToken, EVALUATED_VALUE givenLeftValue, EVALUATED_VALUE givenRightValue);
}