package parser.evaluator;

import parser.functional.Maybe;
import parser.tokeniser.tokens.Token;

public interface EvaluationRuleset<EVALUATED_VALUE> {
    public Maybe<EVALUATED_VALUE> maybeValueRepresentationOfValueToken(Token givenToken);
    public Maybe<EVALUATED_VALUE> maybeValueResultOfApplyingPrefixTokenToValue(Token givenPrefixToken, EVALUATED_VALUE givenValue);
    public Maybe<EVALUATED_VALUE> maybeValueResultOfApplyingInfixTokenToLeftAndRightValue(Token givenInfixToken, EVALUATED_VALUE givenLeftValue, EVALUATED_VALUE givenRightValue);
}