package tictactoe;

import org.springframework.stereotype.Service;
import tictactoe.exceptions.BadParametersException;
import tictactoe.exceptions.GameException;
import tictactoe.io.IOHandler;

import java.util.regex.Pattern;

@Service
public final class GameLauncher implements Launcher {

    private static final Pattern PATTERN_CORRECT_COMMAND =
            Pattern.compile("^((start)\\s(easy|medium|hard|user)\\s(easy|medium|hard|user)|exit)$");
    private static final String  EXIT_COMMAND            = "exit";

    private final IOHandler io;
    private final Game      game;

    public GameLauncher(IOHandler io, Game game) {
        this.io = io;
        this.game = game;
    }

    @Override
    public void start() {
        try (io) {
            tryStart();
        }
    }

    private void tryStart() {
        String parameterizedCommand = askForParameterizedCommand();

        while (!parameterizedCommand.equals(EXIT_COMMAND)) {
            String playerTypeCross = parsePlayerTypeCross(parameterizedCommand);
            String playerTypeNought = parsePlayerTypeNought(parameterizedCommand);

            sendResult(game.start(playerTypeCross, playerTypeNought));
            parameterizedCommand = askForParameterizedCommand();
        }
    }

    private String askForParameterizedCommand() {
        do {
            try {
                io.send("Input command: ");
                String command = io.readLine();
                checkValidityOfParameterizedCommand(command);
                return command;
            } catch (BadParametersException e) {
                io.send("Bad parameters!");
            }
        } while (true);
    }

    private void checkValidityOfParameterizedCommand(String command) throws GameException {
        if (!PATTERN_CORRECT_COMMAND.matcher(command).matches()) {
            throw new BadParametersException();
        }
    }

    private String parsePlayerTypeCross(String command) {
        return command.split("\\s")[1];
    }

    private String parsePlayerTypeNought(String command) {
        return command.split("\\s")[2];
    }

    private void sendResult(GameStatus status) {
        switch (status) {
            case CROSS_WON -> io.send("X wins");
            case NOUGHT_WON -> io.send("O wins");
            case DRAW -> io.send("Draw");
            default -> throw new IllegalArgumentException(String.format("Status %s not allowed here", status));
        }
    }
}
