package view.moduledetail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

public class ModuleDetailViewController extends GridPane {

    @FXML
    private Label labelTitle;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField textFieldModuleName;

    @FXML
    private DatePicker datePickerExamDate;

    @FXML
    private TextField textFieldResult;

    @FXML
    void cancel(ActionEvent event) {

    }

    public ModuleDetailViewController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModuleDetailView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void save(ActionEvent event) {
        this.getScene().getWindow().hide();
    }

}
