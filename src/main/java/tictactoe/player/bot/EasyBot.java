package tictactoe.player.bot;

import tictactoe.grid.GridCoordinate;
import tictactoe.grid.GameGrid;
import tictactoe.grid.GridSymbol;
import tictactoe.io.OHandler;

public final class EasyBot extends Bot {

    private final OHandler o;

    public EasyBot(GameGrid grid, GridSymbol symbol, OHandler o) {
        super(grid, symbol);
        this.o = o;
    }

    @Override
    public void move() {
        GridCoordinate coordinate = RandomMoveMaker.moveRandom(grid);
        grid.setSymbol(botSymbol, coordinate);

        o.send("Making move level \"easy\"");
    }
}
