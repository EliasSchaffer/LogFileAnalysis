package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AnalyzeLogs;
import model.Log;

import java.util.ArrayList;
import java.util.TreeMap;

public class AnalyzeViewController {

    @FXML private TableColumn<Log, String> tbcDate;
    @FXML private TableColumn<Log, String> tbcErrorLevel;
    @FXML private TableColumn<Log, String> tbcMessage;
    @FXML private TextField txtPath;
    @FXML private TableView<Log> tbvOutput;
    private AnalyzeLogs analyzer;
    private ArrayList<Log> logs;

    private String filePath;


    public void initialize() {
        analyzer = new AnalyzeLogs();
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

        logs = analyzer.analyzeLogs(filePath);
        System.out.println(logs);
        refreshTable();
    }

    private void refreshTable() {
        tbvOutput.getItems().clear();
        for (Log log : logs) {
            tbvOutput.getItems().add(log);
        }
    }



}
