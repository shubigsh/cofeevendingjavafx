package application;

public class Inventory {
	private String name;
	private int quantity;
	public Inventory(String name,int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;    }
    public void setQuantity(int quantity) {
        this.quantity= quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

}
