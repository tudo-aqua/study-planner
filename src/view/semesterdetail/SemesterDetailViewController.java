package view.semesterdetail;

import service.SemesterService;
import service.StudyPlannerService;
import exceptions.DataNotValidException;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.StringConverter;
import entity.Semester;
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

    private StudyPlannerService studyPlannerService;

    private  HBox hBoxSemesterContainer;

    private boolean modifySemesters;



    public SemesterDetailViewController(StudyPlannerService spc, boolean modifySemesters, HBox hBoxSemesterContainer){
        this.studyPlannerService = spc;
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
        buttonSave.setText("Semester erstellen");
        choiseBoxModifySemester.setConverter(new StringConverter<Semester>() {
            @Override
            public String toString(Semester semester) {
                return semester.getName();
            }

            @Override
            public Semester fromString(String string) {
                return null;
            }
        });
        if(modifySemesters){
            buttonSave.setText("Semester bearbeiten");
            choiseBoxModifySemester.setItems(studyPlannerService.getStudyPlanner().getSemesters());
            choiseBoxModifySemester.getSelectionModel().selectedItemProperty().addListener(
                    (ObservableValue<? extends Semester> observable, Semester oldValue, Semester newValue) ->{
                    textFieldSemesterName.setText(newValue.getName());
                    datePickerStartDate.setValue(newValue.getStartDate());
                    datePickerEndDate.setValue(newValue.getEndDate());
            });
        }
    }



    @FXML
    void cancel(ActionEvent event) {
        this.getScene().getWindow().hide();
    }


    @FXML
    void save(ActionEvent event) {
        SemesterService sc = studyPlannerService.getSemesterService();
        String inputSemesterName = textFieldSemesterName.getText();
        LocalDate inputStartDate = datePickerStartDate.getValue();
        LocalDate inputEndDate = datePickerEndDate.getValue();
        if(!modifySemesters){
            try {
                Semester newSemester = sc.createSemester(inputSemesterName,inputStartDate,inputEndDate);
                SemesterViewController semesterViewController = new SemesterViewController(studyPlannerService,newSemester);
                hBoxSemesterContainer.getChildren().add(semesterViewController);
                this.getScene().getWindow().hide();
            } catch (DataNotValidException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eingabefehler");
                alert.setHeaderText("Fehler bei Eingabe");
                alert.setContentText("Bitte überprüfen Sie ihre Eingabe.");
                alert.showAndWait();
            }
        }
        else {
            try {
                Semester selectedSemester = choiseBoxModifySemester.getValue();
                if (selectedSemester != null) {
                    sc.modifySemester(selectedSemester, inputSemesterName, inputStartDate, inputEndDate);
                }
                this.getScene().getWindow().hide();
            } catch (DataNotValidException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Eingabefehler");
                alert.setHeaderText("Fehler bei Eingabe");
                alert.setContentText("Bitte überprüfen Sie ihre Eingabe.");
                alert.showAndWait();
            }
        }
    }

}
