package model;

import controller.AnalyzeViewController;
import controller.MainViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private Stage stage;
    private MainViewController mainPageController;
    private AnalyzeViewController analyzeViewController;


    private void viewAnalyzePage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../view/analyzeView.fxml"));

        Parent root = fxmlLoader.load();
        analyzeViewController = fxmlLoader.getController();

        stage.setTitle("Farbtonkarte");
        stage.setScene(new Scene(root));
        stage.show();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        viewAnalyzePage();
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }
}
