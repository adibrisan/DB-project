import java.sql.*;

import javax.swing.*;

public class sqliteConnection {
	Connection conn = null;
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3325/adi","root","");
			
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
	}
}
