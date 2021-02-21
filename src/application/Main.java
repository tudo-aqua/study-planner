package application;

import controller.StudyPlannerController;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import view.studyplan.StudyPlanViewController;
import view.welcome.WelcomeViewController;

import java.io.File;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {


            StudyPlannerController studyPlannerController = new StudyPlannerController();
            File f = new File("data.sp");
            Scene scene;
            if(f.exists() && !f.isDirectory()) {
               studyPlannerController.getiOController().loadData();
               scene = new Scene(new StudyPlanViewController(primaryStage,studyPlannerController));

            }
            else {
                scene = new Scene(new WelcomeViewController(primaryStage, studyPlannerController));

            }
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
