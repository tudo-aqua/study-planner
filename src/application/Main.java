package application;

import service.StudyPlannerService;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.studyplan.StudyPlanViewController;
import view.welcome.WelcomeViewController;
import java.io.IOException;

/**
 * Main-Klasse des Programms mit der Startmethode.
 */
public class Main extends Application {
    /**
     * Start-Methode des JavaFX-Frameworks, in der die Programmstruktur erzeugt wird und bereits vorhandene
     * Daten geladen werden. Sollten keine Vorhanden sein, wird die Begrüßungs-View angezeigt.
     *
     * @param primaryStage Die initiale Stage.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            //Initialisierung der Controller- und der Model-Schicht
            StudyPlannerService studyPlannerService = new StudyPlannerService();
            Scene scene;
            try{

                //Daten aus Datei laden und Standard-View anzeigen
                studyPlannerService.getIOService().loadData("data.sp");
                scene = new Scene(new StudyPlanViewController(primaryStage, studyPlannerService));

            }catch (IOException | ClassNotFoundException e) {
                //Wenn keine Daten vorhanden sind oder Daten nicht gelesen werden können, Willkommens-View anzeigen
                scene = new Scene(new WelcomeViewController(primaryStage, studyPlannerService));
            }
            //css-Datei für das Stylen der View laden
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Standard-Java main-Methode.
     * @param args Array an Argumenten.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
