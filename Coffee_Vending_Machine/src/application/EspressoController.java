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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EspressoController {

    @FXML
    private TextField affprice;

    @FXML
    private TextField ameriprice;

    @FXML
    private Button back;

    @FXML
    private Button buy1;

    @FXML
    private Button buy2;

    @FXML
    private Button buy3;

    @FXML
    private TextField mochaprice;

    @FXML
    void affogato(ActionEvent event) throws IOException {
    	
    	int requiredespressoQuantity = 30;
        int requiredvanillaQuantity = 70;
        
        
    	
    	int currentespressoQuantity = getAvailableQuantity("Espresso");
        int currentvanillaQuantity = getAvailableQuantity("Vanilla Gelatto");
        
        if (currentespressoQuantity < requiredespressoQuantity || currentvanillaQuantity < requiredvanillaQuantity) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(affprice.getText());

        // Fetch the cost of Americano from the database
        double dbCost = getaffogatoCostFromDatabase();
        if (userInput == dbCost) {
            
        	reduceaff();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
       	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
       	    MongoCollection<Document> collection = database.getCollection("sales");
       	    collection.updateOne(
       	            new Document("name", "Espresso"),  // Find document by item name
       	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) // Update the quantity field
       	        ));
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) buy1.getScene().getWindow()).close();
        }
        else{
       	 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText(null);
            alert.setContentText("Enter the correct amount");
            alert.show();
       }
}

    @FXML
    void americano(ActionEvent event) throws IOException {
    	
    	
    	int requiredespressoQuantity = 30;
        int requiredwaterQuantity =180;
        
        
    	
    	int currentespressoQuantity = getAvailableQuantity("Espresso");
        int currentwaterQuantity = getAvailableQuantity("Water");
        
        if (currentespressoQuantity < requiredespressoQuantity || currentwaterQuantity < requiredwaterQuantity) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(ameriprice.getText());

        // Fetch the cost of Americano from the database
        double dbCost = getAmericanoCostFromDatabase();
        if (userInput == dbCost) {
        	reduceame();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
          	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
          	    MongoCollection<Document> collection = database.getCollection("sales");
          	    collection.updateOne(
          	            new Document("name", "Espresso"),  // Find document by item name
          	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) // Update the quantity field
          	        ));
        
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) buy2.getScene().getWindow()).close();}
        else{
       	 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText(null);
            alert.setContentText("Enter the correct amount");
            alert.show();
       }
    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) back.getScene().getWindow()).close();
    }

    @FXML
    void mocha(ActionEvent event) throws IOException {
    	
    	int requiredespressoQuantity = 30;
        int requiredcocoaQuantity = 20;
        int requiredmilkQuantity = 160;
        
    	
    	int currentespressoQuantity = getAvailableQuantity("Espresso");
        int currentcocoaQuantity = getAvailableQuantity("Cocoa mix");
        int currentmilkQuantity = getAvailableQuantity("Milk");
        if (currentespressoQuantity < requiredespressoQuantity || currentcocoaQuantity < requiredcocoaQuantity || currentmilkQuantity < requiredmilkQuantity) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(mochaprice.getText());

        /
        double dbCost = getmochaCostFromDatabase();
        if (userInput == dbCost) {
        	reducemocha();
            
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
          	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
          	    MongoCollection<Document> collection = database.getCollection("sales");
          	    collection.updateOne(
          	            new Document("name", "Espresso"),  // Find document by item name
          	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) // Update the quantity field
          	        ));
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

       
        ((Stage) buy3.getScene().getWindow()).close();}
        else{
       	 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText(null);
            alert.setContentText("Enter the correct amount");
            alert.show();
       }
    }
    private double getaffogatoCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("espresso"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Affogato")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    private double getAmericanoCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("espresso"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Americano")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    private double getmochaCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("espresso"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Mocha")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    private void reduceaff() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Espresso", 30);
        update(collection, "Vanilla Gelatto", 70);
        
    }
    private void reduceame() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Espresso", 30);
        update(collection, "Water", 180);
        
    }
    private void reducemocha() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Espresso", 30);
        update(collection, "Cocoa mix", 20);
        update(collection, "Milk", 160);
        
    }

    private void update(MongoCollection<Document> collection, String itemName, int quantityToReduce) {
        // Create a query to find the ingredient by its name and update its quantity
        collection.updateOne(
            new Document("name", itemName),  // Find document by item name
            new Document("$inc", new Document("quantity", -quantityToReduce))  // Decrement the quantity field
        );
    }
    private int getAvailableQuantity(String itemName) {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        Document document = collection.find(new Document("name", itemName)).first();
        if (document != null) {
            return document.getInteger("quantity", 0);  // Return the quantity if the document exists
        }
        return 0;  // Return 0 if the document does not exist
    }

}
