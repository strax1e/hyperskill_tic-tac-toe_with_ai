package tictactoe.grid;

import org.junit.jupiter.api.Test;
import tictactoe.grid.exceptions.GridException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GridSymbolTest {

    @Test
    void getByCharacterCross() {
        assertEquals(GridSymbol.CROSS, GridSymbol.getByCharacter('X'));
    }

    @Test
    void getByCharacterNought() {
        assertEquals(GridSymbol.NOUGHT, GridSymbol.getByCharacter('O'));
    }

    @Test
    void getByCharacterEmpty() {
        assertEquals(GridSymbol.EMPTY, GridSymbol.getByCharacter('_'));
    }

    @Test
    void getByCharacterException() {
        assertThrows(GridException.class, () -> GridSymbol.getByCharacter('r'));
    }

    @Test
    void testToStringCross() {
        assertEquals("X", GridSymbol.CROSS.toString());
    }

    @Test
    void testToStringNought() {
        assertEquals("O", GridSymbol.NOUGHT.toString());
    }

    @Test
    void testToStringEmpty() {
        assertEquals(" ", GridSymbol.EMPTY.toString());
    }
}