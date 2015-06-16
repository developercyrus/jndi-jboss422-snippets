package javase.jndi.jboss422.example1.client;

import javase.jndi.jboss422.example1.client.Client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientIT {
	@Test
    public void test1() throws Exception {
		String expected = Client.getValue();
		System.out.println(expected);
        assertEquals("EN", expected);
	}
	
}