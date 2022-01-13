package it.unicam.cs.pa.formula1.view;

import it.unicam.cs.pa.formula1.controller.Controller;
import it.unicam.cs.pa.formula1.controller.RacetrackController;
import it.unicam.cs.pa.formula1.model.engine.PromptInformation;

import java.util.Scanner;

/**
 * Responsibility : This class provides the Prompt Graphical Interface using Scanner and Println to show game
 *
 * @author Pierdominici
 */
public class PromptView {

    private final Controller controller;

    public PromptView(String filePath){
        controller = new RacetrackController(filePath);
        controller.newGame();
        System.out.println(PromptInformation.gameInformation());
    }

    /**
     * Using a while loop, play the game, waiting for User Input
     */
    public void showAndWait(){
        while (!controller.getGame().isFinished()){
            Scanner scanner = new Scanner(System.in);
            System.out.println(PromptInformation.gameControllerOptions());
            int i;
            try{
                i = scanner.nextInt();
                if (i<0 || i>3)
                    throw new Exception();
                else
                    checkInput(i);
            }
            catch (Exception e){
                System.out.println("\nINPUT ERROR: You have to write a natural number from 0 to 4...");
            }
        }
    }

    /**
     * Check input user given from command prompt
     *
     * @param i input
     */
    private void checkInput(int i) {
        if(i==0){
            System.out.println(controller.nextMove());
        }
        else if(i==1){
            System.out.println("\nLoading SCOREBOARD...");
            System.out.println(controller.getGameScoreBoard());
        }
        else if(i==2){
            System.out.println("\nNow I'll restart your game...\n\n\n");
            controller.newGame();
            System.out.println(PromptInformation.gameInformation());
        }
        else if(i==3){
            System.out.println("\nNow I'll close game.\nThanks for playing...");
            System.exit(0);
        }
    }
}
