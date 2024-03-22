package application;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoClients;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button logins;

    @FXML
    private Hyperlink newusers;

    @FXML
    private PasswordField password;

    @FXML
    private TextField user;
    private static final String DATABASE_NAME = "VendingMachine";
    private static final String COLLECTION_NAME = "user";
	

    @FXML
    void login(ActionEvent event) throws IOException {
    	
    	String enteredUsername = user.getText();
        String enteredPassword = password.getText();
    	
    	 if (user.getText().isEmpty() || password.getText().isEmpty()) {
             Alert alert = new Alert(AlertType.ERROR);
             alert.setHeaderText(null);
             alert.setContentText("Please fill all blank fields");
             alert.showAndWait();
         }  	

         // Validate login credentials against MongoDB
    	 else if (validateLogin(enteredUsername, enteredPassword)) {

             Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
             Stage stage = new Stage();
             Scene scene = new Scene(root);

             stage.setScene(scene);
             stage.show();

             // Close the current stage (optional, depending on your application flow)
             ((Stage) logins.getScene().getWindow()).close();
         } 
             
          else {
             // If credentials are invalid, show an error message to the user
             // Here, you can display an alert or message to inform the user about the invalid login
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Invalid Credentials");
             alert.setHeaderText(null);
             alert.setContentText("Invalid username or password! Please try again.");
             alert.show();
         }
        	 
        	 
        	
    }

    @FXML
    void newuser(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Sign.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) newusers.getScene().getWindow()).close();
    }
    private boolean validateLogin(String username, String password) {
    	
   	 try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            var database = mongoClient.getDatabase(DATABASE_NAME);
            var collection = database.getCollection(COLLECTION_NAME);

            var query = new Document("username", username).append("password", password);
            Document user = collection.find(query).first();

            return user != null; // If user is found, credentials are valid
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Return false in case of any exceptions or errors
        }
   }

}
