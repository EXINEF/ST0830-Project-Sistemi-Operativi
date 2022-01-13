package it.unicam.cs.pa.formula1.model.engine;

import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.player.PlayerStatus;
import it.unicam.cs.pa.formula1.model.vector.Vector;

import java.util.List;

/**
 * Responsibility : This class provides all the Textual String for the Prompt Information System
 *
 * @author Pierdominici
 */
public class PromptInformation {

    public static String updatePlayerStatus(Vector lastVector, GamePlayer bot){
        String s = "\n"+bot.getName()+" ("+bot.getCar().getColor()+")"+
                " moved from "+ lastVector.getStartPoint().toString()+
                " to "+lastVector.getEndPoint().toString()+
                " speed: "+bot.getCar().getSpeed()+" m/s";
        if(bot.getStatus() == PlayerStatus.OFF_ROAD)
            s+=" and he finished OFF ROAD...";
        else if(bot.getStatus() == PlayerStatus.FINISHED)
            s+=" and he finished the Circuit...";
        else if(bot.getStatus() == PlayerStatus.IN_GAME)
            s+= " and he is still in the Game...";
        else
            s+=" and he crashed...";
        return s;
    }

    public static String gameControllerOptions(){
        return "\nPlease insert a number for the following action:" +
                "\n\t0. NextMove, a new move will be done" +
                "\n\t1. GetScoreBoard, to see a summary of the race at this moment" +
                "\n\t2. RestartGame, to restart a new Game" +
                "\n\t3. EndGame, to close game";
    }

    public static String gameInformation(){
        return "\nFormula 1 is a paper and pencil game that is played on a sheet of squared paper," +
                "\non which a fantasy car circuit is drawn, with a start line and a finish line, " +
                "\nconsists of a race between programmed players (Bot) competing to win the race"+
                "\n\nWelcome in RaceTrack Game, I hope you'll enjoy it...";
    }

    public static String gameIsFinished() {
        return "\nGame is finished..."+
                "\nRestart Game to play a new one or Quit Game to close application...";
    }

    public static String collisionBetweenPlayers(GamePlayer crusher, GamePlayer hit){
        return "\n" +crusher.getName()+
                " collided with "+hit.getName()+
                " in Location "+crusher.lastMovement().getEndPoint().toString()+" when ";
    }

    public static String notifyPlayersThatWon(List<GamePlayer> players){
        StringBuilder s = new StringBuilder("\nGame is finished and those are the winners:");
        boolean someoneFinish = false;
        for (GamePlayer player : players) {
            if (player.getStatus() == PlayerStatus.FINISHED) {
                s.append("\n\t").append(player.getName()).append(" (").append(player.getCar().getColor()).append(")");
                someoneFinish=true;
            }
        }
        if(!someoneFinish)
            return "\nNo player has finished the circuit";
        return s.toString();
    }

}
