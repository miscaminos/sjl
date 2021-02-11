package proctest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Test1Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DbConnect.getInstance().conn();
			if (conn != null) {
				try {
					//SQL에선 exec로 호출하지만, java에선 call로 호출한다
					String sql = "{call test1(?,?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					//in parameter매칭 (첫번째 물음표)
					cstmt.setString(1, "Kochhar"); 
					//out parameter 매칭 (두번째 물음표)
					cstmt.registerOutParameter(2, OracleTypes.NVARCHAR);
					cstmt.execute();
					String name = (String) cstmt.getObject(2); //obj를 받아서 String으로 downcasting
					System.out.println(name);
					cstmt.close();
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
