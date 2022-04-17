package tictactoe.player;

import tictactoe.exception.CoordinateIsBusyException;
import tictactoe.exception.CoordinateIsNotInIntervalException;
import tictactoe.exception.GameException;
import tictactoe.exception.WordsInsteadOfCoordinatesException;
import tictactoe.grid.GridCoordinate;
import tictactoe.grid.GameGrid;
import tictactoe.grid.GridSymbol;
import tictactoe.io.IOHandler;

import java.util.regex.Pattern;

public class User extends Player {

    private final IOHandler io;

    public User(GameGrid grid, GridSymbol symbol, IOHandler io) {
        super(grid, symbol);
        this.io = io;
    }

    @Override
    public void move() {
        GridCoordinate coordinate = getCoordinate(grid);
        grid.setSymbol(botSymbol, coordinate);
    }

    private GridCoordinate getCoordinate(GameGrid gameGrid) {
        GridCoordinate coordinate;
        do {
            try {
                coordinate = askForNextMoveCoordinate();
                checkAvailabilityOfCoordinate(gameGrid, coordinate);
                return coordinate;
            } catch (GameException e) {
                handleGameException(e);
            }
        } while (true);
    }

    private GridCoordinate askForNextMoveCoordinate() {
        io.send("Enter the coordinates:");
        final var coordinate = io.readLine();
        checkValidityOfCoordinate(coordinate);

        return parseCoordinate(coordinate);
    }

    private void checkValidityOfCoordinate(String coordinate) {
        Pattern pattern = Pattern.compile("\\d+\\s+\\d+");
        if (!pattern.matcher(coordinate).matches()) {
            throw new WordsInsteadOfCoordinatesException();
        }

        pattern = Pattern.compile("[1-3]\\s+[1-3]");
        if (!pattern.matcher(coordinate).matches()) {
            throw new CoordinateIsNotInIntervalException();
        }
    }

    private GridCoordinate parseCoordinate(String coordinate) {
        String[] coordinates = coordinate.split(" ");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);

        return new GridCoordinate(x, y);
    }

    private void checkAvailabilityOfCoordinate(GameGrid gameGrid, GridCoordinate coordinate) throws GameException {
        if (gameGrid.isBusy(coordinate)) {
            throw new CoordinateIsBusyException();
        }
    }

    private void handleGameException(GameException e) {
        if (e instanceof WordsInsteadOfCoordinatesException) {
            io.send("You should enter numbers!");
        } else if (e instanceof CoordinateIsNotInIntervalException) {
            io.send("Coordinates should be from 1 to 3!");
        } else if (e instanceof CoordinateIsBusyException) {
            io.send("This cell is occupied! Choose another one!");
        }
    }
}
