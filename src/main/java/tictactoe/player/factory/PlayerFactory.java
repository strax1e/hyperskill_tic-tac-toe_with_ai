package tictactoe.player.factory;

import org.springframework.stereotype.Component;
import tictactoe.grid.GameGrid;
import tictactoe.grid.GridSymbol;
import tictactoe.io.IOHandler;
import tictactoe.player.Player;

@Component
public interface PlayerFactory {

    Player make(String playerType, GameGrid grid, GridSymbol symbol, IOHandler io);
}
