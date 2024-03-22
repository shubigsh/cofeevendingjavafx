package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class ActionController {

    @FXML
    private Button bean;

    @FXML
    private Button espresso;

    @FXML
    private Button fresh;

    @FXML
    private MenuItem inventory;

    @FXML
    private Button premix;

    @FXML
    private MenuItem sales;

    @FXML
    void bean(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Bean.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) bean.getScene().getWindow()).close();
    }

    @FXML
    void espresso(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Espresso.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) espresso.getScene().getWindow()).close();
    }

    @FXML
    void fresh(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Fresh.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) fresh.getScene().getWindow()).close();
    }

    @FXML
    void inventory(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        
    }

    @FXML
    void premix(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Premix.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) premix.getScene().getWindow()).close();
    }

    @FXML
    void sales(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Sales.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
