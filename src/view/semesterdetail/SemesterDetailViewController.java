package view.semesterdetail;

import controller.SemesterController;
import controller.StudyPlannerController;
import exceptions.DataNotValidException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Semester;
import view.semesterview.SemesterViewController;

import java.io.IOException;
import java.time.LocalDate;

public class SemesterDetailViewController extends GridPane {

    @FXML
    private Label labelTitle;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField textFieldSemesterName;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private Label labelModifySemester;

    @FXML
    private ChoiceBox<Semester> choiseBoxModifySemester;

    private StudyPlannerController spc;

    private  HBox hBoxSemesterContainer;

    private boolean modifySemesters;



    public SemesterDetailViewController(StudyPlannerController spc, boolean modifySemesters, HBox hBoxSemesterContainer){
        this.spc = spc;
        this.hBoxSemesterContainer = hBoxSemesterContainer;
        this.modifySemesters = modifySemesters;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SemesterDetailView.fxml"));
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
        choiseBoxModifySemester.setVisible(modifySemesters);
        labelModifySemester.setVisible(modifySemesters);
        if(modifySemesters){
            choiseBoxModifySemester.setItems(spc.getStudyPlanner().getSemesters());

            choiseBoxModifySemester.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Semester>() {

                @Override
                public void changed(ObservableValue<? extends Semester> observable, Semester oldValue, Semester newValue) {
                    textFieldSemesterName.setText(newValue.getName());
                    datePickerStartDate.setValue(newValue.getStartDate());
                    datePickerEndDate.setValue(newValue.getEndDate());
                }
            });
        }
    }



    @FXML
    void cancel(ActionEvent event) {
        this.getScene().getWindow().hide();
    }


    @FXML
    void save(ActionEvent event) {
        SemesterController sc = spc.getSemesterController();
        String inputSemesterName = textFieldSemesterName.getText();
        LocalDate inputStartDate = datePickerStartDate.getValue();
        LocalDate inputEndDate = datePickerEndDate.getValue();
        if(!modifySemesters){
            try {
                sc.createSemester(inputSemesterName,inputStartDate,inputEndDate);
                Semester semester = spc.getStudyPlanner().getSemesters().get(spc.getStudyPlanner().getSemesters().size()-1);
                SemesterViewController semesterViewController = new SemesterViewController(spc,semester);
                hBoxSemesterContainer.getChildren().add(semesterViewController);
                this.getScene().getWindow().hide();
            } catch (DataNotValidException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Semester selectedSemester = choiseBoxModifySemester.getValue();
                sc.modifySemester(selectedSemester,inputSemesterName,inputStartDate,inputEndDate);
                this.getScene().getWindow().hide();
            } catch (DataNotValidException e) {
                e.printStackTrace();
            }
        }


    }

}
