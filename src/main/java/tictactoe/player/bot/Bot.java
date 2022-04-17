package tictactoe.player.bot;

import tictactoe.grid.GameGrid;
import tictactoe.grid.GridSymbol;
import tictactoe.player.Player;

public abstract sealed class Bot extends Player permits HardBot, MediumBot, EasyBot {

    protected Bot(GameGrid grid, GridSymbol symbol) {
        super(grid, symbol);
    }
}
