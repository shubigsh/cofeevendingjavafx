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

public class PremixController {

    @FXML
    private Button back;

    @FXML
    private Button buy1;

    @FXML
    private Button buy2;

    @FXML
    private Button buy3;

    @FXML
    private TextField chaiprice;

    @FXML
    private TextField coffeeprice;

    @FXML
    private TextField teaprice;

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
    void chai(ActionEvent event) throws IOException {
    	int requiredchaiQuantity = 15;
        int requiredwaterQuantity = 160;
        
        
    	
    	int currentchaiQuantity = getAvailableQuantity("Tea Powder");
        int currentwaterQuantity = getAvailableQuantity("Water");
        
        
        if (currentchaiQuantity < requiredchaiQuantity || currentwaterQuantity < requiredwaterQuantity) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(chaiprice.getText());

        // Fetch the cost of Americano from the database
        double dbCost = getchaiCostFromDatabase();
        if (userInput == dbCost) {
            
        	reducechai();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
        	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
        	    MongoCollection<Document> collection = database.getCollection("sales");
        	    collection.updateOne(
        	            new Document("name", "Premix"),  // Find document by item name
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
    void coffee(ActionEvent event) throws IOException {
    	int requiredcoffeeQuantity = 4;
        int requiredwaterQuantity = 170;
       
        
    	
    	int currentcoffeeQuantity = getAvailableQuantity("Coffee Powder");
        int currentwaterQuantity = getAvailableQuantity("Water");
        
        
        if (currentcoffeeQuantity < requiredcoffeeQuantity || currentwaterQuantity < requiredwaterQuantity ) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(coffeeprice.getText());

        // Fetch the cost of Americano from the database
        double dbCost = getcoffeeCostFromDatabase();
        if (userInput == dbCost) {
            
        	reducecoffee();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
       	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
       	    MongoCollection<Document> collection = database.getCollection("sales");
       	    collection.updateOne(
       	            new Document("name", "Premix"),  // Find document by item name
       	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) // Update the quantity field
       	        ));
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) buy2.getScene().getWindow()).close();
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
    void tea(ActionEvent event) throws IOException {
    	int requiredgreenQuantity = 3;
        int requiredwaterQuantity = 180;
       
        
    	
    	int currentgreenQuantity = getAvailableQuantity("Green Tea Powder");
        int currentwaterQuantity = getAvailableQuantity("Water");
        
        
        if (currentgreenQuantity < requiredgreenQuantity || currentwaterQuantity < requiredwaterQuantity ) {
            // Show alert if any of the quantities are insufficient
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  // Exit the method if any ingredient is insufficient
        }
    	double userInput = Double.parseDouble(teaprice.getText());

        // Fetch the cost of Americano from the database
        double dbCost = getteaCostFromDatabase();
        if (userInput == dbCost) {
            reducetea();
            var mongoClient = MongoClients.create("mongodb://localhost:27017");
       	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
       	    MongoCollection<Document> collection = database.getCollection("sales");
       	    collection.updateOne(
       	            new Document("name", "Premix"),  // Find document by item name
       	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) // Update the quantity field
       	        ));
        
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        // Close the current stage (optional, depending on your application flow)
        ((Stage) buy3.getScene().getWindow()).close();
        }
        else{
       	 Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Amount");
            alert.setHeaderText(null);
            alert.setContentText("Enter the correct amount");
            alert.show();
       }
    }
    private double getcoffeeCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("premix"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Classic Coffee")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    private double getchaiCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("premix"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Masala Chai")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    private double getteaCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            // Connect to the database
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); // Replace 'YourDatabaseName' with your database name

            // Get the collection containing drink details
            MongoCollection<Document> collection = database.getCollection("premix"); // Replace 'drinkCollection' with your collection name

            // Query the database to find the cost of Americano
            Document americanoDoc = collection.find(new Document("name", "Green Tea")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; // Return default value if not found or error occurs
    }
    
    private void reducechai() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Tea Powder", 15);
        
        update(collection, "Water", 160);
        
    }
    private void reducecoffee() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Coffee Powder", 4);
        
        update(collection, "Water", 170);
        
    }
    private void reducetea() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        // Assuming you want to reduce the quantity of "Bean" by 30, "Milk" by 160, and "Caramel" by 30
        update(collection, "Green Tea Powder", 3);
        
        update(collection, "Water", 180);
        
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
        return 0;
}}
