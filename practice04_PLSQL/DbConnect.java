package proctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
	
	private static  DbConnect db = new DbConnect();
	private String driver = "oracle.jdbc.driver.OracleDriver"; //오라클 용 드라이버 명
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //로그인에 필요한 서버 주소 및 sid(sescurity identifier)
	//DB가 내 PC에 있기 때문에 @localhost(@내 PC 주소) 만약 DB가 내 PC가 아니고 다른곳에 있다면 그곳의 주소를 적어야함 
	
	//생성자가  private로 정의 --> singletone
	private DbConnect() {
	}
	//singletone --> 외부에서는 getInstance를 통해서 단 하나의 instance "db"만 사용할 수 있음.
	public static DbConnect getInstance() {
		return db;
	}
	
	public Connection conn() {
		Connection conn = null;
		try {
			Class.forName(driver); //forName() 메소드를 통해 드라이버 클래스를 로드한다.
			conn = DriverManager.getConnection(url,"hr","hr"); //db서버에 접속
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
