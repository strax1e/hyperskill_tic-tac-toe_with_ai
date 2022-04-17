package tictactoe.player.factory;

import org.springframework.stereotype.Component;
import tictactoe.exception.UnknownPlayerTypeException;
import tictactoe.grid.GridSymbol;
import tictactoe.io.IOHandler;
import tictactoe.grid.GameGrid;
import tictactoe.player.*;
import tictactoe.player.bot.EasyBot;
import tictactoe.player.bot.HardBot;
import tictactoe.player.bot.MediumBot;

@Component
public class PlayerFactoryStandard implements PlayerFactory {

    @Override
    public Player make(String playerType, GameGrid grid, GridSymbol symbol, IOHandler io) {
        return switch (playerType) {
            case "easy" -> new EasyBot(grid, symbol, io);
            case "medium" -> new MediumBot(grid, symbol, io);
            case "hard" -> new HardBot(grid, symbol, io);
            case "user" -> new User(grid, symbol, io);
            default -> throw new UnknownPlayerTypeException();
        };
    }
}
