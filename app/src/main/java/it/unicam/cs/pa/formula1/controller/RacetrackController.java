package it.unicam.cs.pa.formula1.controller;

import it.unicam.cs.pa.formula1.model.engine.PlayerRuleChecker;
import it.unicam.cs.pa.formula1.model.RacetrackGame;
import it.unicam.cs.pa.formula1.model.engine.JsonGameLoader;
import it.unicam.cs.pa.formula1.model.engine.PromptInformation;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.player.PlayerStatus;

/**
 * Responsibility : This class provide the implementation of the GameController
 *
 * @author Pierdominici
 */
public class RacetrackController implements Controller {

    private RacetrackGame game;
    private final String filePath;

    public RacetrackController(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public RacetrackGame getGame() {
        return game;
    }

    @Override
    public String getGameScoreBoard() {
        return game.getScoreBoard();
    }

    @Override
    public void newGame() {
        try{
            game = JsonGameLoader.loadGameFromFile(filePath);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Game Loading is failed... Please try again...");
        }
    }

    @Override
    public String nextMove() {
        while (!game.isFinished()) {
            for (GamePlayer player : game.getPlayers()) {
                if (player.getStatus() == PlayerStatus.IN_GAME) {
                    if (player.getTurn() < game.getGeneralTurn()) {
                        if(game.getGeneralTurn()>game.getBestPossibleRoad().size()-1) {
                            player.moveCar(game.getBestPossibleRoad().get(game.getBestPossibleRoad().size() - 1), player.isSmart());
                        }
                        else {
                            player.moveCar(game.getBestPossibleRoad().get(game.getGeneralTurn()), player.isSmart());
                            return PlayerRuleChecker.applyGameRule(game, player);
                        }
                    }
                }
            }
            if(!game.allPlayersFinished())
                game.addGeneralTurn();
            else{
                game.setFinished();
                return PromptInformation.notifyPlayersThatWon(game.getPlayers());
            }
        }
        return PromptInformation.gameIsFinished();
    }
}
