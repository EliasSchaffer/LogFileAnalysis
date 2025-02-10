package controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AnalyzeLogs;
import model.Log;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class AnalyzeViewController {

    @FXML private TableColumn<Log, String> tbcDate;
    @FXML private TableColumn<Log, String> tbcErrorLevel;
    @FXML private TableColumn<Log, String> tbcMessage;
    @FXML private TextField txtPath;
    @FXML private TableView<Log> tbvOutput;
    @FXML private Text txtResults;
    private AnalyzeLogs analyzer;
    private ArrayList<Log> logs;

    private String filePath;


    public void initialize() {
        logs = new ArrayList<>();
        tbvOutput.setEditable(false);
        tbcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tbcErrorLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        tbcMessage.setCellValueFactory(new PropertyValueFactory<>("message"));

    }

    @FXML
    void onSubmit(ActionEvent event) {
        if (!txtPath.getText().isEmpty()) {
            filePath = txtPath.getText();
        }

        analyzer = new AnalyzeLogs(filePath);
        logs = analyzer.analyzeLogs();
        showSummary();
        refreshTable();
    }

    private void refreshTable() {
        tbvOutput.getItems().clear();
        for (Log log : logs) {
            tbvOutput.getItems().add(log);
        }
    }

    private void showSummary(){
        txtResults.setText(analyzer.getSummary().toString());
    }



}
