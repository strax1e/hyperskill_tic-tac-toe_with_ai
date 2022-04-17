package tictactoe.player.factory;

import tictactoe.grid.GameGrid;
import tictactoe.grid.GridSymbol;
import tictactoe.io.IOHandler;
import tictactoe.player.Player;

public interface PlayerFactory {

    Player make(String playerType, GameGrid grid, GridSymbol symbol, IOHandler io);
}
