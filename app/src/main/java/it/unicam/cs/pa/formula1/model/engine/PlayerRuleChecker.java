package it.unicam.cs.pa.formula1.model.engine;

import it.unicam.cs.pa.formula1.model.circuit.checkpoint.CheckPointType;
import it.unicam.cs.pa.formula1.model.RacetrackGame;
import it.unicam.cs.pa.formula1.model.circuit.location.Location;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import it.unicam.cs.pa.formula1.model.player.PlayerStatus;

/**
 * Responsibility : This class apply Game Rule to Player Movement changing its Status in accord of Rules
 *
 * @author Pierdominici
 */
public class PlayerRuleChecker {

    public static String applyGameRule(RacetrackGame game, GamePlayer player){
        getSpecialCrossedCheckPoints(game,player);
        checkAndSetOffRoad(game, player.lastMovement().getEndPoint(), player);
        return checkCollisions(game) + PromptInformation.updatePlayerStatus(player.lastMovement(), player);
    }

    /**
     * Check if last Movement Of Player goes off road or not, the check if he finished
     *
     * @param location Last EndPoint of VectorMovement
     * @param player player who moved
     */
    private static void checkAndSetOffRoad(RacetrackGame game, Location location, GamePlayer player){
        if(game.getCircuit().containsPosition(location)==null && player.getStatus()!=PlayerStatus.FINISHED){
            player.setStatus(PlayerStatus.OFF_ROAD);
        }
    }

    /**
     * Check if there was a collision between two car, and remove them from game
     *
     * @return String info about collision, if there was one
     */
    private static String checkCollisions(RacetrackGame game){
        StringBuilder collisionStatus = new StringBuilder();
        for(int i = 0; i < game.getPlayers().size(); i++) {
            for (int j = 0; j < game.getPlayers().size(); j++) {
                if (!game.getPlayers().get(i).equals(game.getPlayers().get(j))) {
                    if(game.getPlayers().get(i).allMovements().size()==0 || game.getPlayers().get(j).allMovements().size()==0)
                        break;
                    if(game.getPlayers().get(i).getStatus()==PlayerStatus.CRASHED && game.getPlayers().get(j).getStatus()==PlayerStatus.CRASHED)
                        break;
                    if(game.getPlayers().get(i).getStatus()==PlayerStatus.FINISHED || game.getPlayers().get(j).getStatus()==PlayerStatus.FINISHED)
                        break;
                    if (game.getPlayers().get(i).lastMovement().getEndPoint().equals(game.getPlayers().get(j).lastMovement().getEndPoint())) {
                        game.getPlayers().get(i).setStatus(PlayerStatus.CRASHED);
                        game.getPlayers().get(j).setStatus(PlayerStatus.CRASHED);
                        collisionStatus.append(PromptInformation.collisionBetweenPlayers(game.getPlayers().get(i), game.getPlayers().get(j)));
                    }
                }
            }
        }
        return collisionStatus.toString();
    }

    private static void getSpecialCrossedCheckPoints(RacetrackGame game, GamePlayer player){
        int x1 = player.lastMovement().getStartPoint().getX();
        int y1 = player.lastMovement().getStartPoint().getY();

        int x2 = player.lastMovement().getEndPoint().getX();
        int y2 = player.lastMovement().getEndPoint().getY();

        for (int i = 0; i < game.getCircuit().getCheckPoints().size(); i++) {
            if (game.getCircuit().getCheckPoints().get(i).getType() == CheckPointType.OBLIGATORY || game.getCircuit().getCheckPoints().get(i).getType() == CheckPointType.FINISH || game.getCircuit().getCheckPoints().get(i).getType() == CheckPointType.START_FINISH) {
                for (int j = 0; j < game.getCircuit().getCheckPoints().get(i).getPoints().size(); j++) {
                    int x = game.getCircuit().getCheckPoints().get(i).getPoints().get(j).getX();
                    int y = game.getCircuit().getCheckPoints().get(i).getPoints().get(j).getY();
                    checkCheckPointType(game,player, x1, y1, x2, y2, i, x, y);
                }
            }
        }
    }

    private static void checkCheckPointType(RacetrackGame game, GamePlayer player, int x1, int y1, int x2, int y2, int i, int x, int y) {
        if(x1<x2 && x1<= x && x<=x2){
            if(y1<y2 && y1<=y && y<=y2)
                applyCondition(game,player,i);
            else if(y2 <= y && y <= y1)
                applyCondition(game,player,i);
        }
        else if(x2 <= x && x <= x1){
            if(y2<y1 && y2<=y && y<=y1)
                applyCondition(game,player,i);
            else if(y1 <= y && y <= y2)
                applyCondition(game,player,i);
        }
    }

    private static void applyCondition(RacetrackGame game, GamePlayer player, int i) {
        if(game.getCircuit().getCheckPoints().get(i).getType()==CheckPointType.OBLIGATORY) {
            if (!player.getCrossedCheckPoint().contains(game.getCircuit().getCheckPoints().get(i))) {
                player.getCrossedCheckPoint().add(game.getCircuit().getCheckPoints().get(i));
            }
        }
        if(game.getCircuit().getCheckPoints().get(i).getType()==CheckPointType.START_FINISH) {
            if(player.getCrossedCheckPoint().size() == game.getCircuit().getObligatoryCheckPoints()){
                player.resetCrossedCheckPoint();
                if(player.getLap()+1<game.getCircuit().getLaps())
                    player.addLap();
                else
                    player.setStatus(PlayerStatus.FINISHED);
            }
        }
        if(game.getCircuit().getCheckPoints().get(i).getType()==CheckPointType.FINISH){
            player.setStatus(PlayerStatus.FINISHED);
        }
    }
}
