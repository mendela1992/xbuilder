package constants;

public enum Character {

    Slash("/"),
    DoubleSlash("//"),
    OpenBracket("("),
    CloseBracket(")"),
    OpenSquareBracket("["),
    CloseSquareBracket("]"),
    At("@"),
    Equal("="),
    Quote("'"),
    Comma(","),
    all("*");

    private String value;

    Character(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}