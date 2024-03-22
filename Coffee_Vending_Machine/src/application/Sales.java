package application;

public class Sales {
	private String name;
	private int sales;
	private double revenue;
	public Sales(String name,int sales,double revenue) {
		this.name = name;
		this.sales = sales;
		this.revenue = revenue;
	}
	public String getName() {
        return name;
    }

    public int getSales() {
        return sales;    }
    public double getRevenue() {
        return revenue;    }
    
    public void setSales(int sales) {
        this.sales= sales;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

}