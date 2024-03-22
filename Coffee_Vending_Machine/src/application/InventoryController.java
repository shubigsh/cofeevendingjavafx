package application;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

public class InventoryController implements Initializable {
	  @FXML
	    private Button delete;

	    @FXML
	    private Button insert;

    private static final String DATABASE_NAME = "VendingMachine"; // Replace with your database name
    private static final String COLLECTION_NAME = "inventory"; // Replace with your collection name
    @FXML
    private TableColumn<Inventory, String> name;
    @FXML
    private TableColumn<Inventory, Integer> quantity;
  

    @FXML
    private TextField itemfield;

    @FXML
    private TextField quantityfield;

    @FXML
    private Button update;
    
    @FXML
    private TableView<Inventory> table;
	@Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
	
	        showInventory();
	    }
	 
	 

        public ObservableList<Inventory> getInventory() {
            ObservableList<Inventory> inventoryList = FXCollections.observableArrayList();

        	
          	 var mongoClient = MongoClients.create("mongodb://localhost:27017");
                   var database = mongoClient.getDatabase(DATABASE_NAME);
                   var collection = database.getCollection(COLLECTION_NAME);
          
			try (MongoCursor<Document> cursor = collection.find().iterator()) {
				try {
				    while (cursor.hasNext()) {
				        Document doc = cursor.next();
				        Inventory i = new Inventory(
				            
				            doc.getString("name"),
				            
				            doc.getInteger("quantity")
				          
				        );
				        inventoryList.add(i);
				    }
				} finally {
				    cursor.close();
				}
			}
            
            return inventoryList;
        }

        public void showInventory() {
	    	 ObservableList<Inventory> list = getInventory();

		        name.setCellValueFactory(new PropertyValueFactory<>("name"));
		        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		       


		        table.setItems(list);
	    }
        @FXML
        void update(ActionEvent event) { String itemNameToUpdate = itemfield.getText();
        int newQuantity = Integer.parseInt(quantityfield.getText()); // Convert text to integer

        var mongoClient = MongoClients.create("mongodb://localhost:27017");
        var database = mongoClient.getDatabase(DATABASE_NAME);
        var collection = database.getCollection(COLLECTION_NAME);
     
        // Create an update query to set the new quantity for the specified item
        collection.updateOne(
            new Document("name", itemNameToUpdate),  // Find document by item name
            new Document("$set", new Document("quantity", newQuantity))  // Update the quantity field
        );

        // Refresh the table view
        showInventory();
    }
        @FXML
        void delete(ActionEvent event) {
        	String itemName = itemfield.getText();
             

            var mongoClient = MongoClients.create("mongodb://localhost:27017");
            var database = mongoClient.getDatabase(DATABASE_NAME);
            var collection = database.getCollection(COLLECTION_NAME);
         
            
            collection.deleteOne(new Document("name", itemName));
            // Refresh the table view
            showInventory();
        }

        @FXML
        void insert(ActionEvent event) {
        	String itemName = itemfield.getText();
            int itemQuantity = Integer.parseInt(quantityfield.getText()); // Convert text to integer

            var mongoClient = MongoClients.create("mongodb://localhost:27017");
            var database = mongoClient.getDatabase(DATABASE_NAME);
            var collection = database.getCollection(COLLECTION_NAME);
            
            // Create a new document to insert into the collection
            Document newItem = new Document("name", itemName)
                                  .append("quantity", itemQuantity);
            
            // Insert the new document into the collection
            collection.insertOne(newItem);

            // Refresh the table view after insertion
            showInventory();
        }
}