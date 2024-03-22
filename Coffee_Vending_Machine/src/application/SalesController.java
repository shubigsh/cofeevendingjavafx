package application;

import java.net.URL;
import java.util.ResourceBundle;

import org.bson.Document;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCursor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SalesController implements Initializable {
	private static final String DATABASE_NAME = "VendingMachine"; // Replace with your database name
    private static final String COLLECTION_NAME = "sales";

    @FXML
    private TableColumn<Sales, String> machine;

    @FXML
    private TableColumn<Sales, Double> revenue;

    @FXML
    private TableColumn<Sales, Integer> sales;
    @FXML
    private TableView<Sales> table;
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
        showSales();
    }
 
 

    public ObservableList<Sales> getSales() {
        ObservableList<Sales> salesLists = FXCollections.observableArrayList();

    	
      	 var mongoClient = MongoClients.create("mongodb://localhost:27017");
               var database = mongoClient.getDatabase(DATABASE_NAME);
               var collection = database.getCollection(COLLECTION_NAME);
      
		try (MongoCursor<Document> cursor = collection.find().iterator()) {
			try {
			    while (cursor.hasNext()) {
			        Document doc = cursor.next();
			        Sales s = new Sales(
			            
			            doc.getString("name"),
			            
			            doc.getInteger("sales"),
			            doc.getDouble("revenue")
			          
			        );
			        salesLists.add(s);
			    }
			} finally {
			    cursor.close();
			}
		}
        
        return salesLists;
    }

    public void showSales() {
    	 ObservableList<Sales> lists = getSales();

	        machine.setCellValueFactory(new PropertyValueFactory<>("name"));
	        sales.setCellValueFactory(new PropertyValueFactory<>("sales"));
	        revenue.setCellValueFactory(new PropertyValueFactory<>("revenue"));
	       


	        table.setItems(lists);
    }
    
}
