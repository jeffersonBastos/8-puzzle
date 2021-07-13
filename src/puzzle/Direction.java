package puzzle;

public enum Direction {
    up("cima"),
    down("baixo"),
    left("esquerda"),
    right("direita");

    private final String literal;

    private Direction(String literal) {
        this.literal = literal;
    }

    public String getLiteral() {
        return literal;
    }

}
