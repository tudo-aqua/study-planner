package view.semesterview;

import javafx.scene.control.Cell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;
import model.Module;
import model.enums.State;

public class ModuleCell extends ListCell<Module> {

    @Override
    public void updateItem(Module module, boolean empty) {
        super.updateItem(module, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            GridPane gridPane = new GridPane();
            Label labelName = new Label();
            labelName.textProperty().bind(module.nameProperty());
            gridPane.add(labelName,0,0,2,1);
            Label labelExamDate = new Label();
            labelExamDate.textProperty().bind(module.examDateProperty().asString());
            gridPane.add(labelExamDate,0,1,2,1);
            Label labelGrade = new Label();
            labelGrade.textProperty().bind(module.gradeProperty().asString());
            Label labelECTS = new Label();
            labelECTS.textProperty().bind(module.ectsProperty().asString());
            if(module.getState()== State.PASSED_WITH_GRADE){
                gridPane.add(labelGrade,0,2,1,1);
                gridPane.add(new Label("    "),1,2,1,1);
            }
            gridPane.add(labelECTS,2,2,1,1);
            setGraphic(gridPane);
        }
    }
}
