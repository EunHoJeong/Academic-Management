package first.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	
	public static Connection getConnection() throws Exception{
		Connection connection = null;
		try {
			String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
			String URL = "jdbc:mysql://localhost/studentdb?useSSL=false";
			
			Class.forName(MYSQL_DRIVER);
			
			//2. ����Ÿ ���̽� ����
			
			connection = DriverManager.getConnection(URL, "root", "2737");
			
		}catch(Exception e) {
			System.out.println("DB ���� ����");
		}
		
		return connection;
	}
}
