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
			
			//2. 데이타 베이스 연결
			
			connection = DriverManager.getConnection(URL, "root", "2737");
			
		}catch(Exception e) {
			System.out.println("DB 연결 에러");
		}
		
		return connection;
	}
}
