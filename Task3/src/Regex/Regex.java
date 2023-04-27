package Regex;

import TokenUtils.TokenType;

public class Regex {
    private String REGEX_NUM = "(\\d)+";
    private String REGEX_PLUS = "(\\+)";
    private String REGEX_MINUS = "(\\-)";
    private String REGEX_STAR = "(\\*)";
    private String REGEX_SLASH = "(/)";
    private String REGEX_POW = "(\\^)";

    public TokenType getTokenType(String token) {
        if (token.matches(REGEX_PLUS)) {
            return TokenType.PLUS;
        } else if (token.matches(REGEX_MINUS)) {
            return TokenType.MINUS;
        } else if (token.matches(REGEX_STAR)) {
            return TokenType.STAR;
        } else if (token.matches(REGEX_SLASH)) {
            return TokenType.SLASH;
        } else if (token.matches(REGEX_NUM)) {
            return TokenType.NUM;
        } else {
            return TokenType.NOTVALID;
        }
    }
}
