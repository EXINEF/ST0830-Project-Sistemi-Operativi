package it.unicam.cs.pa.formula1.view;

import it.unicam.cs.pa.formula1.controller.Controller;
import it.unicam.cs.pa.formula1.model.RacetrackGame;
import it.unicam.cs.pa.formula1.controller.RacetrackController;
import it.unicam.cs.pa.formula1.model.circuit.RacetrackCircuit;
import it.unicam.cs.pa.formula1.model.engine.PromptInformation;
import it.unicam.cs.pa.formula1.model.player.GamePlayer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Responsibility : This class provides the JavaFx Graphical Interface using Scene to draw game,
 *                  Button to interact with the user and Console to inform about all Actions
 *
 * @author Pierdominici
 */
public class JavaFxView {

    private final Controller controller;
    private final Group root;
    private final Scene scene;
    private final double scale;
    private final int width;
    private final int height;
    private static final int MARGIN = 100;


    public JavaFxView(String filePath, Stage stage, int winHeight, int winWidth) {
        controller = new RacetrackController(filePath);
        controller.newGame();
        System.out.println(PromptInformation.gameInformation());
        this.width = winWidth;
        this.height = winHeight;
        this.scale = calculateBestScale(controller.getGame().getCircuit());

        root = new Group();
        drawGame(controller.getGame());
        scene = new Scene(root,winWidth,winHeight);
        scene.setFill(Color.web("#81c483"));
        setPrimaryStage(stage);
    }

    private double calculateBestScale(RacetrackCircuit circuit){
        double scale;
        if(circuit.getHeight()>circuit.getWidth())
            scale = (double) (height - (MARGIN * 2)) / circuit.getHeight();
        else
            scale = (double) (width - (MARGIN * 2)) / circuit.getWidth();
        return scale;
    }

    /**
     * Set primary Stage giving it Scene, Title, other Options and its Action Listener
     *
     * @param primaryStage primary stage
     */
    private void setPrimaryStage(Stage primaryStage){
        primaryStage.setScene(scene);
        primaryStage.setTitle("Formula 1");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }

    private void restartGame(){
        root.getChildren().clear();
        controller.newGame();
        System.out.println(PromptInformation.gameInformation());
        drawGame(controller.getGame());
    }

    /**
     * Draw Game Once, drawing Players, Circuit and Buttons
     */
    private void drawGame(RacetrackGame game) {
        drawPlayers((ArrayList<GamePlayer>) game.getPlayers());
        drawCircuit(game.getCircuit());
        root.setTranslateX(MARGIN);
        root.setTranslateY(MARGIN);
        createAndCheckAllButtonActions();
    }

    /**
     * Refresh Game every time Next Move it's pressed, changing Player positions and Vector Movements
     */
    private void refreshGame(RacetrackGame game){
        refreshPlayers((ArrayList<GamePlayer>) game.getPlayers());
        refreshBotPlayersMovements((ArrayList<GamePlayer>) game.getPlayers());
    }

    /**
     * Create All Buttons, their Positions, their Name and their Actions Listener with their Action
     */
    private void createAndCheckAllButtonActions(){
        Button button0 = new Button("Next Move");
        button0.setTranslateX((double)width/5*1-2.5*scale- MARGIN);
        button0.setTranslateY((double)height/8*7- MARGIN);

        Button button1 = new Button("ScoreBoard");
        button1.setTranslateX((double)width/5*2-2.5*scale- MARGIN);
        button1.setTranslateY((double)height/8*7- MARGIN);

        Button button2 = new Button("New Game");
        button2.setTranslateX((double)width/5*3-2.5*scale- MARGIN);
        button2.setTranslateY((double)height/8*7- MARGIN);

        Button button3 = new Button("Quit Game");
        button3.setTranslateX((double)width/5*4-2.5*scale- MARGIN);
        button3.setTranslateY((double)height/8*7- MARGIN);

        root.getChildren().add(button0);
        root.getChildren().add(button1);
        root.getChildren().add(button2);
        root.getChildren().add(button3);

        button0.setOnAction(e -> {
                System.out.println(controller.nextMove());
                refreshGame(controller.getGame());
        });
        button1.setOnAction(e -> System.out.println(controller.getGameScoreBoard()));
        button2.setOnAction(e -> {
            System.out.println("\nNow I'll restart Game...");
            restartGame();
        });
        button3.setOnAction(e -> {
            System.out.println("\nNow I'll close game.\nThanks for playing...");
            System.exit(0);
        });
    }

    /**
     * Draw Player Once, describing their Shape, startX, startY, width, height and Color
     */
    private void drawPlayers(ArrayList<GamePlayer> players){
        for (GamePlayer player : players) {
            Rectangle shapePlayer = new Rectangle();
            if (player.allMovements().size() != 0) {
                shapePlayer.setX(scale * player.lastMovement().getEndPoint().getX() - scale / 2.0);
                shapePlayer.setY(scale * player.lastMovement().getEndPoint().getY() - scale / 2.0);
            }
            shapePlayer.setX(scale * player.getCar().getStartLocation().getX() - scale / 2.0);
            shapePlayer.setY(scale * player.getCar().getStartLocation().getY() - scale / 2.0);
            shapePlayer.setWidth(scale);
            shapePlayer.setHeight(scale);
            shapePlayer.setFill(Color.web(player.getCar().getColor().getHex()));
            root.getChildren().add(shapePlayer);
        }
    }

    /**
     * Draw Circuit Once when It is created, because it cannot change
     */
    private void drawCircuit(RacetrackCircuit circuit){
        for (int i = 0; i < circuit.getCheckPoints().size(); i++) {
            Line lineCheckPoint = new Line();
            lineCheckPoint.setStartX(scale * circuit.getCheckPoints().get(i).getStartPoint().getX());
            lineCheckPoint.setStartY(scale * circuit.getCheckPoints().get(i).getStartPoint().getY());
            lineCheckPoint.setEndX(scale * circuit.getCheckPoints().get(i).getEndPoint().getX());
            lineCheckPoint.setEndY(scale * circuit.getCheckPoints().get(i).getEndPoint().getY());
            lineCheckPoint.setStroke(Color.web(circuit.getCheckPoints().get(i).getType().getHex()));
            root.getChildren().add(lineCheckPoint);
        }
    }

    /**
     * Refresh Players who moves during the game changing their positions
     */
    private void refreshPlayers(ArrayList<GamePlayer> players){
        for (int i = 0; i < players.size(); i++){
            Rectangle shapePlayer = (Rectangle) root.getChildren().get(i);
            if(players.get(i).allMovements().size()!=0) {
                shapePlayer.setX(scale * players.get(i).lastMovement().getEndPoint().getX()-scale/2.0);
                shapePlayer.setY(scale * players.get(i).lastMovement().getEndPoint().getY()-scale/2.0);
            }
            else {
                shapePlayer.setX(scale * players.get(i).getCar().getStartLocation().getX()-scale/2.0);
                shapePlayer.setY(scale * players.get(i).getCar().getStartLocation().getY()-scale/2.0);
            }
        }
    }

    /**
     * Refresh Movements Vector to show Player Trajectory
     */
    private void refreshBotPlayersMovements(ArrayList<GamePlayer> players){
        for (GamePlayer player : players) {
            if (player.allMovements().size() != 0) {
                for (int j = 0; j < player.allMovements().size(); j++) {
                    Line lineVector = new Line();
                    lineVector.setStartX(scale * player.allMovements().get(j).getStartPoint().getX());
                    lineVector.setStartY(scale * player.allMovements().get(j).getStartPoint().getY());
                    lineVector.setEndX(scale * player.allMovements().get(j).getEndPoint().getX());
                    lineVector.setEndY(scale * player.allMovements().get(j).getEndPoint().getY());
                    lineVector.setStroke(Color.web(player.getCar().getColor().getHex()));
                    root.getChildren().add(lineVector);
                }
            }
        }
    }
}

