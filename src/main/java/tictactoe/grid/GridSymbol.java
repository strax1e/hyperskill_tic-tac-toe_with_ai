package tictactoe.grid;

import tictactoe.grid.exceptions.GridException;

public enum GridSymbol {
    CROSS, NOUGHT, EMPTY;

    static GridSymbol getByCharacter(char symbol) {
        return switch (symbol) {
            case 'X' -> CROSS;
            case 'O' -> NOUGHT;
            case '_' -> EMPTY;
            default -> throw new GridException(String.format("Unknown symbol '%c'", symbol));
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case CROSS -> "X";
            case NOUGHT -> "O";
            default -> " ";
        };
    }
}