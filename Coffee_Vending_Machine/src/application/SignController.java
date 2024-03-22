package application;

import java.io.IOException;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SignController {

    @FXML
    private TextField contact;

    @FXML
    private TextField email;

    @FXML
    private TextField first;

    @FXML
    private TextField last;

    @FXML
    private PasswordField password;

    @FXML
    private Button register;

    @FXML
    private TextField user;

    private static final String DATABASE_NAME = "VendingMachine";
    private static final String COLLECTION_NAME = "user";
    @FXML
    void register(ActionEvent event) throws IOException {
    	if (user.getText().isEmpty() || password.getText().isEmpty() || first.getText().isEmpty() || last.getText().isEmpty() || contact.getText().isEmpty() || email.getText().isEmpty() ) {
            // Show error message
        	Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please fill all fields");
            alert.setContentText("One or more fields are empty. Please fill all fields to proceed.");
            alert.showAndWait();
        } else {
        	String username = user.getText();
            String userPassword = password.getText();
            String firstname = first.getText();
            String lastname = last.getText();
            String userContact = contact.getText();
            String userEmail = email.getText();

            // Connect to MongoDB
            try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
                // Connect to the database
                MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);

                // Get the user collection
                MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);

                // Create a document with user details
                Document userDocument = new Document()
                        .append("username", username)
                        .append("password", userPassword)
                        .append("first", firstname)
                        .append("last", lastname)
                        .append("contact", userContact)
                        .append("email", userEmail);

                // Insert the document into the collection
                collection.insertOne(userDocument);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Registration successful");
                alert.setContentText("Your account has been successfully created.");
                alert.showAndWait();
                // Close the MongoDB client
                mongoClient.close();
            }
        	
    	Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) register.getScene().getWindow()).close();}
    }

}
