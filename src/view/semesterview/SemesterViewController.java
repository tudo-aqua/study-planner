package view.semesterview;
import controller.StudyPlannerController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Module;
import model.Semester;
import view.moduledetail.ModuleDetailViewController;
import view.semesterdetail.SemesterDetailViewController;

import java.io.IOException;
import java.time.LocalDate;

public class SemesterViewController extends GridPane {
    @FXML
    private Label labelName;

    @FXML
    private Label labelCollectedECTSInSemester;

    @FXML
    private Label labelStartDate;

    @FXML
    private Label labelEndDate;

    @FXML
    private ListView<Module> listViewModuls;

    private Stage primaryStage;

    private StudyPlannerController studyPlannerController;

    private Semester semester;

    public SemesterViewController(StudyPlannerController spc, Semester semester){
        this.semester = semester;
        this.studyPlannerController = spc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SemesterView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    public void initialize() {
        labelName.textProperty().bind(semester.nameProperty());
        labelStartDate.textProperty().bind(semester.startDateProperty().asString());
        labelEndDate.textProperty().bind(semester.endDateProperty().asString());
        labelCollectedECTSInSemester.textProperty().bind(semester.collectedECTSProperty().asString());
        listViewModuls.setCellFactory(new Callback<ListView<Module>, ListCell<Module>>() {
            @Override
            public ListCell<Module> call(ListView<Module> studentListView) {
                return new ModuleCell();
            }
        });
        listViewModuls.setItems(semester.getModules());
        listViewModuls.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent click) {

                if (click.getClickCount() == 2) {
                    //Use ListView's getSelected Item
                    Module selectedItem = listViewModuls.getSelectionModel()
                            .getSelectedItem();
                   if(selectedItem != null){
                       Stage stage = new Stage();
                       Scene modifySemesterScene = new Scene(new ModuleDetailViewController(studyPlannerController,selectedItem));
                       stage.initModality(Modality.WINDOW_MODAL);
                       stage.initOwner(primaryStage);
                       stage.setScene(modifySemesterScene);
                       stage.showAndWait();
                   }
                }
            }
        });



    }

    public Semester getSemester(){
        return this.semester;
    }
}


