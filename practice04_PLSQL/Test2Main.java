package proctest;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class Test2Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Connection conn = DbConnect.getInstance().conn();
			if (conn != null) {
				try {
					String sql = "{call test2(?,?)}";
					CallableStatement cstmt = conn.prepareCall(sql);
					//in parameter 첫번째 물음표이고 값은 80, hence (1, 80)
					cstmt.setInt(1, 80);
					//out parameter 두번째 물음표이고 형태는 cursor, hence (2,OracleTypes.CURSOR)
					cstmt.registerOutParameter(2, OracleTypes.CURSOR);
					cstmt.execute();
					
					ResultSet rs = (ResultSet) cstmt.getObject(2);
					while(rs.next()) {
						System.out.println(rs.getInt(1)+". "+rs.getString(2));
					}
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
