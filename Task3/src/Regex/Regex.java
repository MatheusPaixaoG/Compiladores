package Regex;

import TokenUtils.TokenType;

public class Regex {
    private String REGEX_NUM = "(\\d)+";
    private String REGEX_PLUS = "(\\+)";
    private String REGEX_MINUS = "(\\-)";
    private String REGEX_STAR = "(\\*)";
    private String REGEX_SLASH = "(/)";
    private String REGEX_POW = "(\\^)";

    private boolean isNum(String token) {
        return token.matches(REGEX_NUM);
    }

    private boolean isOP(String token) {
        return token.matches("(\\+|\\-|\\*|\\^|/)");
    }

    private TokenType determineOperation(String token) {
        if (token.matches(REGEX_PLUS)) {
            return TokenType.PLUS;
        } else if (token.matches(REGEX_MINUS)) {
            return TokenType.MINUS;
        } else if (token.matches(REGEX_STAR)) {
            return TokenType.STAR;
        } else {
            return TokenType.SLASH;
        }
    }

    public TokenType getTokenType(String token) {
        if (this.isOP(token)) {
            return determineOperation(token);
        } else if (this.isNum(token)) {
            return TokenType.NUM;
        } else {
            return TokenType.NOTVALID;
        }
    }
}
