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

public class BeanController {

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
    private TextField cappprice;

    @FXML
    private TextField caramelprice;
    @FXML
    void americano(ActionEvent event) throws IOException {
    	int requiredBeanQuantity = 30;
        int requiredWaterQuantity = 180;
    	double userInput = Double.parseDouble(ameriprice.getText());
    	int currentBeanQuantity = getAvailableQuantity("Bean");
        int currentWaterQuantity = getAvailableQuantity("Water");
        if (currentBeanQuantity < requiredBeanQuantity || currentWaterQuantity < requiredWaterQuantity) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return; 
        }
        
        double dbCost = getAmericanoCostFromDatabase();
        if (userInput == dbCost) {
            reduceame();
            var mongoClient = MongoClients.create("mongodb://localhost:27017");
          	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
          	    MongoCollection<Document> collection = database.getCollection("sales");
          	    collection.updateOne(
          	            new Document("name", "BeanToCoffee"),  
          	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) 
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
    private int getAvailableQuantity(String itemName) {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        Document document = collection.find(new Document("name", itemName)).first();
        if (document != null) {
            return document.getInteger("quantity", 0);  
        }
        return 0;  
    }

    @FXML
    void back(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        
        ((Stage) back.getScene().getWindow()).close();
    }

    @FXML
    void cappuccino(ActionEvent event) throws IOException {
    	int requiredBeanQuantity = 30;
        int requiredMilkQuantity = 180;
        int requiredMilkfoamQuantity = 80;
        int requiredcocoaQuantity = 20;
        
    	
    	int currentBeanQuantity = getAvailableQuantity("Bean");
        int currentMilkQuantity = getAvailableQuantity("Milk");
        int currentMilkfoamQuantity = getAvailableQuantity("Milk foam");
        int currentcocoaQuantity = getAvailableQuantity("Cocoa mix");;
        if (currentBeanQuantity < requiredBeanQuantity || currentMilkQuantity < requiredMilkQuantity ||currentMilkfoamQuantity < requiredMilkfoamQuantity || currentcocoaQuantity < requiredcocoaQuantity ) {
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  
        }
    	double userInput = Double.parseDouble(cappprice.getText());

        
        double dbCost = getcappuccinoCostFromDatabase();
        if (userInput == dbCost) {
        	reduceCapp();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
         	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
         	    MongoCollection<Document> collection = database.getCollection("sales");
         	    collection.updateOne(
         	            new Document("name", "BeanToCoffee"),  
         	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) 
         	        ));
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        
        ((Stage) buy1.getScene().getWindow()).close();}
    
        else{
    	 Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Invalid Amount");
         alert.setHeaderText(null);
         alert.setContentText("Enter the correct amount");
         alert.show();
    }}


    @FXML
    void caramel(ActionEvent event) throws IOException {
    	
    	int requiredBeanQuantity = 30;
        int requiredMilkQuantity = 160;
        int requiredcaramelQuantity = 30;
        
        
    	
    	int currentBeanQuantity = getAvailableQuantity("Bean");
        int currentMilkQuantity = getAvailableQuantity("Milk");
        int currentcaramelQuantity = getAvailableQuantity("Caramel");
       
        if (currentBeanQuantity < requiredBeanQuantity || currentMilkQuantity < requiredMilkQuantity ||currentcaramelQuantity < requiredcaramelQuantity  ) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Insufficient Inventory");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient quantity");
            alert.show();
            return;  
        }
    	double userInput = Double.parseDouble(caramelprice.getText());

        
        double dbCost = getcaramelCostFromDatabase();
        if (userInput == dbCost) {
            
        	reduceCaramel();
        	var mongoClient = MongoClients.create("mongodb://localhost:27017");
         	 MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
         	    MongoCollection<Document> collection = database.getCollection("sales");
         	    collection.updateOne(
         	            new Document("name", "BeanToCoffee"),  
         	            new Document("$inc", new Document("sales", 1).append("revenue", dbCost) 
         	        ));
    	Parent root = FXMLLoader.load(getClass().getResource("Payment.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        
        ((Stage) buy2.getScene().getWindow()).close();}
    
        else{
    	 Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setTitle("Invalid Amount");
         alert.setHeaderText(null);
         alert.setContentText("Enter the correct amount");
         alert.show();
    }
    }
    private double getAmericanoCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 
           
            MongoCollection<Document> collection = database.getCollection("bean");

            
            Document americanoDoc = collection.find(new Document("name", "Americano")).first();
            if (americanoDoc != null && americanoDoc.containsKey("cost")) {
                return americanoDoc.getDouble("cost");
            }
        }
        return 0.0; 
    }
    private double getcappuccinoCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 

            
            MongoCollection<Document> collection = database.getCollection("bean"); 

            
            Document capp = collection.find(new Document("name", "Cappuccino")).first();
            if (capp != null && capp.containsKey("cost")) {
                return capp.getDouble("cost");
            }
        }
        return 0.0; 
    }
    private double getcaramelCostFromDatabase() {
        try (var mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            
            MongoDatabase database = mongoClient.getDatabase("VendingMachine"); 

            
            MongoCollection<Document> collection = database.getCollection("bean"); 

            
            Document capp = collection.find(new Document("name", "Caramel Macchiato")).first();
            if (capp != null && capp.containsKey("cost")) {
                return capp.getDouble("cost");
            }
        }
        return 0.0;     }
    private void reduceCaramel() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

       
        updateCaramel(collection, "Bean", 40);
        updateCaramel(collection, "Milk", 160);
        updateCaramel(collection, "Caramel", 30);
    }
    private void updateCaramel(MongoCollection<Document> collection, String itemName, int quantityToReduce) {
        
        collection.updateOne(
            new Document("name", itemName),  
            new Document("$inc", new Document("quantity", -quantityToReduce))  
        );
    }
    private void reduceCapp() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        
        updateCapp(collection, "Bean", 40);
        updateCapp(collection, "Milk", 140);
        updateCapp(collection, "Milk foam", 80);
        updateCapp(collection, "Cocoa mix", 20);
    }
    private void updateCapp(MongoCollection<Document> collection, String itemName, int quantityToReduce) {
        
        collection.updateOne(
            new Document("name", itemName),  
            new Document("$inc", new Document("quantity", -quantityToReduce))  
        );
    }
    private void reduceame() {
        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase("VendingMachine");
        var collection = database.getCollection("inventory");

        
        updateame(collection, "Bean", 30);
        updateame(collection, "Water", 180);
        
    }
    private void updateame(MongoCollection<Document> collection, String itemName, int quantityToReduce) {
        
        collection.updateOne(
            new Document("name", itemName),  
            new Document("$inc", new Document("quantity", -quantityToReduce))  
        );
    }
    

}
