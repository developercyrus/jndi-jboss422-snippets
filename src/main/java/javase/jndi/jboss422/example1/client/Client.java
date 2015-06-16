package javase.jndi.jboss422.example1.client;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Client {
	public static void main(String[] args) throws Exception {
		System.out.println(getValue());
	}

	public static String getValue() {
		
        
		try {
			/*
				In junit testing, it can't directly use jndi.properties with "Context ctx = new InitialContext();"
				
				reference: http://stackoverflow.com/questions/12545129/setting-up-jndi-datasource-in-junit
			*/	
			//Context ctx = new InitialContext();
				
			Properties props = new Properties();
	        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
	        props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
	        props.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
			Context ctx = new InitialContext(props);

			DataSource ds = (DataSource) ctx.lookup("jdbc/mysql");
			Connection conn = ds.getConnection();        
	        Statement stmt = conn.createStatement();        
	        ResultSet rs = stmt.executeQuery("select symbol from language where id = 1");
	        if (rs.next()) {
	            return rs.getString("symbol");
	        }
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}