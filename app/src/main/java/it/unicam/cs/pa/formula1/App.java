package it.unicam.cs.pa.formula1;

import it.unicam.cs.pa.formula1.view.JavaFxView;
import it.unicam.cs.pa.formula1.view.PromptView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private static String FILE_PATH;
    private static final int WINDOWS_DIM = 600;

    public static void main(String[] args) {
        if (args.length!=2) {
            System.err.println("Use : "
                    + "\n\t- <prompt/javafx> for use Prompt or JavaFx GUI"
                    + "\n\t- <filepath> to load file game configuration.");
        }
        FILE_PATH = args[1];
        if(args[0].equals("javafx")) {
            launch(args);
        }
        else if (args[0].equals("prompt")){
            PromptView controller = new PromptView(FILE_PATH);
            controller.showAndWait();
        }
        else
            System.err.println("First parameter must be <prompt> or <javafx>");
            System.exit(-1);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new JavaFxView(FILE_PATH, primaryStage, WINDOWS_DIM, WINDOWS_DIM);
    }
}
