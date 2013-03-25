package br.com.caelum.vraptor.console.myproducts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OrderTest {
	
	private static final double ERROR = 0.000001;
	@Test
	public void should_sum_all_prices() {
		Order order = new Order(new Product(30), new Product(40.0));
		assertEquals(70.0, order.getTotal(), ERROR);
	}

}
