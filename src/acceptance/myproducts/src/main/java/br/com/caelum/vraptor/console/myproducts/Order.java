package br.com.caelum.vraptor.console.myproducts;

public class Order {

	private final Product[] products;

	public Order(Product ... products) {
		this.products = products;
	}

	public double getTotal() {
		double total = 0;
		for (Product product : products) {
			total += product.getPrice();
		}
		return total;
	}

}
