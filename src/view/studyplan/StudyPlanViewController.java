package view.studyplan;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.moduledetail.ModuleDetailViewController;

import java.io.IOException;

public class StudyPlanViewController extends GridPane {

    @FXML
    private Tab tabViewSemesteres;

    @FXML
    private Button buttonAddSemester;

    @FXML
    private Button buttonModifySemester;

    private Stage primaryStage;

    public StudyPlanViewController(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StudyPlanView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
            this.primaryStage = primaryStage;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addSemester(ActionEvent event) {
        Stage stage = new Stage();
        Scene newModuleScene = new Scene(new ModuleDetailViewController());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        stage.setScene(newModuleScene);
        stage.showAndWait();
    }

    @FXML
    void modifySemester(ActionEvent event) {

    }

}
